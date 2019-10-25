package aims.core.api.transferobject;

import aims.core.domain.Config;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/check-update")
public class CheckForUpdateApplicationController {
    @Autowired
    Config config;

    /**
     * JSON Mapper
     */
    private final ObjectMapper jsonObjMapper = new ObjectMapper();

    @ApiOperation(value = "어플리케이션의 버전 업데이트 여부를 제공한다.", nickname = "checkForUpdatedApplication")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{app-name}", method = RequestMethod.GET)
    public ResponseEntity<CheckForUpdateDto> checkForUpdatedApplication(HttpServletResponse response, @PathVariable("app-name") String appName) throws IOException {

        return ResponseEntity.ok(getCheckFileInfo(appName));
    }

    /**
     * 어플리케이션의 버전정보를 반환한다.
     *
     * @param appName 어플리케이션이름
     * @return 버전업데이트 VO
     */
    private CheckForUpdateDto getCheckFileInfo(String appName) {
        CheckForUpdateDto checkForUpdateDto = null;

        try {
            //파일 내용을 UTF-8롤 인코딩하여 읽어 들인다.
            checkForUpdateDto = jsonObjMapper.readValue(new InputStreamReader(
                    new FileInputStream("D:\\temp\\aims-server\\downStore" + File.separator + appName + File.separator + "Release.txt"), "UTF-8"), CheckForUpdateDto.class);
        }
        catch (JsonParseException jpe) {
            throw new RuntimeException("버전정보 파일을 분석 하는 중 오류가 발생하였습니다.", jpe);
        }
        catch (JsonMappingException jme) {
            throw new RuntimeException("버전정보 파일을 OBJECT로 변환 하는 중 오류가 발생하였습니다.", jme);
        }
        catch (IOException ie) {
            throw new RuntimeException("버전정보 파일을 읽던 중 오류가 발생하였습니다.", ie);
        }

        return checkForUpdateDto;
    }
}
