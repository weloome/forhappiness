package com.wimp.sample.api;


import com.wimp.sample.application.SampleService;
import com.wimp.sample.dto.SampleDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SampleController {

    private final SampleService sampleService;
    private final HttpServletRequest httpServletRequest;

    @GetMapping("/eks-test")
    public String test(){

        return httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR") + "|"
                + httpServletRequest.getHeader("REMOTE_ADDR")+ "|"
                + httpServletRequest.getHeader("X-Forwarded-For")+ "|"
                + httpServletRequest.getHeader("x-forwarded-for") + "|"
                + httpServletRequest.getRemoteAddr();

    }

    @GetMapping("/sample")
    @Cacheable(value = "sample", key = "#id")
    public SampleDto sample(Long id) {
        return sampleService.sample(id);
    }

    @PutMapping("/sample")
    @CachePut(value = "sample", key = "#sampleDto.id")
    public SampleDto putSample(SampleDto sampleDto) {
        return sampleService.updateSample(sampleDto);
    }

    @DeleteMapping("/sample/cache")
    @CacheEvict(value = "sample", allEntries = true)
    public Boolean deleteSampleCache(){
        return true;
    }

    @GetMapping("/test/cache/set")
    public SampleDto testCacheSet() throws IOException {
        return sampleService.cacheSetTest();
    }

    @GetMapping("/test/cache/get")
    public SampleDto testCacheGet(){
        return sampleService.cacheGetTest();
    }

    @GetMapping("/event")
    public Boolean event(){
        sampleService.event();
        return true;
    }
}
