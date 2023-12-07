package com.wimp.customer.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCenterEntity is a Querydsl query type for CenterEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCenterEntity extends EntityPathBase<CenterEntity> {

    private static final long serialVersionUID = 508296129L;

    public static final QCenterEntity centerEntity = new QCenterEntity("centerEntity");

    public final StringPath address = createString("address");

    public final StringPath department = createString("department");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final ListPath<PetEntity, QPetEntity> pets = this.<PetEntity, QPetEntity>createList("pets", PetEntity.class, QPetEntity.class, PathInits.DIRECT2);

    public final StringPath tel = createString("tel");

    public QCenterEntity(String variable) {
        super(CenterEntity.class, forVariable(variable));
    }

    public QCenterEntity(Path<? extends CenterEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCenterEntity(PathMetadata metadata) {
        super(CenterEntity.class, metadata);
    }

}

