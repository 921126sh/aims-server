package aims.core.api;

import aims.core.infrastructure.constant.EnvCd;
import aims.core.infrastructure.util.CheckUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Fever 어플리케이션에 대한 컨트롤러이다.
 *
 * @author jjh
 */
@RestController
@RequestMapping("/app")
@Slf4j
@NoArgsConstructor
public class AppController {
    /**
     * 스프링 프래임워크 환경정보
     */
    @Autowired
    private Environment environment;
    
    /**
     * 매니져 릴리즈 디렉토리
     */
    @Value("${fever.common.release-dir}")
    private String ROOT_FILE_PATH;
    
    /**
     * 서버 버전정보
     */
    @Value("${ks.info.version}")
    private String SERVER_VERSION;

    @ApiOperation(value = "어플리케이션 서버정보를 조회한다.", nickname = "retrieveServerInfo")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Success", response = Void.class) })
    @RequestMapping(value = "/server/info", method = RequestMethod.GET)
    public String retrieveServerInfo() {
        // 현재 환경코드를 구한다.
        EnvCd curEnvCd = EnvCd.codeOf(CheckUtil.nvl(environment.getActiveProfiles()[0], EnvCd.Production.getCode()));

        // 어플리케이션 서버정보를 담은 HTML를 작성한다.
        StringBuilder appInfo = new StringBuilder();
        appInfo.append("<html lang=\"ko\">");
        appInfo.append("<head><meta charset=\"UTF-8\"/></head>");
        appInfo.append("<body style=\"font-size:25px;\">");
        appInfo.append("<div style=\" position:absolute; border:black 2px solid; margin:auto; top:0; right:0; bottom:0; left:0; width:50%; height:50%;\">");
        appInfo.append("<div>");
        appInfo.append("<h2 style=\"text-align: center;\">Welcome Koob Store !</h2>");
        appInfo.append("</div>");
        appInfo.append("<ul>");
        appInfo.append("<li><b>Version</b> : ").append(SERVER_VERSION).append("</li>");
        appInfo.append("<li><b>Profile</b> : ").append(String.format("%s(%s)", curEnvCd.getTitle(), curEnvCd.name())).append("</li>");
        appInfo.append("</ul>");
        appInfo.append("</div>");
        appInfo.append("</body>");
        appInfo.append("</html>");
        
        return appInfo.toString();
    }
}