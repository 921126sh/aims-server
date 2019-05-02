package aims.menu.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import aims.core.domain.DomainIdentity;
import aims.core.infrastructure.util.GeneratorKeyValue;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 메뉴 엔티티 PK 오브젝트이다.
 * 
 * @author JH
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Access(AccessType.FIELD)
@Embeddable
public class MnuIdentity extends DomainIdentity {
    private static final long serialVersionUID = 6163398736805769423L;
    
    @Column(name = "MNU_UID", length = 32, nullable = false, insertable = false, updatable = false)
    private String val = GeneratorKeyValue.genUidValue();
    
    @Override
    public String getValue() {
        return val;
    }
}
