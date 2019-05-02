package aims.menu.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import aims.core.infrastructure.exception.Errors.MnuErrCd;
import aims.core.infrastructure.util.CheckUtil;
import aims.menu.api.dto.MnuResponse;
import aims.menu.domain.Mnu;
import aims.menu.domain.MnuAwareService;

/**
 * 메뉴 조회 서비스
 * 
 * @author JH
 */
@Service
@Transactional
public class RetrieveMnuService {
    
    /**
     * 메뉴 도메인 서비스
     */
    @Autowired
    private MnuAwareService mnuAwareService;
    
    /**
     * 메뉴 엔티티 <--> TO 객체 변환기
     */
    @Autowired
    private MnuConveter mnuConveter;
    
    /**
     * 메뉴 목록을 조회한다.
     * 
     * @return 메뉴 목록
     */
    public List<MnuResponse> retrieveMnuList() {
        List<Mnu> mnus = mnuAwareService.findAll();
        
        return Lists.transform(mnus, new Function<Mnu, MnuResponse>() {
            public MnuResponse apply(Mnu mnu) {
                return mnuConveter.convert(mnu);
            }
        });
    }
    
    /**
     * 메뉴 식별자에 해당되는 메뉴을 조회한다.
     * 
     * @param mnuId 메뉴 식별자
     * @return 메뉴 응답 데이터
     */
    public MnuResponse retrieveMnu(String mnuId) {
        CheckUtil.checkArgumentNullOrEmpty(mnuId, MnuErrCd.EBMNUS004.name());
        
        return mnuConveter.convert(mnuAwareService.find(mnuId));
    }
}
