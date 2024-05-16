package org.example.ClientService.cb;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
public abstract class CircuitBreaker {
    private boolean isOpen;
    private LocalDateTime lastFailureTime;
    private int failuresCount;

    public int handleRequest(Requestable requestable) {
        try {
            if (isOpen) {
                int intervalSeconds = 10;
                if (lastFailureTime.plusSeconds(intervalSeconds).isBefore(LocalDateTime.now())) {
                    close();
                } else {
                    throw new RuntimeException("Circuit will be closed in "
                            + ChronoUnit.SECONDS.between(LocalDateTime.now(), lastFailureTime.plusSeconds(intervalSeconds)) + " seconds");
                }
            }
            return requestable.call();
        } catch (HttpServerErrorException ex) {
            failuresCount++;
            if (failuresCount < 2) {
                System.out.println("Retrying...");
                return handleRequest(requestable);
            } else {
                open();
                throw new RuntimeException("Opening circuit breaker in " + lastFailureTime);
            }
        }
    }


    public abstract RequestType type();

    private void open() {
        lastFailureTime = LocalDateTime.now();
        isOpen = true;
        failuresCount = 0;
    }

    private void close() {
        lastFailureTime = null;
        isOpen = false;
        failuresCount = 0;
    }
}
