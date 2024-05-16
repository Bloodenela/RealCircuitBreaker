package org.example.CalculatorService.services.impl;

import org.example.CalculatorService.services.ServiceService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Override
    public int yes() {
        return 1+1;
    }

    @Override
    public int nopeButMaybe() {
        Integer x  = new Random().nextInt();
        if(x%2==0) return x;
        throw new RuntimeException();
    }
}
