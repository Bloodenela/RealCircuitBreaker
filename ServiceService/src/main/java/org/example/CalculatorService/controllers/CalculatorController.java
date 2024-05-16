package org.example.CalculatorService.controllers;

import lombok.RequiredArgsConstructor;
import org.example.CalculatorService.services.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class CalculatorController {
    private final ServiceService serviceServiceService;

    @GetMapping("/yes")
    ResponseEntity<Integer> onlyYes() {
        int yesRes = serviceServiceService.yes();
        return new ResponseEntity<>(yesRes, HttpStatus.OK);
    }

    @GetMapping("/nopeButMaybe")
    ResponseEntity<Integer> yesOrNotIDK() {
        int maybeRes = serviceServiceService.nopeButMaybe();
        return new ResponseEntity<>(maybeRes, HttpStatus.OK);
    }

}
