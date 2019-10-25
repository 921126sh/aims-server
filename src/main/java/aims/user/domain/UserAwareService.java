package aims.user.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aims.core.infrastructure.exception.Errors.UsrErrCd;
import aims.user.infrastructure.exception.UserDuplicateException;
import aims.user.infrastructure.exception.UserNotFoundException;

/**
 * 사용자 도메인 서비스이다. 
 * 
 * @author sh
 */
@Service
public class UserAwareService {
    /**
     * 사용자 도메인 엔티티 레파지토리
     */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * 신규 사용자를 등록한다.
     * 
     * @param user 사용자
     */
    public void newSave(User user) {
        checkDuplicate(user.getIdentity().getUserId());
        save(user);
    }
    
    /**
     * 신규 사용자를 등록한다.
     * 
     * @param user 사용자
     */
    public void save(User user) {
        userRepository.save(user);
    }

	/**
	 * 특정 사용자를 삭제한다.
	 * 
	 * @param user 사용자
	 */
	public void delete(User user) {
		userRepository.delete(user);
	}
    
    /**
     * 특정 사용자를 조회한다.
     * 
     * @param userId 사용자 식별자
     * @return 사용자
     */
    public User find(String userId) {
    	// EBUSRS002 : 사용자 식별자(%s)에 해당되는 사용자가 존재하지 않습니다.
        return find(userId, UsrErrCd.EBUSRS002.name());
    }
    
    /**
     * 특정 사용자를 조회한다.
     * 
     * @param userId 사용자 식별자
     * @param errCd 오류코드
     * @return 사용자
     */
    public User find(String userId, String errCd) {
        User user = userRepository.findByIdUserId(userId);
        
        if (null == user) {
            throw new UserNotFoundException(errCd, new Object[]{userId});
        }
        
        return user;
    }
    
    /**
     * 동일한 객체가 존재하는지 확인한다.
     * 
     * @param userId 사용자 식별자
     */
    public void checkDuplicate(String userId) {
    	// EBUSRS003 : 동일한 사용자 식별자(%s)가 이미 존재합니다.
        checkDuplicate(userId, UsrErrCd.EBUSRS003.name());
    }
    
    /**
     * 동일한 사용자가 존재하는지 확인한다.
     * 
     * @param userId 사용자 식별자
     * @param errCd 에러코드
     */
    public void checkDuplicate(final String userId, String errCd) {
    	final User user = userRepository.findByIdUserId(userId);
        
        if (null != user) {
            throw new UserDuplicateException(String.format(errCd, userId));
        }
    }
    
    /**
     * 변경할 새로운 사용자 식별자와 동일한 개체가 존재하는지 확인한다.
     * 
     * @param userId 사용자 식별자
     * @param newUserId 새로운 사용자 식별자
     */
    public void checkEditDuplicate(String userId, String newUserId) {
        //변경할 식별자와 새로운 식별자가 다른 경우, 새로운 식별자가 존재하는지 검사한다. 
        if (!userId.equals(newUserId)) {
        	// EBUSRS004 : 새로운 사용자 식별자(%s)와 동일한 객체가 이미 존재합니다.
            checkDuplicate(newUserId, UsrErrCd.EBUSRS004.name());
        }
    }

    /**
     * 전체 사용자 목록을 조회한다.
     * 
     * @return 사용자 목록
     */
    public List<User> findAll() {
    	return userRepository.findAll();
    }
}
