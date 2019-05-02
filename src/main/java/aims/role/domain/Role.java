
package aims.role.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import aims.core.domain.DomainEntity;
import aims.core.infrastructure.sysauth.SystemInfo;
import aims.role.api.dto.RoleRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 역할 도메인의 역할 엔티티 오브젝트이다.
 * 
 * @author JH
 */
@Entity
@Table(name = "TB_ROLE")
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Role extends DomainEntity<RoleIdentity> {
    private static final long serialVersionUID = 1315014160055245811L;

    /**
     * 역할엔티티PK
     */
    @EmbeddedId
    private RoleIdentity uid = new RoleIdentity();
    
    /**
     * 역할식별자
     */
    @NonNull
    @Getter
    @Column(name = "role_id", length = 36, nullable = false, unique = true)
    private String roleId;
    
    /**
     * 역할명
     */
    @NonNull
    @Getter
    @Column(name = "role_nm", length = 50, nullable = false)
    private String roleNm;
    
    /**
     * 역할 설명
     */
    @NonNull
    @Getter
    @Column(name = "role_desc", length = 500, nullable = true)
    private String roleDesc;
    
    @Override
    public RoleIdentity getUid() {
        return uid;
    }
    
    /**
     * 역할 엔티티 정보를 변경한다.
     * 
     * @param roleRequest 역할 요청 데이터
     * @param systemInfo 시스템정보
     */
    public void modify(RoleRequest roleRequest, SystemInfo systemInfo) {
        this.roleId = roleRequest.getRoleId();
        this.roleNm = roleRequest.getRoleNm();
        this.roleDesc = roleRequest.getRoleDesc();
        
        this.lstChgId = systemInfo.getUserId();
        this.lstChgDt = LocalDateTime.now();
    }
}
