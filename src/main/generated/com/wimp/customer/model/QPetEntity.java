package com.wimp.customer.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPetEntity is a Querydsl query type for PetEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPetEntity extends EntityPathBase<PetEntity> {

    private static final long serialVersionUID = 310588249L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPetEntity petEntity = new QPetEntity("petEntity");

    public final QCenterEntity center;

    public final StringPath comment = createString("comment");

    public final StringPath description = createString("description");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath location = createString("location");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> weight = createNumber("weight", Integer.class);

    public QPetEntity(String variable) {
        this(PetEntity.class, forVariable(variable), INITS);
    }

    public QPetEntity(Path<? extends PetEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPetEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPetEntity(PathMetadata metadata, PathInits inits) {
        this(PetEntity.class, metadata, inits);
    }

    public QPetEntity(Class<? extends PetEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.center = inits.isInitialized("center") ? new QCenterEntity(forProperty("center")) : null;
    }

}

