package com.store.config;

import com.store.util.s3.S3UploadManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@PropertySource(value = { "classpath:s3client.properties" })
public class S3Configs {

    @Value("${accesskeyId}")
    private  String accesskeyId;
    @Value("${accessKeySecret}")
    private  String accessKeySecret;

    @Bean(name = "s3Client")
    public S3Client getS3Client(){
         S3Client s3Client;
        AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accesskeyId, accessKeySecret);
        Region region = Region.EU_CENTRAL_1;
        s3Client = S3Client.builder()
                .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                .region(region)
                .build();

        return  s3Client;
    }

}
