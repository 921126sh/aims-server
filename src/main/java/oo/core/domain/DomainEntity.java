package oo.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 추상화된 최상위 도메인 엔터티 오브젝트이다.
 * 
 * @author sh
 * @param <ID> 엔터티 UID 유형
 */
@MappedSuperclass
public abstract class DomainEntity<IDENTITY extends DomainIdentity> implements Serializable {
	private static final long serialVersionUID = 8581525848268817615L;

	/**
     * 최초 등록자
     */
    @CreatedBy
    @Column(name = "fst_reg_id", length = 36, nullable = true, updatable = false)
    protected String fstRegId;
    
    /**
     * 최초 등록일시
     */
    @CreatedDate
    @Column(name = "fst_reg_dt", nullable = false, updatable = false)
    protected LocalDateTime fstRegDt = LocalDateTime.now();
    
    /**
     * 최총 변경자
     */
    @LastModifiedBy
    @Column(name = "lst_chg_id", length = 36, nullable = true)
    protected String lstChgId;
    
    /**
     * 최종 변경일시
     */
    @LastModifiedDate
    @Column(name = "lst_chg_dt", nullable = false)
    protected LocalDateTime lstChgDt = LocalDateTime.now();
    
    /**
     * 기본 생성자이다.
     */
    public DomainEntity() {
    }
    
    /**
     * 도메인 엔터티 식별자를 반환한다.
     * 
     * @return 도메인 엔터티 식별자
     */
    public abstract IDENTITY getUid();
    
    //------------------------------------------------------------------------------------------------------------
	
    /**
     * 플랫폼에 의해 엔티티 신규시 저장 전 호출되어지는 메서드로서 내부적으로 최초등록일시와 최종변경일시를 초기화하고 하위 구현체의 신규 전 초기화 메서드를 호출한다.
     */
    @PrePersist
    private void onPrePersist() {
        // 하위 구현체의 신규 전 초기화 메서드를 호출한다.
        onCreate();
    }
    
    /**
     * 플랫폼에 의해 엔티티 변경 저장 전 호출되어지는 메서드로서 내부적으로 최종변경일시를 초기화하고 하위 구현체의 변경 전 초기화 메서드를 호출한다.
     */
    @PreUpdate
    private void onPreUpdate() {
        // 하위 구현체의 변경 전 초기화 메서드를 호출한다.
        onUpdate();
    }
    
    /**
     * 엔티티 신규 저장 전 초기화할 로직을 구사한다.
     */
    protected void onCreate() {
    }
    
    /**
     * 엔티티 변경 저장 전 초기화할 로직을 구사한다.
     */
    protected void onUpdate() {
    }
    
    /**
     * 최초 등록자를 반환한다.
     * 
     * @return 최초 등록자
     */
    public String getFstRegId() {
        return fstRegId;
    }
    
    /**
     * 최초 등록일시를 반환한다.
     * 
     * @return 최초 등록일시
     */
    public LocalDateTime getFstRegDt() {
        return fstRegDt;
    }
    
    /**
     * 최종 변경자를 반환한다.
     * 
     * @return 최종 변경자
     */
    public String getLstChgId() {
        return lstChgId;
    }
    
    /**
     * 최종 변경일시를 반환한다.
     * 
     * @return 최종 변경일시
     */
    public LocalDateTime getLstChgDt() {
        return lstChgDt;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (null != obj) {
	    	if (this == obj) {
				return true;
	    	}
	        if (obj instanceof DomainEntity) {
	            return this.getUid().equals(((DomainEntity<?>) obj).getUid());
	        }
	        if (obj instanceof DomainIdentity) {
	            return this.getUid().equals(obj);
	        }
    	}
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getUid().hashCode();
    }
    
    @Override
    public String toString() {
        return this.getUid().toString();
    }
}
