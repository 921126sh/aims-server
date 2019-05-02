package aims.core.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aims.user.api.dto.UserResponse;
import aims.user.application.RetrieveUserService;
import aims.user.infrastructure.exception.UserNotFoundException;

/**
 * 사용자식별자를 파라메터로 사용자 정보와 사용자 역할정보를 가져오는 서비스이다. (DB를 검색하거나 LDAP, openID를 검색하여 사용자 인증정보를 조회한다.)
 * 
 * @author leppk
 */
@Service
public class AuthenticationService  implements UserDetailsService {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);
    
    /**
     * 사용자를 조회하는 서비스
     */
    @Autowired
    private RetrieveUserService retrieveUserService;
        
    /**
     * 인증된 사용자정보를 반환한다.
     * 
     * @param userId 사용자식별자
     * @return 인증된 사용자정보
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	UserResponse userVo;
        // 사용자활성화
        boolean enabled = true;
        // 사용자계정 만료이전 여부
        boolean accountNonExpired = true;
        // 사용자인증말료이전여부
        boolean credentialsNonExpired = true;
        // 사용자계정미제한 여부
        boolean accountNonLocked = true;
        
    	try {
    		userVo = retrieveUserService.retrieveUser(userId);
    	}
    	catch (UserNotFoundException unfe) {
    		throw new UsernameNotFoundException("Not found user id : " + userId, unfe);
    	}

    	return new aims.core.infrastructure.sysauth.AuthUserVo(userId, userVo.getUserNm(), userVo.getPwd(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked);
    }
}