package aims.role.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import aims.core.infrastructure.exception.Errors.RolErrCd;
import aims.core.infrastructure.util.CheckUtil;
import aims.role.api.dto.RoleResponse;
import aims.role.domain.Role;
import aims.role.domain.RoleAwareService;

/**
 * 역할 조회 서비스
 * 
 * @author JH
 */
@Service
@Transactional
public class RetrieveRoleService {
    
    /**
     * 역할 도메인 서비스
     */
    @Autowired
    private RoleAwareService roleAwareService;
    
    /**
     * 역할 엔티티 <--> TO 객체 변환기
     */
    @Autowired
    private RoleConveter roleConveter;
    
    /**
     * 역할 목록을 조회한다.
     * 
     * @return 역할 목록
     */
    public List<RoleResponse> retrieveRoleList() {
        List<Role> roles = roleAwareService.findAll();
        
        return Lists.transform(roles, new Function<Role, RoleResponse>() {
            public RoleResponse apply(Role role) {
                return roleConveter.convert(role);
            }
        });
    }
    
    /**
     * 역할 식별자에 해당되는 역할을 조회한다.
     * 
     * @param roleId 역할 식별자
     * @return 역할 응답 데이터
     */
    public RoleResponse retrieveRole(String roleId) {
        CheckUtil.checkArgumentNullOrEmpty(roleId, RolErrCd.EBROLS004.name());
        
        return roleConveter.convert(roleAwareService.find(roleId));
    }
}
