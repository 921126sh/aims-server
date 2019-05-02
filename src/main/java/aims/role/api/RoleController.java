package aims.role.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import aims.role.api.dto.RoleRequest;
import aims.role.api.dto.RoleResponse;
import aims.role.application.ChangeRoleService;
import aims.role.application.RetrieveRoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 역할에 대한 REST 컨트롤러이다.
 * 
 * @author JH
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    
    /**
     * 역할을 조회하는 서비스
     */
    @Autowired
    private RetrieveRoleService retrieveRoleService;
    
    /**
     * 역할을 변경하는 서비스
     */
    @Autowired
    private ChangeRoleService changeRoleService;
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @ApiOperation(value = "역할 목록을 조회한다.", nickname = "retrieveRoleList")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = RoleResponse.class) })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody HttpEntity<Resources<RoleResponse>> retrieveRoleList() {
        ResponseEntity<Resources<RoleResponse>> responseEntity;
        
        try {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveRoleList ---- [ start ]");
            }
            
            // 전체 역할 목록을 조회한다.
            List<RoleResponse> responses = retrieveRoleService.retrieveRoleList();
            
            // 응답(내용없음)을 설정한다.
            if (responses.isEmpty()) {
                responseEntity = new ResponseEntity<Resources<RoleResponse>>(HttpStatus.NO_CONTENT);
            }
            else {
                Resources<RoleResponse> asb = new Resources<RoleResponse>(responses, linkTo(methodOn(RoleController.class).retrieveRoleList()).withSelfRel());
                
                // 응답(처리성공)을 설정한다.
                responseEntity = new ResponseEntity<Resources<RoleResponse>>(asb, HttpStatus.OK);
            }
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveRoleList ---- [ end ]");
            }
        }
        
        return responseEntity;
    }
    
    @ApiOperation(value = "특정 역할을 조회한다.", nickname = "retrieveRole")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = RoleResponse.class) })
    @RequestMapping(value = "/{role-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<RoleResponse> retrieveRole(@PathVariable("role-id") String roleId) {
        ResponseEntity<RoleResponse> responseEntity;
        
        try {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveRole ---- [ start ]");
            }
            
            // 특정 역할을 조회을 요청한다.
            RoleResponse response = retrieveRoleService.retrieveRole(roleId);
            
            // 응답(처리성공)을 설정한다.
            responseEntity = new ResponseEntity<RoleResponse>(response, HttpStatus.OK);
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveRole ---- [ end ]");
            }
        }
        
        return responseEntity;
    }
    
    @ApiOperation(value = "신규 역할을 등록한다.", nickname = "registerRole")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success", response = Void.class) })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody HttpEntity<Void> registerRole(@RequestBody RoleRequest roleRequest) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("registerRole ---- [ start ]");
            }
            
            // 신규 역할을 등록한다.
            changeRoleService.registerRole(roleRequest);
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("registerRole ---- [ end ]");
            }
        }
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "특정 역할을 변경한다.", nickname = "modifyRole")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{role-id}", method = RequestMethod.PUT)
    public @ResponseBody HttpEntity<Void> modifyRole(@PathVariable("role-id") String roleId, @RequestBody RoleRequest roleRequest) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("modifyRole ---- [ start ]");
            }
            
            // 특정 역할을 변경한다.
            changeRoleService.modifyRole(roleId, roleRequest);
            // 응답(처리성공)을 설정한다.
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("modifyRole ---- [ end ]");
            }
        }
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "특정 역할을 삭제한다.", nickname = "deleteRole")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{role-id}", method = RequestMethod.DELETE)
    public @ResponseBody HttpEntity<Void> deleteRole(@PathVariable("role-id") String roleId) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("deleteRole ---- [ start ]");
            }
            
            // 특정 역할을 삭제한다.
            changeRoleService.deleteRole(roleId);
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("deleteRole ---- [ end ]");
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
