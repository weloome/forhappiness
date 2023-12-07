package com.wimp.sample.model;


import com.wimp.sample.dto.SampleDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sample")
@Data
//@RedisHash(value = "sample", timeToLive = 30)
public class SampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition = "INT UNSIGNED")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    public void updateFromSampleDto(SampleDto sample){
        setTitle(sample.getTitle());
        setDescription(sample.getDescription());
    }
}
