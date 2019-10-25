package aims.user.domain;

/**
 * 함수 도메인 엔티티 레파지토리 커스텀 인터페이스 이다.
 *
 * @author jjh
 */
public interface UserRepositoryCustom {
    /**
     * 사용자 식별자에 해당하는 사용자를 반환한다.
     *
     * @param userId 사용자 식별자
     * @return 사용자 식별자에 해당하는 사용자
     */
    User findByIdUserId(String userId);
}