package oo.core.infrastructure.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author sh
 */
public enum UserDiv {
    /**
     * 관리자
     */
    ADMIN("00", "관리자"),
    
	/**
	 * 점주
	 */
	MANAGER("01", "점주"),

	/**
	 * 직원
	 */
	EMPLOYEE("02", "직원");
	
    @Getter
    @Setter
    private String code;
    
    @Getter
    @Setter
	private String codeNm;
	
	UserDiv (String code, String codeNm) {
        this.code = code;
        this.codeNm = codeNm;
    }
}
