package com.wimp.global.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CacheService {
    private final StringRedisTemplate redisTemplate;

    public ValueOperations<String, String> getOpsForValue() {
        return redisTemplate.opsForValue();
    }

    public Set<String> getKeys(String pattern){
        return redisTemplate.keys(pattern);
    }

    public void set(String key, Object value) throws IOException {
        ValueOperations<String, String> valueOperations = getOpsForValue();

        byte[] serializedMember;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(value);
                serializedMember = baos.toByteArray();
            }
        }

        valueOperations.set(key, Base64.getEncoder().encodeToString(serializedMember));
    }


    public <T> T get(String key) throws IOException, ClassNotFoundException {
        ValueOperations<String, String> valueOperations = getOpsForValue();
        byte[] serializedMember = Base64.getDecoder().decode(valueOperations.get(key));

        T result = null;

        try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
            try (ObjectInputStream ois = new ObjectInputStream(bais)) {
                Object objectMember = ois.readObject();
                result = (T) objectMember;
            }
        }

        return result;
    }

}
