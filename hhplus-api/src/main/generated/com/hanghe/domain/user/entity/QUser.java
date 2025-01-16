package com.hanghe.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -852019349L;

    public static final QUser user = new QUser("user");

    public final com.hanghe.domain.base.QBaseEntity _super = new com.hanghe.domain.base.QBaseEntity(this);

    public final NumberPath<Integer> balance = createNumber("balance", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final ListPath<com.hanghe.domain.payment.entity.Payment, com.hanghe.domain.payment.entity.QPayment> paymentList = this.<com.hanghe.domain.payment.entity.Payment, com.hanghe.domain.payment.entity.QPayment>createList("paymentList", com.hanghe.domain.payment.entity.Payment.class, com.hanghe.domain.payment.entity.QPayment.class, PathInits.DIRECT2);

    public final ListPath<com.hanghe.domain.queue.entity.QueueToken, com.hanghe.domain.queue.entity.QQueueToken> userQueueListToken = this.<com.hanghe.domain.queue.entity.QueueToken, com.hanghe.domain.queue.entity.QQueueToken>createList("userQueueListToken", com.hanghe.domain.queue.entity.QueueToken.class, com.hanghe.domain.queue.entity.QQueueToken.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

