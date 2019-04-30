package oo.user.domain;

import oo.core.domain.DomainRepository;

/**
 * 사용자 도메인 엔티티 레파지토리 최상위 인터페이스 이다.
 * 
 * @author itt
 */
public interface UserRepository extends DomainRepository<User, UserIdentity>  {
	User findByUserId(String userId);
}