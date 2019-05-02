package aims.menu.api;

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

import aims.menu.api.dto.MnuRequest;
import aims.menu.api.dto.MnuResponse;
import aims.menu.application.ChangeMnuService;
import aims.menu.application.RetrieveMnuService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 메뉴에 대한 REST 컨트롤러이다.
 * 
 * @author JH
 */
@RestController
@RequestMapping("/mnus")
public class MnuController {
    private static final Logger logger = LoggerFactory.getLogger(MnuController.class);
    
    /**
     * 메뉴를 조회하는 서비스
     */
    @Autowired
    private RetrieveMnuService retrieveMnuService;
    
    /**
     * 메뉴를 변경하는 서비스
     */
    @Autowired
    private ChangeMnuService changeMnuService;
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @ApiOperation(value = "메뉴 목록을 조회한다.", nickname = "retrieveMnuList")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MnuResponse.class) })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody HttpEntity<Resources<MnuResponse>> retrieveMnuList() {
        ResponseEntity<Resources<MnuResponse>> responseEntity;
        
        try {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveMnuList ---- [ start ]");
            }
            
            // 전체 메뉴 목록을 조회한다.
            List<MnuResponse> responses = retrieveMnuService.retrieveMnuList();
            
            // 응답(내용없음)을 설정한다.
            if (responses.isEmpty()) {
                responseEntity = new ResponseEntity<Resources<MnuResponse>>(HttpStatus.NO_CONTENT);
            }
            else {
                Resources<MnuResponse> asb = new Resources<MnuResponse>(responses, linkTo(methodOn(MnuController.class).retrieveMnuList()).withSelfRel());
                
                // 응답(처리성공)을 설정한다.
                responseEntity = new ResponseEntity<Resources<MnuResponse>>(asb, HttpStatus.OK);
            }
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveMnuList ---- [ end ]");
            }
        }
        
        return responseEntity;
    }
    
    @ApiOperation(value = "특정 메뉴를 조회한다.", nickname = "retrieveMnu")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MnuResponse.class) })
    @RequestMapping(value = "/{mnu-id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody HttpEntity<MnuResponse> retrieveMnu(@PathVariable("mnu-id") String mnuId) {
        ResponseEntity<MnuResponse> responseEntity;
        
        try {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveMnu ---- [ start ]");
            }
            
            // 특정 메뉴를 조회를 요청한다.
            MnuResponse response = retrieveMnuService.retrieveMnu(mnuId);
            
            // 응답(처리성공)을 설정한다.
            responseEntity = new ResponseEntity<MnuResponse>(response, HttpStatus.OK);
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("retrieveMnu ---- [ end ]");
            }
        }
        
        return responseEntity;
    }
    
    @ApiOperation(value = "신규 메뉴를 등록한다.", nickname = "registerMnu")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success", response = Void.class) })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody HttpEntity<Void> registerMnu(@RequestBody MnuRequest mnuRequest) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("registerMnu ---- [ start ]");
            }
            
            // 신규 메뉴를 등록한다.
            changeMnuService.registerMnu(mnuRequest);
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("registerMnu ---- [ end ]");
            }
        }
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "특정 메뉴를 변경한다.", nickname = "modifyMnu")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{mnu-id}", method = RequestMethod.PUT)
    public @ResponseBody HttpEntity<Void> modifyMnu(@PathVariable("mnu-id") String mnuId, @RequestBody MnuRequest mnuRequest) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("modifyMnu ---- [ start ]");
            }
            
            // 특정 메뉴를 변경한다.
            changeMnuService.modifyMnu(mnuId, mnuRequest);
            // 응답(처리성공)을 설정한다.
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("modifyMnu ---- [ end ]");
            }
        }
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @ApiOperation(value = "특정 메뉴를 삭제한다.", nickname = "deleteMnu")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "Success", response = Void.class) })
    @RequestMapping(value = "/{mnu-id}", method = RequestMethod.DELETE)
    public @ResponseBody HttpEntity<Void> deleteMnu(@PathVariable("mnu-id") String mnuId) {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("deleteMnu ---- [ start ]");
            }
            
            // 특정 메뉴를 삭제한다.
            changeMnuService.deleteMnu(mnuId);
        }
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("deleteMnu ---- [ end ]");
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
