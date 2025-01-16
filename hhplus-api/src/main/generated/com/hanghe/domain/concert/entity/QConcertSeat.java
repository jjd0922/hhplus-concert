package com.hanghe.domain.concert.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConcertSeat is a Querydsl query type for ConcertSeat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConcertSeat extends EntityPathBase<ConcertSeat> {

    private static final long serialVersionUID = 532513888L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConcertSeat concertSeat = new QConcertSeat("concertSeat");

    public final com.hanghe.domain.base.QBaseEntity _super = new com.hanghe.domain.base.QBaseEntity(this);

    public final QConcertSchedule concertSchedule;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Integer> no = createNumber("no", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final EnumPath<SeatStatus> status = createEnum("status", SeatStatus.class);

    public QConcertSeat(String variable) {
        this(ConcertSeat.class, forVariable(variable), INITS);
    }

    public QConcertSeat(Path<? extends ConcertSeat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConcertSeat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConcertSeat(PathMetadata metadata, PathInits inits) {
        this(ConcertSeat.class, metadata, inits);
    }

    public QConcertSeat(Class<? extends ConcertSeat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.concertSchedule = inits.isInitialized("concertSchedule") ? new QConcertSchedule(forProperty("concertSchedule"), inits.get("concertSchedule")) : null;
    }

}

