package aims.core.infrastructure.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("======================================          START         ======================================");
            log.info(String.format("%s %s", request.getRequestURI(), request.getMethod()));
        }
        return super.preHandle(request, response, handler);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (log.isInfoEnabled()) {
            log.info("======================================          END         ======================================");
            log.info(String.format("%s %s", request.getRequestURI(), request.getMethod()));
        }
        
        super.afterCompletion(request, response, handler, ex);
    }
}