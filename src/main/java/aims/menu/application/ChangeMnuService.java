package aims.menu.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import aims.core.infrastructure.exception.Errors.MnuErrCd;
import aims.core.infrastructure.sysauth.SystemInfo;
import aims.core.infrastructure.util.CheckUtil;
import aims.menu.api.dto.MnuRequest;
import aims.menu.domain.Mnu;
import aims.menu.domain.MnuAwareService;

/**
 * 메뉴 변경 서비스
 * 
 * @author JH
 */
@Service
@Transactional
public class ChangeMnuService {
    
    /**
     * 메뉴 도메인 서비스
     */
    @Autowired
    private MnuAwareService mnuAwareService;
    
    /**
     * 시스템정보
     */
    @Autowired
    private SystemInfo systemInfo;
    
    /**
     * 신규 메뉴을 등록한다.
     * 
     * @param request 메뉴 요청 데이터
     */
    public void registerMnu(MnuRequest request) {
        CheckUtil.checkArgumentNullOrEmpty(request.getMnuId(), MnuErrCd.EBMNUS005.name());
        
        Mnu mnu = Mnu.of(request.getMnuId(), request.getMnuNm(), request.getMnuCtnt(), request.getMnuSort());
        mnu.setMnuStyle(request.getMnuStyle());
        mnuAwareService.newSave(mnu);
    }
    
    /**
     * 특정 메뉴를 변경한다.
     * 
     * @param mnuId 메뉴 식별자
     * @param request 메뉴 요청 데이터
     */
    public void modifyMnu(String mnuId, MnuRequest request) {
        CheckUtil.checkArgumentNullOrEmpty(Lists.newArrayList(mnuId, request.getMnuId()), MnuErrCd.EBMNUS006.name());
        
        Mnu mnu = mnuAwareService.find(mnuId);
        mnuAwareService.checkEditDuplicate(mnuId, request.getMnuId());
        mnu.modify(request, systemInfo);
    }
    
    /**
     * 특정 메뉴를 삭제한다.
     * 
     * @param mnuId 메뉴 식별자
     */
    public void deleteMnu(String mnuId) {
        CheckUtil.checkArgumentNullOrEmpty(mnuId, MnuErrCd.EBMNUS008.name());
        
        Mnu mnu = mnuAwareService.find(mnuId);
        mnuAwareService.delete(mnu);
    }
    
}
