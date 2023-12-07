package com.wimp.sample.application;


import com.wimp.global.service.CacheService;
import com.wimp.sample.dao.sample.SampleRepository;
import com.wimp.sample.dto.SampleDto;
import com.wimp.sample.event.SampleEvent;
import com.wimp.sample.model.SampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SampleService {
    private final SampleRepository sampleRepository;
    private final CacheService cacheService;
    private final ApplicationEventPublisher publisher;

    @Transactional
    public SampleDto sample(Long id) {
        SampleEntity sample = sampleRepository.searchById(id);

        return SampleDto.fromSampleEntity(sample);
    }

    @Transactional
    public SampleDto updateSample(SampleDto sampleDto){

        SampleEntity sample = sampleRepository.findById(sampleDto.getId()).orElseThrow(
                () -> new RuntimeException("찾을 수 없습니다.")
        );

        sample.updateFromSampleDto(sampleDto);

        return SampleDto.fromSampleEntity(sample);
    }

    public SampleDto cacheSetTest() throws IOException {
        SampleEntity sample = sampleRepository.searchById(1L);
        SampleDto result = SampleDto.fromSampleEntity(sample);
        cacheService.set("test", result);
        return result;
    }

    public SampleDto cacheGetTest(){
        SampleDto sampleDto = null;

        try{
            sampleDto = cacheService.<SampleDto>get("test");

        }catch (Exception e){
            e.printStackTrace();
        }

        return sampleDto;
    }

    public void event(){
        publisher.publishEvent(new SampleEvent(this,"Event Message"));
    }
}
