package com.hanghe.domain.concert.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConcertSchedule is a Querydsl query type for ConcertSchedule
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConcertSchedule extends EntityPathBase<ConcertSchedule> {

    private static final long serialVersionUID = -467295374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConcertSchedule concertSchedule = new QConcertSchedule("concertSchedule");

    public final com.hanghe.domain.base.QBaseEntity _super = new com.hanghe.domain.base.QBaseEntity(this);

    public final QConcert concert;

    public final ListPath<ConcertSeat, QConcertSeat> concertSeatList = this.<ConcertSeat, QConcertSeat>createList("concertSeatList", ConcertSeat.class, QConcertSeat.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DatePath<java.time.LocalDate> performanceDate = createDate("performanceDate", java.time.LocalDate.class);

    public final NumberPath<Integer> seatLimit = createNumber("seatLimit", Integer.class);

    public QConcertSchedule(String variable) {
        this(ConcertSchedule.class, forVariable(variable), INITS);
    }

    public QConcertSchedule(Path<? extends ConcertSchedule> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConcertSchedule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConcertSchedule(PathMetadata metadata, PathInits inits) {
        this(ConcertSchedule.class, metadata, inits);
    }

    public QConcertSchedule(Class<? extends ConcertSchedule> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.concert = inits.isInitialized("concert") ? new QConcert(forProperty("concert")) : null;
    }

}

