package aims.role.domain;

import aims.core.domain.DomainRepository;

/**
 * 역할 도메인 엔티티 레파지토리 최상위 인터페이스 이다.
 * 
 * @author JH
 */
public interface RoleRepository extends DomainRepository<Role, RoleIdentity> {
    Role findByRoleId(String mnuId);
}