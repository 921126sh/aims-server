package oo.core.api.transferobject;

import java.io.Serializable;

import org.pojomatic.Pojomatic;

/**
 * 추상화된 최상위 요청 DTO 오브젝트이다.
 * 
 * @author sh
 */
public abstract class BaseRequestDto  implements Serializable {
    private static final long serialVersionUID = 5184330279419775451L;
    
    /**
     * 기본 생성자이다.
     */
    public BaseRequestDto() {
    }
    
    @Override
    public boolean equals(Object obj) {
        return Pojomatic.equals(this, obj);
    }
    
    @Override
    public int hashCode() {
        return Pojomatic.hashCode(this);
    }
    
    @Override
    public String toString() {
        return Pojomatic.toString(this);
    }
}
