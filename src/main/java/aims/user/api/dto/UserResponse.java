package aims.user.api.dto;

import aims.core.api.transferobject.BaseResponseDto;
import lombok.*;

/**
 * 사용자 응답 데이터이다.
 * 
 * @author sh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseResponseDto {
    private static final long serialVersionUID = -2958788788940304386L;

    /**
     * 사용자 식별자
     */
    private String userId;

    /**
     * 사용자 명
     */
    private String userNm;

    /**
     * 사용자 비밀번호
     */
    private String userPwd;
}
