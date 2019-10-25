package aims.user.domain;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.*;

/**
 * 사용자 도메인 엔티티 식별자 오브젝트이다.
 *
 * @author jjh
 */
@Getter
@ToString
@EqualsAndHashCode
@Access(AccessType.FIELD)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class UserIdentity implements Serializable {
    private static final long serialVersionUID = 1712652284741475659L;

    /**
     * 사용자 식별자
     */
    @NonNull
    @NotNull
    @Column(name = "user_id", length = 15, nullable = false)
    private String userId;
}
