package com.wimp.sample.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSampleEntity is a Querydsl query type for SampleEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSampleEntity extends EntityPathBase<SampleEntity> {

    private static final long serialVersionUID = 2002867466L;

    public static final QSampleEntity sampleEntity = new QSampleEntity("sampleEntity");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QSampleEntity(String variable) {
        super(SampleEntity.class, forVariable(variable));
    }

    public QSampleEntity(Path<? extends SampleEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSampleEntity(PathMetadata metadata) {
        super(SampleEntity.class, metadata);
    }

}

