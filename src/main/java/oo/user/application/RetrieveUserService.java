package oo.user.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import oo.core.infrastructure.exception.Errors.UsrErrCd;
import oo.core.infrastructure.util.CheckUtil;
import oo.user.api.dto.UserResponse;
import oo.user.domain.User;
import oo.user.domain.UserAwareService;

@Service
@Transactional
public class RetrieveUserService {
    
    /**
     * 사용자 도메인 서비스
     */
    @Autowired
    private UserAwareService userAwareService;
    
    /**
     * 사용자 엔티티 <--> TO 객체 변환기
     */
    @Autowired
    private UserConveter userConveter;
    
    /**
     * 사용자 목록을 조회한다.
     * 
     * @return 사용자 목록
     */
    public List<UserResponse> retrieveUserList() {
        List<User> users = userAwareService.findAll();
        
        return Lists.transform(users, new Function<User, UserResponse>() {
            public UserResponse apply(User user) {
                return userConveter.convert(user);
            }
        });
    }
    
    /**
     * 사용자 식별자에 해당되는 사용자을 조회한다.
     * 
     * @param userId 사용자 식별자
     * @return 사용자 응답 데이터
     */
    public UserResponse retrieveUser(String userId) {
    	// EBUSRS005 : 조회할 사용자 식별자(%s)가 입력되지 않았습니다.
        CheckUtil.checkArgumentNullOrEmpty(userId, UsrErrCd.EBUSRS005.name());
        
        return userConveter.convert(userAwareService.find(userId));
    }
}
