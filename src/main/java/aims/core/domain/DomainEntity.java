package aims.core.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 추상화된 최상위 도메인 엔터티 오브젝트이다.
 *
 * @author sh
 * @param <IDENTITY> 엔터티 UID 유형
 */
@Getter
@ToString
@NoArgsConstructor
@MappedSuperclass
public abstract class DomainEntity<IDENTITY extends Serializable> implements Serializable {
	private static final long serialVersionUID = 8581525848268817615L;

	/**
     * 최초 등록자
     */
    @CreatedBy
    @Column(name = "fst_reg_id", length = 15, nullable = false, updatable = false)
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
    @Column(name = "lst_chg_id", length = 15, nullable = false)
    protected String lstChgId;

    /**
     * 최종 변경일시
     */
    @LastModifiedDate
    @Column(name = "lst_chg_dt", nullable = false)
    protected LocalDateTime lstChgDt = LocalDateTime.now();

    /**
     * 도메인 엔터티 식별자를 반환한다.
     *
     * @return 도메인 엔티티 식별자
     */
    public abstract IDENTITY getIdentity();

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

    @Override
    public boolean equals(Object obj) {
    	if (null != obj) {
	    	if (this == obj) {
				return true;
	    	}
	        if (obj instanceof DomainEntity) {
	            return this.getIdentity().equals(((DomainEntity<?>) obj).getIdentity());
	        }
    	}

        return false;
    }

    @Override
    public int hashCode() {
        return this.getIdentity().hashCode();
    }

    @Override
    public String toString() {
        return this.getIdentity().toString();
    }
}
