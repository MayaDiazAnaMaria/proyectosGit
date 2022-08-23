package com.aws.example.awsdemo.secretmanager.repository;


public interface SecretManagerRepository {

    String getSecretValue(String secretName);

}
