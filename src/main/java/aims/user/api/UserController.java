package aims.user.api;

import aims.user.api.dto.UserRequest;
import aims.user.api.dto.UserResponse;
import aims.user.application.ChangeUserService;
import aims.user.application.RetrieveUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * 사용자에 대한 REST 컨트롤러이다.
 *
 * @author sh
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    /**
     * 사용자를 조회하는 서비스
     */
    @Autowired
    private RetrieveUserService retrieveUserService;

    /**
     * 사용자를 변경하는 서비스
     */
    @Autowired
    private ChangeUserService changeUserService;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation(value = "사용자 목록을 조회한다.", nickname = "getUserList")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class) })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody HttpEntity<Resources<UserResponse>> retrieveUserList() {
        ResponseEntity<Resources<UserResponse>> responseEntity;

        // 전체 사용자 목록을 조회한다.
        List<UserResponse> responses = retrieveUserService.retrieveUserList();

        if (responses.isEmpty()) {
            // 응답(내용없음)을 설정한다.
            responseEntity = new ResponseEntity<Resources<UserResponse>>(HttpStatus.NO_CONTENT);
        }
        else {
            Resources<UserResponse> responseAssemble = new Resources<UserResponse>(responses, linkTo(methodOn(UserController.class).retrieveUserList())
                    .withSelfRel());

            // 응답(처리성공)을 설정한다.
            responseEntity = new ResponseEntity<Resources<UserResponse>>(responseAssemble, HttpStatus.OK);
        }

        return responseEntity;
    }

    @ApiOperation(value = "특정 사용자를 조회한다.", nickname = "retrieveUser")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserResponse.class) })
    @RequestMapping(value = "/{user-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<UserResponse> retrieveUser(@PathVariable("user-id") String userId) {
        ResponseEntity<UserResponse> responseEntity;

        // 특정 사용자를 조회를 요청한다.
        UserResponse response = retrieveUserService.retrieveUser(userId);

        // 응답(처리성공)을 설정한다.
        responseEntity = new ResponseEntity<UserResponse>(response, HttpStatus.OK);

        return responseEntity;
    }

    @ApiOperation(value = "신규 사용자를 등록한다.", nickname = "registerUser")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success", response = Void.class) })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody HttpEntity<Void> registerUser(@RequestBody UserRequest userRequest) {
        ResponseEntity<Void> responseEntity;

        // 신규 사용자를 등록한다.
        changeUserService.registerUser(userRequest);

        responseEntity = new ResponseEntity<Void>(HttpStatus.CREATED);

        return responseEntity;
    }

    @ApiOperation(value = "특정 사용자를 변경한다.", nickname = "chgUserInfo")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{user-id}", method = RequestMethod.PUT)
    public @ResponseBody HttpEntity<Void> chgUserInfo(@PathVariable("user-id") String userId, @RequestBody UserRequest userRequest) {
        ResponseEntity<Void> responseEntity;

        // 특정 사용자를 변경한다.
        changeUserService.chgUserInfo(userId, userRequest);

        // 응답(처리성공)을 설정한다.
        responseEntity = new ResponseEntity<Void>(HttpStatus.CREATED);

        return responseEntity;
    }

    @ApiOperation(value = "특정 사용자를 삭제한다.", nickname = "deleteUser")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{user-id}", method = RequestMethod.DELETE)
    public @ResponseBody HttpEntity<Void> deleteUser(@PathVariable("user-id") String userId) {
        ResponseEntity<Void> responseEntity;

        // 특정 사용자를 삭제한다.
        changeUserService.deleteUser(userId);

        // 응답(처리성공)을 설정한다.
        responseEntity = new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

        return responseEntity;
    }
}
