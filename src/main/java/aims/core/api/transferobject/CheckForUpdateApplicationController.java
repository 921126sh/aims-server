package aims.core.api.transferobject;

import aims.core.domain.Config;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.util.List;

@RestController
@RequestMapping("/update")
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

    @ApiOperation(value = "어플리케이션의 버전 업데이트 파일 업로드 서비스를 제공한다.", nickname = "provideUploadApplication")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{app-name}/{version}/{file-name}", method = RequestMethod.POST)
    public @ResponseBody
    HttpEntity<Void> provideUploadApplication(
            @PathVariable("app-name") String appName, @PathVariable("version") String version, @PathVariable("file-name") String fileName,
            @RequestParam("file") MultipartFile file, @RequestParam("date") String date, @RequestParam("notableChanges") List<String> notableChanges,
            @RequestParam(value = "isRelease" , required = false, defaultValue = "true") String isRelease) throws IOException {
        ResponseEntity<Void> responseEntity;

        //파일명에 확장자가 누락되어서 파일객체의 원본파일명을 파일명으로 대체한다.
        fileName = file.getOriginalFilename();

        final String uploadFilePath = "D:\\temp\\aims-server\\downStore" + File.separator + appName + File.separator + version;
        if(file.isEmpty()) {
            throw new RuntimeException(String.format("Failed to upload(%s), because the file was empty", uploadFilePath  + File.separator + fileName));
        }

        try {
            //1.등록 버전의 디렉토리를 만든다.
            if (new File(uploadFilePath).exists()) {
                FileSystemUtils.deleteRecursively(new File(uploadFilePath));
            }

            if (!new File(uploadFilePath).mkdirs()){
                throw new RuntimeException(String.format("Failed to create upload directory", uploadFilePath));
            }

            //2.업로드 파일 등록
            FileCopyUtils.copy(file.getBytes(), new File(uploadFilePath  + File.separator + fileName));

            //3.Release.txt를 UTF-8롤 인코딩하여 생성하고 생성된 폴더를 루트 폴더에도 복사시킨다.
            File releaseFile = new File(uploadFilePath + File.separator + "Release.txt");

            //어플리케이션 버전 업데이트 정보를 생성한다.
            CheckForUpdateDto checkForUpdateDto = new CheckForUpdateDto();
            checkForUpdateDto.setVersion(version);
            checkForUpdateDto.setFileName(fileName);
            checkForUpdateDto.setDate(date);
            checkForUpdateDto.setNotableChanges(notableChanges);

            String releaseInfoJson =  jsonObjMapper.writeValueAsString(checkForUpdateDto);
//            if (logger.isDebugEnabled()) {
//                logger.debug(String.format("Upload ReleaseFile(%s) Contents : %s", releaseFile, releaseInfoJson));
//            }

            FileCopyUtils.copy(releaseInfoJson, new OutputStreamWriter(new FileOutputStream(releaseFile), "UTF-8"));

            //생성한 파일 루트폴더에도 생성
            if (Boolean.valueOf(isRelease)) {
                FileCopyUtils.copy(releaseFile, new File("D:\\temp\\aims-server\\downStore" + File.separator + appName + File.separator + "Release.txt"));
            }

            //4. 등록 성공 응답처리 한다.
            responseEntity = new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Throwable t) {
            throw new RuntimeException(String.format("Failed to upload(%s) ", uploadFilePath  + File.separator + fileName), t);
        }

        return responseEntity;
    }

    @ApiOperation(value = "어플리케이션의 버전 업데이트 파일 다운로드 서비스를 제공한다.", nickname = "provideDownloadApplication")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{app-name}/{version}/{file-name}", method = RequestMethod.GET)
    public void provideDownloadApplication(HttpServletResponse response, @PathVariable("app-name") String appName, @PathVariable("version") String version,
                                           @PathVariable("file-name") String fileName) throws IOException {

        // 1. 다운로드 컨텐츠 생성
        final File file = new File("D:\\temp\\aims-server\\downStore" + File.separator + appName + File.separator + version + File.separator + fileName);
        final String mimeType = URLConnection.guessContentTypeFromName(file.getName());

        // 2. request 객체에 컨텐츠 유형 설정하기.
        // 마임유형을 설정한다.
        response.setContentType(mimeType == null ? "application/octet-stream" : mimeType);
        response.setCharacterEncoding("UTF-8");

        // 파일의 이름을 헤더에 설정한다.
        response.setHeader("Content-Disposition", String.format("inline; filename=\"%s\"", file.getName()));

        // 파일의 길이를 설정한다.
        response.setContentLength((int) file.length());

        FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
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
