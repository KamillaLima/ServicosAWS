package com.eventostec.api.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {
    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonS3 createS3Instance(){
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                //Falando pra eles pegarem as minhas credenciais padrão do meu pc
                .withRegion(awsRegion).build();
        //Standard: credenciais que estão configuradas na minha maquina

    }

}
