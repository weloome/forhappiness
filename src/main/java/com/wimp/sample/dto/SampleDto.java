package com.wimp.sample.dto;


import com.wimp.sample.model.SampleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SampleDto implements Serializable {
    @Id
    private Long id;
    private String title;
    private String description;

    public static SampleDto fromSampleEntity(SampleEntity sample){
        return SampleDto.builder()
                .id(sample.getId())
                .title(sample.getTitle())
                .description(sample.getDescription())
                .build();
    }

}
