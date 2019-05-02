package aims.role.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import aims.core.infrastructure.exception.Errors.RolErrCd;
import aims.core.infrastructure.sysauth.SystemInfo;
import aims.core.infrastructure.util.CheckUtil;
import aims.role.api.dto.RoleRequest;
import aims.role.domain.Role;
import aims.role.domain.RoleAwareService;

/**
 * 역할 변경 서비스
 * 
 * @author JH
 */
@Service
@Transactional
public class ChangeRoleService {
    
    /**
     * 역할 도메인 서비스
     */
    @Autowired
    private RoleAwareService roleAwareService;
    
    /**
     * 시스템정보
     */
    @Autowired
    private SystemInfo systemInfo;
    
    /**
     * 신규 역할을 등록한다.
     * 
     * @param request 역할 요청 데이터
     */
    public void registerRole(RoleRequest request) {
        CheckUtil.checkArgumentNullOrEmpty(request.getRoleId(), RolErrCd.EBROLS005.name());
        
        Role role = Role.of(request.getRoleId(), request.getRoleNm(), request.getRoleDesc());
        roleAwareService.newSave(role);
    }
    
    /**
     * 특정 역할을 변경한다.
     * 
     * @param roleId 역할 식별자
     * @param request 역할 요청 데이터
     */
    public void modifyRole(String roleId, RoleRequest request) {
        CheckUtil.checkArgumentNullOrEmpty(Lists.newArrayList(roleId, request.getRoleId()), RolErrCd.EBROLS006.name());
        
        Role role = roleAwareService.find(roleId);
        roleAwareService.checkEditDuplicate(roleId, request.getRoleId());
        role.modify(request, systemInfo);
    }
    
    /**
     * 특정 역할을 삭제한다.
     * 
     * @param roleId 역할 식별자
     */
    public void deleteRole(String roleId) {
        CheckUtil.checkArgumentNullOrEmpty(roleId, RolErrCd.EBROLS007.name());
        
        Role role = roleAwareService.find(roleId);
        roleAwareService.delete(role);
    }
    
}
