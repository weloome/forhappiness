package com.wimp.sample.dao.sample;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wimp.sample.model.SampleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class SampleRepositoryImpl implements SampleRepositoryInterface {
    private final JPAQueryFactory queryFactory;

    @Override
    public SampleEntity searchById(Long id) {
        return null;
    }
}
