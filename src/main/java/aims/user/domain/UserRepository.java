package aims.user.domain;

import aims.core.domain.DomainRepository;

/**
 * 사용자 도메인 엔티티 레파지토리 최상위 인터페이스 이다.
 *
 * @author jjh
 */
public interface UserRepository extends DomainRepository<User, UserIdentity>, UserRepositoryCustom {
}