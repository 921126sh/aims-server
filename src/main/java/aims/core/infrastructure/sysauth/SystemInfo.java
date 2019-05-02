package aims.core.infrastructure.sysauth;

/**
 * 시스템 정보을 제공하는 서비스 인터페이스이다.
 * 
 * @author itt
 */
public interface SystemInfo {
    /**
     * 운영환경구분코드를 반환한다.
     * 
     * @return 운영환경구분코드
     */
    public String getEnvCd();
    
    /**
     * 사용자 식별자
     * 
     * @return 사용자 식별자
     */
    String getUserId();
    
    /**
     * 사용자 이름
     * 
     * @return 사용자 이름
     */
    String getUserNm();
}
