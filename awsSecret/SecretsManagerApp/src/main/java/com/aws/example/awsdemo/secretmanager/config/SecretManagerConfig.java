package com.aws.example.awsdemo.secretmanager.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretManagerConfig {

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.secretsmanager.endpoint}")
    private String  endpoints;


    @Bean
    public AWSSecretsManager smClient() {
        AWSSecretsManagerClientBuilder  clientBuilder  =  AWSSecretsManagerClientBuilder.standard();
        AwsClientBuilder.EndpointConfiguration  config  =  new  AwsClientBuilder.EndpointConfiguration(endpoints, region);
        clientBuilder.setEndpointConfiguration(config);
        return clientBuilder.build();
    }

}
