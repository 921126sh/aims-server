package aims.role.api.dto;

import aims.core.api.transferobject.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 역할 응답 데이터이다.
 * 
 * @author JH
 */
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse extends BaseResponseDto {
    private static final long serialVersionUID = 7914484958111196035L;

    /**
     * 원 역할 식별자를 반환한다.
     * 
     * @return 원 역할 식별자
     */
    public String getOrgRoleId() {
        return roleId;
    }
    
    /**
     * 역할 식별자
     */
    @Getter
    @Setter
    private String roleId;
    
    /**
     * 역할 명
     */
    @Getter
    @Setter
    private String roleNm;
    
    /**
     * 역할설명
     */
    @Getter
    @Setter
    private String roleDesc;
}
