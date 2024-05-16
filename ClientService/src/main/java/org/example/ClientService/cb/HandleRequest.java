package org.example.ClientService.cb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HandleRequest {
    private final Map<RequestType, CircuitBreaker> circuitBreakerMap;
    private final RestTemplate restTemplate;


    @Autowired
    public HandleRequest(List<CircuitBreaker> circuitBreakerList, RestTemplate restTemplate) {
        circuitBreakerMap = circuitBreakerList.stream()
                .collect(Collectors.toMap(CircuitBreaker::type, Function.identity()));
        this.restTemplate = restTemplate;
    }


    public int work(RequestType type) throws RuntimeException {
        CircuitBreaker circuitBreaker = circuitBreakerMap.get(type);
        String url = "http://localhost:8089/service" + "/" + type;
        return circuitBreaker.handleRequest(() ->
            restTemplate.getForObject(url, Integer.class));
    }
}
