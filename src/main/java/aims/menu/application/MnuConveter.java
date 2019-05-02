package aims.menu.application;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import aims.menu.api.dto.MnuResponse;
import aims.menu.domain.Mnu;

/**
 * 메뉴 어그리게이션 TO객체 변환기이다.
 * 
 * @author JH
 */
@Component
public class MnuConveter {
    public MnuResponse convert(Mnu mnu) {
        MnuResponse response = new MnuResponse();
        BeanUtils.copyProperties(mnu, response);
        
        return response;
    }
}
