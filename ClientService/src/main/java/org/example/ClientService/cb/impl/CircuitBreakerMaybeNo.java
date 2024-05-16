package org.example.ClientService.cb.impl;

import org.example.ClientService.cb.CircuitBreaker;
import org.example.ClientService.cb.RequestType;
import org.springframework.stereotype.Component;

@Component
public class CircuitBreakerMaybeNo extends CircuitBreaker {
    @Override
    public RequestType type() {
        return RequestType.nopeButMaybe;
    }
}
