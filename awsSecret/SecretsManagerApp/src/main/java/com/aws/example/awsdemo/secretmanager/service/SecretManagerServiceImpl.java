package com.aws.example.awsdemo.secretmanager.service;

import com.aws.example.awsdemo.secretmanager.repository.SecretManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SecretManagerServiceImpl implements SecretManagerService {

    private SecretManagerRepository secretManagerRepository;

    @Autowired
    public SecretManagerServiceImpl(SecretManagerRepository secretManagerRepository) {
        this.secretManagerRepository = secretManagerRepository;
    }


    @Override
    public String getSecretValue(String secretName) {
        return secretManagerRepository.getSecretValue(secretName);
    }
}
