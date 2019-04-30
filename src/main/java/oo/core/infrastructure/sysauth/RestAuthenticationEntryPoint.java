package oo.core.infrastructure.sysauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Spring Security에서는 Default 동작으로, Authentication Required일때, Login URL로 Redirect가 된다.
 * REST 에서는 Redirect가 불필요 하므로, 특정API에 인증 없이 Request 수신시, "UnAuthorized(401)" HTTP Response를 Return하도록 구현이 필요하다.
 * 
 * @author leppk
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
    
}
