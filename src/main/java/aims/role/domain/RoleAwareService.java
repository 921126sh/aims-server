package aims.role.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aims.core.infrastructure.exception.Errors.RolErrCd;
import aims.role.infrastructure.exception.RoleDuplicateException;
import aims.role.infrastructure.exception.RoleNotFoundException;

/**
 * 역할 도메인 서비스이다.
 * 
 * @author JH
 */
@Service
public class RoleAwareService {
    /**
     * 역할 도메인 엔티티 레파지토리
     */
    @Autowired
    private RoleRepository roleRepository;
    
    /**
     * 신규 역할을 등록한다.
     * 
     * @param role 역할
     */
    public void newSave(Role role) {
        checkDuplicate(role.getRoleId());
        save(role);
    }
    
    /**
     * 신규 역할을 등록한다.
     * 
     * @param role 역할
     */
    public void save(Role role) {
        roleRepository.save(role);
    }
    
    /**
     * 특정 역할을 삭제한다.
     * 
     * @param role 역할
     */
    public void delete(Role role) {
        roleRepository.delete(role);
    }
    
    /**
     * 특정 역할을 조회한다.
     * 
     * @param roleId 역할 식별자
     * @return 역할
     */
    public Role find(String roleId) {
        return find(roleId, RolErrCd.EBROLS001.name());
    }
    
    /**
     * 특정 역할을 조회한다.
     * 
     * @param roleId 역할 식별자
     * @param errCd 오류코드
     * @return 역할
     */
    public Role find(String roleId, String errCd) {
        Role role = roleRepository.findByRoleId(roleId);
        
        if (null == role) {
            throw new RoleNotFoundException(errCd, new Object[] { roleId });
        }
        
        return role;
    }
    
    /**
     * 동일한 객체가 존재하는지 확인한다.
     * 
     * @param roleId 역할 식별자
     */
    public void checkDuplicate(String roleId) {
        checkDuplicate(roleId, RolErrCd.EBROLS002.name());
    }
    
    /**
     * 동일한 역할가 존재하는지 확인한다.
     * 
     * @param roleId 역할 식별자
     * @param errCd 에러코드
     */
    public void checkDuplicate(final String roleId, String errCd) {
        final Role role = roleRepository.findByRoleId(roleId);
        
        if (null != role) {
            throw new RoleDuplicateException(String.format(errCd, roleId));
        }
    }
    
    /**
     * 변경할 새로운 역할 식별자와 동일한 개체가 존재하는지 확인한다.
     * 
     * @param roleId 역할 식별자
     * @param newRoleId 새로운 역할 식별자
     */
    public void checkEditDuplicate(String roleId, String newRoleId) {
        // 변경할 식별자와 새로운 식별자가 다른 경우, 새로운 식별자가 존재하는지 검사한다.
        if (!roleId.equals(newRoleId)) {
            checkDuplicate(newRoleId, RolErrCd.EBROLS002.name());
        }
    }
    
    /**
     * 전체 역할 목록을 조회한다.
     * 
     * @return 역할 목록
     */
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
