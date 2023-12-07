package com.wimp.sample.dao.sample;


import com.wimp.sample.model.SampleEntity;

public interface SampleRepositoryInterface {

    SampleEntity searchById(Long id);
}
