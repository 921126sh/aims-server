package aims.user.api.dto;

import aims.core.api.transferobject.BaseRequestDto;
import aims.core.infrastructure.constant.UserDiv;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 사용자 요청 데이터이다.
 * 
 * @author sh
 */
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest extends BaseRequestDto {
    private static final long serialVersionUID = -6204119528714772034L;

    /**
     * 사용자 식별자
     */
    @Getter
    @Setter
    private String userId;

    /**
     * 사용자 명
     */
    @Getter
    @Setter
    private String userNm;
    
    /**
     * 패스워드 
     */
    @Setter
    @Getter
    private String pwd;
}
