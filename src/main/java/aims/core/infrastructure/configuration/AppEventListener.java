package aims.core.infrastructure.configuration;

import aims.core.infrastructure.constant.EnvCd;
import aims.core.infrastructure.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * 어플리케이션 서버 시작과 종료 이벤트를 수신하는 서비스이다.
 * 
 * @author jjh
 */
@Service
@Slf4j
public class AppEventListener {
    /**
     * 스프링 환경 제공자
     */
    @Autowired
    private Environment environment;
    
    /**
     * 서버 버전정보
     */
    @Value("${ks.info.version}")
    private String SERVER_VERSION;
    
    /**Ø
     * 어플리케이션 서버 시작 시 발생하는 이벤트 리스너이다.
     * 
     * @param event 이벤트
     */
    @EventListener
    public void onStartUp(ApplicationReadyEvent event) {
        // 현재 환경코드를 구한다.
        EnvCd curEnvCd = EnvCd.codeOf(CheckUtil.nvl(environment.getActiveProfiles()[0], EnvCd.Production.getCode()));
        
        // 어플리케이션 서버정보를 출력한다.
        if (log.isInfoEnabled()) {
            log.info("######################################       Koob Store SERVER INFORMATION       ######################################");
            log.info(String.format("#    Version : %s", SERVER_VERSION));
            log.info(String.format("#    Profile : %s", String.format("%s(%s)", curEnvCd.getTitle(), curEnvCd.name())));
            log.info("#################################################################################################################");
        }
    }
    
    /**
     * 어플리케이션 서버 종료 시 발생하는 이벤트 리스너이다.
     * 
     * @param event 이벤트
     */
    @EventListener
    public void onShutDown(ApplicationReadyEvent event) {
    }
}