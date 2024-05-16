package org.example.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.ClientService.cb.HandleRequest;


import static org.example.ClientService.cb.RequestType.*;

@SpringBootApplication
public class ClientServiceApplication implements CommandLineRunner {
    @Autowired
    private HandleRequest service;

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (long i = 0; i < 10000; i++) {
            Thread.sleep(4000);
            try {
                System.out.println("MaybeNo: " + service.work(nopeButMaybe));
            } catch (RuntimeException e) {
                System.out.println("MaybeNo: " + e.getMessage());
            }
            System.out.println("Yes: " + service.work(yes));
            System.out.println();
        }
    }
}
