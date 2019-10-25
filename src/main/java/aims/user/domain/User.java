
package aims.user.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import aims.core.infrastructure.sysauth.SystemInfo;
import aims.user.api.dto.UserRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import aims.core.domain.DomainEntity;

/**
 * 사용자 도메인의 사용자 엔티티 오브젝트이다.
 * 
 * @author sh
 */
@Entity
@Table(name = "tb_user")
@ToString(callSuper = true)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User extends DomainEntity<UserIdentity> {
    private static final long serialVersionUID = -3508285197962847409L;
    
    /**
     * 사용자엔티티PK
     */
    @EmbeddedId
    @NonNull
    private UserIdentity id;

    /**
     * 사용자 명
     */
    @Getter
    @NonNull
    @Column(name = "user_nm", length = 50, nullable = false)
    private String userNm;
    
    /**
     * 사용자패스워드
     */
    @Getter
    @NonNull
    @Column(name = "user_pwd", length = 15, nullable = false)
    private String userPwd;

    @Override 
    public UserIdentity getIdentity() {
        return this.id;
    }

    /**
     * 사용자 엔티티 정보를 변경한다.
     *
     * @param userRequest 사용자 요청 데이터
     * @param systemInfo 시스템정보
     */
    public void modify(UserRequest userRequest, SystemInfo systemInfo) {

    }

    /**
     *사용자 엔티티 정보를 변경한다.
     *
     * @param userNm 사용자명
     */
    public void modify(String userNm) {
    }

    /**
     * 패스워드를 변경한다.
     *
     * @param newPwd 신규 패스워드
     */
    public void chgPwd(String newPwd) {
        this.userPwd = newPwd;
    }
}
