package oo.core.api.transferobject;

import java.io.Serializable;

import org.pojomatic.Pojomatic;
import org.springframework.hateoas.ResourceSupport;

/**
 * 추상화된 최상위 응답 DTO 오브젝트이다.
 * 
 * @author sh
 */
public abstract class BaseResponseDto extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 5184330279419775451L;
   
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
