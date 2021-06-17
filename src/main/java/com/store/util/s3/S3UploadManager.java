package com.store.util.s3;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

@Component
@PropertySource(value = { "classpath:s3client.properties" })
public class S3UploadManager {

    @Value("${BUCKET_NAME}")
    private String BUCKET_NAME;

    private final S3Client s3Client;
    private final UUIDProvider uuidProvider;

    @Autowired
    public  S3UploadManager(S3Client s3Client, UUIDProvider uuidProvider){
        this.s3Client = s3Client;
        this.uuidProvider = uuidProvider;
    }

    //Will Upload Byte array and return it's URL
    public String uploadImage(byte[] byteArr){

        String fileName = uuidProvider.getUUID();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .key(fileName)
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromByteBuffer( ByteBuffer.wrap(byteArr) ) );

        return s3Client.utilities().getUrl(builder -> builder.
                bucket(BUCKET_NAME)
                .key(fileName))
                .toExternalForm();
    }


    public void deleteImage(String imageUrl){
        URI uri = null;
        try {
            uri = new URI(imageUrl);
            //ASSUMING WE UPLOAD DIRECTLY  TO BUCKET
            String fileName = uri.getPath().substring(1);


            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest
                    .builder()
                    .bucket(BUCKET_NAME)
                    .key(fileName)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }


}
