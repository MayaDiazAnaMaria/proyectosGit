package com.aws.example.awsdemo.secretmanager.service;


public interface SecretManagerService {

    String getSecretValue(String secretName);
}
