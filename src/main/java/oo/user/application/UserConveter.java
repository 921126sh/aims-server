package oo.user.application;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import oo.user.api.dto.UserResponse;
import oo.user.domain.User;

/**
 * 사용자 어그리게이션 TO객체 변환기이다.
 * 
 * @author sh
 */
@Component
public class UserConveter {
    public UserResponse convert(User user) {
    	UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        
        return response;
    }
}
