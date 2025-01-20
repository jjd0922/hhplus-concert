package com.hanghe.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    // 공통 에러
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid input value"),

    // 도메인 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "payment not found"),
    CONCERT_NOT_FOUND(HttpStatus.NOT_FOUND, "concert not found"),
    CONCERT_SCEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "concertScehule not found"),
    CONCERT_SEAT_NOT_FOUND(HttpStatus.NOT_FOUND, "concertSeat not found"),

    // 비즈니스 애러
    USER_INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "잔액 부족"),
    USER_MINUS_AMOUNT(HttpStatus.BAD_REQUEST, "마이너스 잔액 충전"),
    PAYMENT_INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "잔액 부족"),
    PAYMENT_MINUS_AMOUNT(HttpStatus.BAD_REQUEST, "마이너스 잔액 충전"),
    TOKEN_EXPIRED(HttpStatus.BAD_REQUEST, "토큰 만료"),
    TOKEN_WAIT(HttpStatus.BAD_REQUEST, "토큰 대기중"),
    TOKEN_INVALID(HttpStatus.BAD_REQUEST, "유효하지 않은 토큰"),
    RESERVATION_COMPLETE(HttpStatus.BAD_REQUEST, "예약 완료 좌석"),
    RESERVATION_CANCEL(HttpStatus.BAD_REQUEST, "예약 취소 좌석");

    private final HttpStatus status;
    private final String msg;

    ErrorCode(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
