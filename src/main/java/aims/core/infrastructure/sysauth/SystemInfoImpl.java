package aims.core.infrastructure.sysauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 시스템 정보을 제공하는 서비스 오브젝트이다.
 * 
 * @author itt
 */
@Service("systemInfo")
public class SystemInfoImpl implements SystemInfo {
    @Autowired
    private Environment environment;
    
    /**
     * 시스템정보의 기본 생성자이다.
     */
    public SystemInfoImpl() {
    }
    
    @Override
    public String getEnvCd() {
        return environment.getActiveProfiles()[0];
    }
    
    @Override
    public String getUserId() {
        return get().getUserId();
    }
    
    @Override
    public String getUserNm() {
        return get().getUserNm();
    }
    
    /**
     * 만약 쓰래드 컨텍스트가 달라 사용자인증이 없는 경우 가상의 인증정보를 생성하여 반환한다.
     * 
     * @return 가상의 인증정보
     */
    private AuthUserVo get() {
        AuthUserVo authUserVo;
        
        if (null == SecurityContextHolder.getContext().getAuthentication()) {
            authUserVo = new AuthUserVo("nouser", "nouser", "", true, true, true, true);
        }
        else {
            authUserVo = (AuthUserVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        
        return authUserVo;
    }
}
