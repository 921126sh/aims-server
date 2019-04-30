
package oo.user.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import ch.qos.logback.core.util.SystemInfo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import oo.core.domain.DomainEntity;
import oo.core.infrastructure.constant.UserDiv;
import oo.user.api.dto.UserRequest;

/**
 * 사용자 도메인의 사용자 엔티티 오브젝트이다.
 * 
 * @author sh
 */
@Entity
@Table(name = "TB_USER")
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User extends DomainEntity<UserIdentity> {
    private static final long serialVersionUID = -3508285197962847409L;
    
    /**
     * 사용자엔티티PK
     */
    @EmbeddedId
    private UserIdentity uid = new UserIdentity();
    
    /**
     * 사용자식별자
     */
    @NonNull
    @Getter
    @Column(name = "user_id", length = 36, nullable = false, unique = true)
    private String userId;
    
    /**
     * 사용자명
     */
    @NonNull
    @Getter
    @Column(name = "user_nm", length = 50, nullable = false)
    private String userNm;
    
    /**
     * 사용자패스워드
     */
    @NonNull
    @Getter
    @Column(name = "user_pw", length = 100, nullable = false)
    private String userPw;
    
    /**
     * 사용자구분
     */
    @NonNull
    @Getter
    @Column(name = "USER_DIV", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private UserDiv userDiv;
    
    
    /**
     * 사용자 엔티티 정보를 변경한다.
     * 
     * @param userRequest 사용자 요청 데이터
     * @param systemInfo 시스템정보
     */
    public void modify(UserRequest userRequest, SystemInfo systemInfo) {
    }

    @Override
    public UserIdentity getUid() {
        return uid;
    }

    public void chgUserInfo(String userId, String userNm, String pwd, UserDiv userDiv) {
        this.userId = userId;
        this.userNm = userNm;
        this.userPw = pwd;
        this.userDiv = userDiv;
    }
}
