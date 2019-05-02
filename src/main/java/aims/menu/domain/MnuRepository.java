package aims.menu.domain;

import aims.core.domain.DomainRepository;

/**
 * 메뉴 도메인 엔티티 레파지토리 최상위 인터페이스 이다.
 * 
 * @author JH
 */
public interface MnuRepository extends DomainRepository<Mnu, MnuIdentity> {
    Mnu findByMnuId(String mnuId);
}