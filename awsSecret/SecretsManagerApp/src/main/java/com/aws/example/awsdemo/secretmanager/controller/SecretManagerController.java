package com.aws.example.awsdemo.secretmanager.controller;

import com.aws.example.awsdemo.secretmanager.service.SecretManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/secretManager")
public class SecretManagerController {

    private SecretManagerService awsService;

    @Autowired
    public SecretManagerController(SecretManagerService awsService) {
        this.awsService = awsService;
    }

    @GetMapping("/getSecretValue")
    public ResponseEntity<String> getSecretValue(@RequestParam(value = "secretName") String secretName) throws IOException {
        return new ResponseEntity<>(awsService.getSecretValue(secretName), HttpStatus.OK);
    }

}
