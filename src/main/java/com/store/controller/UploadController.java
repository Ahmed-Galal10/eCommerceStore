package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.util.s3.S3UploadManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {

    private S3UploadManager s3UploadManager;

    @Autowired
    public  UploadController (S3UploadManager s3UploadManager){
        this.s3UploadManager = s3UploadManager;
    }



    @PostMapping(params = "multiple")
    public ResponseEntity<GenericResponse> uploadMultipleFiles(@RequestParam("files")MultipartFile[] files){

        List<String> uriList = Arrays.stream(files).map(this::uploadFile)
                .collect(Collectors.toList());

        GenericResponse< List<String > > response =
                new GenericResponse<>(uriList, HttpStatus.CREATED, "UPLAODED SUCCEFFULY");

        return  ResponseEntity.status(HttpStatus.CREATED).body(response );

    }
    @PostMapping
    public  ResponseEntity<GenericResponse> uploadSingleFile(@RequestParam("file")MultipartFile file){

       String uri  = uploadFile(file);
        GenericResponse<String> response =
                new GenericResponse(uri, HttpStatus.CREATED, "UPLOADED SUCCESSFULLY");

        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private String uploadFile(MultipartFile file)   {

        if(file.isEmpty() ){
            return  "https://via.placeholder.com/300/?Text=Prod";
        }

        byte[] byteArr = new byte[0];
        try {
            byteArr = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return  "https://via.placeholder.com/300/?Text=Prod";
        }
        String uri =  s3UploadManager.uploadImage( byteArr );
        return uri;
    }
}
