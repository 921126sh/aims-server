
package aims.menu.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import aims.core.domain.DomainEntity;
import aims.core.infrastructure.sysauth.SystemInfo;
import aims.menu.api.dto.MnuRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 메뉴 도메인의 메뉴 엔티티 오브젝트이다.
 * 
 * @author JH
 */
@Entity
@Table(name = "TB_MNU")
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Mnu extends DomainEntity<MnuIdentity> {
    private static final long serialVersionUID = -5125673686438634771L;
    
    /**
     * 메뉴엔티티PK
     */
    @EmbeddedId
    private MnuIdentity uid = new MnuIdentity();
    
    /**
     * 메뉴식별자
     */
    @NonNull
    @Getter
    @Column(name = "mnu_id", length = 36, nullable = false, unique = true)
    private String mnuId;
    
    /**
     * 메뉴명
     */
    @NonNull
    @Getter
    @Column(name = "mnu_nm", length = 50, nullable = false)
    private String mnuNm;
    
    /**
     * 메뉴 연결내용
     */
    @NonNull
    @Getter
    @Column(name = "mnu_ctnt", length = 500, nullable = true)
    private String mnuCtnt;
    
    /**
     * 메뉴 스타일
     */
    @Getter
    @Setter
    @Column(name = "mnu_style", length = 50, nullable = true)
    private String mnuStyle;
    
    /**
     * 메뉴 정렬순서
     */
    @NonNull
    @Getter
    @Column(name = "mnu_sort")
    private Integer mnuSort;
    
    @Override
    public MnuIdentity getUid() {
        return uid;
    }
    
    /**
     * 메뉴 엔티티 정보를 변경한다.
     * 
     * @param mnuRequest 메뉴 요청 데이터
     * @param systemInfo 시스템정보
     */
    public void modify(MnuRequest mnuRequest, SystemInfo systemInfo) {
        this.mnuId = mnuRequest.getMnuId();
        this.mnuNm = mnuRequest.getMnuNm();
        this.mnuCtnt = mnuRequest.getMnuCtnt();
        this.mnuStyle = mnuRequest.getMnuStyle();
        this.mnuSort = mnuRequest.getMnuSort();
        
        this.lstChgId = systemInfo.getUserId();
        this.lstChgDt = LocalDateTime.now();
    }
}
