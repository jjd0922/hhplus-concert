package com.hanghe.domain.payment.queue.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.hanghe.domain.queue.entity.QueueStatus;
import com.hanghe.domain.queue.entity.QueueToken;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQueueToken is a Querydsl query type for QueueToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQueueToken extends EntityPathBase<QueueToken> {

    private static final long serialVersionUID = -1368866314L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQueueToken queueToken = new QQueueToken("queueToken");

    public final com.hanghe.domain.base.QBaseEntity _super = new com.hanghe.domain.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> expiredAt = createDateTime("expiredAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final EnumPath<QueueStatus> status = createEnum("status", QueueStatus.class);

    public final StringPath token = createString("token");

    public final com.hanghe.domain.user.entity.QUser user;

    public QQueueToken(String variable) {
        this(QueueToken.class, forVariable(variable), INITS);
    }

    public QQueueToken(Path<? extends QueueToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQueueToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQueueToken(PathMetadata metadata, PathInits inits) {
        this(QueueToken.class, metadata, inits);
    }

    public QQueueToken(Class<? extends QueueToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.hanghe.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

