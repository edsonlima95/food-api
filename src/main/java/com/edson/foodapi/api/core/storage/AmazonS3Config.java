package com.edson.foodapi.api.core.storage;


import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {

    @Value("${foodapi.storage.s3.id-chave-de-acesso}")
    private String chaveAcesso;

    @Value("${foodapi.storage.s3.id-chave-secreta}")
    private String chaveSecreta;

    @Value("${foodapi.storage.s3.regiao}")
    private String regiao;

    @Bean
    public AmazonS3 amazonS3() {

        var credentials = new BasicAWSCredentials(chaveAcesso, chaveSecreta);

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(regiao)
                .build();
    }

}
