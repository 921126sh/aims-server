package aims.role.application;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import aims.role.api.dto.RoleResponse;
import aims.role.domain.Role;

/**
 * 역할 어그리게이션 TO객체 변환기이다.
 * 
 * @author JH
 */
@Component
public class RoleConveter {
    public RoleResponse convert(Role role) {
        RoleResponse response = new RoleResponse();
        BeanUtils.copyProperties(role, response);
        
        return response;
    }
}
