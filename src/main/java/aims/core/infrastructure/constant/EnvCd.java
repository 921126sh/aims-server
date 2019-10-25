package aims.core.infrastructure.constant;

import lombok.Getter;

/**
 * 환경코드 개발(loc): {@link EnvCd#Local}, 개발(dev): {@link EnvCd#Develop}, 테스트(test): {@link EnvCd#Test}, 스테이징(stg) {@link EnvCd#Staging}, 교육(edu): {@link EnvCd#Education}, 운영(prod): {@link EnvCd#Production})에 대한 열거형 상수이다.
 *
 * @author jjh
 */
public enum EnvCd {
    /**
     * 개발
     */
    Local("loc", "로컬"),
    /**
     * 개발
     */
    Develop("dev", "개발"),

    /**
     * 테스트
     */
    Test("test", "테스트"),
    
    /**
     * 스테이징
     */
    Staging("stg", "스테이징"),
    
    /**
     * 교육
     */
    Education("edu", "교육"),
    
    /**
     * 운영
     */
    Production("prod", "운영");
    
    /**
     * 코드
     */
    @Getter
    private String code;
    
    /**
     * 제목
     */
    @Getter
    private String title;
    
    /**
     * 열거형 상수를 초기화하는 생성자이다.
     * 
     * @param code 코드
     * @param title 제목
     */
    EnvCd(String code, String title) {
        this.code = code;
        this.title = title;
    }
    
    /**
     * 코드에 해당되는 열거형 상수를 반환한다.
     * 
     * @param code 코드
     * @return 코드에 해당되는 열거형 상수
     */
    public final static EnvCd codeOf(String code) {
        for (EnvCd em : values()) {
            if (em.code.equals(code)) {
                return em;
            }
        }
        
        return null;
    }

    @Override
    public String toString() {
        return this.code;
    }
}