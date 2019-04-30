package oo.core.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

/**
 * 추상화된 최상위 도메인 엔터티 식별자 오브젝트이다.
 * 
 * @author sh
 */
@MappedSuperclass
public abstract class DomainIdentity implements Serializable {
    private static final long serialVersionUID = -7077851376132633343L;
    
    /**
     * 기본 생성자로서 내부적으로 도메인 엔터티 UID 초기화한다.
     */
    public DomainIdentity() {
    }
    
    /**
     * 도메인 엔터티 UID 값을 반환한다.
     * 
     * @return 도메인 엔터티 식별자 값
     */
    public abstract String getValue();
    
    @Override
    public boolean equals(Object obj) {
    	if (null != obj) {
	    	if (this == obj) {
				return true;
	    	}
	        if (obj instanceof DomainEntity) {
	            return this.getValue().equals(((DomainEntity<?>)obj).getUid().getValue());
	        }
	        if (obj instanceof DomainIdentity) {
	            return this.getValue().equals(((DomainIdentity)obj).getValue());
	        }
	    }
        
        return false;
    }
    
    @Override
    public String toString() {
        return getValue();
    }
    
    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
