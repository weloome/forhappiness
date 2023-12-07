package com.wimp.customer.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCustomerEntity is a Querydsl query type for CustomerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerEntity extends EntityPathBase<CustomerEntity> {

    private static final long serialVersionUID = -281149878L;

    public static final QCustomerEntity customerEntity = new QCustomerEntity("customerEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> dob = createDate("dob", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final StringPath firstName = createString("firstName");

    public final StringPath fullName = createString("fullName");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public final StringPath passwordHash = createString("passwordHash");

    public final StringPath phone = createString("phone");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public QCustomerEntity(String variable) {
        super(CustomerEntity.class, forVariable(variable));
    }

    public QCustomerEntity(Path<? extends CustomerEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomerEntity(PathMetadata metadata) {
        super(CustomerEntity.class, metadata);
    }

}

