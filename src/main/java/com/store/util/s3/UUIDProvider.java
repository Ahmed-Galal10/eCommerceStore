package com.store.util.s3;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UUIDProvider {

    public  String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
