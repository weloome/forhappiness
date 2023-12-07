package com.wimp.sample.dao.sample;

import com.wimp.sample.model.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<SampleEntity,Long> ,SampleRepositoryInterface{
}
