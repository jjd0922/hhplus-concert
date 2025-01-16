package com.hanghe.domain.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = 412121979L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final com.hanghe.domain.base.QBaseEntity _super = new com.hanghe.domain.base.QBaseEntity(this);

    public final com.hanghe.domain.concert.entity.QConcertSeat concertSeat;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> expiredAt = createDateTime("expiredAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.hanghe.domain.payment.entity.QPayment payment;

    public final DateTimePath<java.time.LocalDateTime> reservedAt = createDateTime("reservedAt", java.time.LocalDateTime.class);

    public final EnumPath<ReservationStatus> status = createEnum("status", ReservationStatus.class);

    public final com.hanghe.domain.user.entity.QUser user;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.concertSeat = inits.isInitialized("concertSeat") ? new com.hanghe.domain.concert.entity.QConcertSeat(forProperty("concertSeat"), inits.get("concertSeat")) : null;
        this.payment = inits.isInitialized("payment") ? new com.hanghe.domain.payment.entity.QPayment(forProperty("payment"), inits.get("payment")) : null;
        this.user = inits.isInitialized("user") ? new com.hanghe.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

