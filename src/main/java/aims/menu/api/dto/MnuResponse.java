package aims.menu.api.dto;

import aims.core.api.transferobject.BaseResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 메뉴 응답 데이터이다.
 * 
 * @author JH
 */
@AllArgsConstructor
@NoArgsConstructor
public class MnuResponse extends BaseResponseDto {
    private static final long serialVersionUID = 3488496252553818838L;
    
    /**
     * 원 메뉴 식별자를 반환한다.
     * 
     * @return 원 메뉴 식별자
     */
    public String getOrgMnuId() {
        return mnuId;
    }
    
    /**
     * 메뉴 식별자
     */
    @Getter
    @Setter
    private String mnuId;
    
    /**
     * 메뉴 명
     */
    @Getter
    @Setter
    private String mnuNm;
    
    /**
     * 메뉴 연결내용
     */
    @Getter
    @Setter
    private String mnuCtnt;
    
    /**
     * 메뉴 스타일
     */
    @Getter
    @Setter
    private String mnuStyle;
    
    /**
     * 메뉴 정렬순서
     */
    @Getter
    @Setter
    private Integer mnuSort;
}
