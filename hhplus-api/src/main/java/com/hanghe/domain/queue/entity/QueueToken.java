package com.hanghe.domain.queue.entity;

import com.hanghe.domain.base.BaseEntity;
import com.hanghe.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class QueueToken extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;

    private String token;
    private LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    private QueueStatus status;

    /** 토큰 발행 */
    public static QueueToken generateToken(User user) {
        QueueToken queueToken = new QueueToken();
        queueToken.user = user;
        queueToken.token = UUID.randomUUID().toString();
        queueToken.expiredAt = LocalDateTime.now().plusMinutes(5);
        return queueToken;
    }

    /** 토큰 검증 */
    public void validation(){
        switch (status) {
            case EXPIRED:
                throw new RuntimeException("토큰이 만료되었습니다.");
            case WAIT:
                throw new RuntimeException("입장 대기 중인 토큰입니다.");
            case ACTIVE:
                break;
            default:
                throw new RuntimeException("유효하지 않은 토큰 상태입니다.");
        }
    }

    /** 토큰 ACTIVE */
    public void acticeToken() {
       setStatus(QueueStatus.ACTIVE);
       setExpiredAt(LocalDateTime.now().plusMinutes(5));
    }

    /** 토큰 EXPIRE */
    public void expireToken() {
        setStatus(QueueStatus.EXPIRED);
    }

}
