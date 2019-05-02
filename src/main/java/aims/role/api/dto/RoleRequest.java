package aims.role.api.dto;

import aims.core.api.transferobject.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 역할 요청 데이터이다.
 * 
 * @author JH
 */
@AllArgsConstructor
@NoArgsConstructor
public class RoleRequest extends BaseRequestDto {
    private static final long serialVersionUID = 5161465198928106279L;

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
