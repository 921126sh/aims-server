package oo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 런타임(unchecked) 예외 계열의 잘못된 인자에 대한 예외 오브젝트이다.
 * 
 * @author itt
 */
public class IllegalArgException extends BaseRuntimeException {
	private static final long serialVersionUID = 7407331591921329626L;

	/**
     * 오류코드 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     */
    public IllegalArgException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param cause 원인예외
     */
    public IllegalArgException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 오류코드를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public IllegalArgException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }
    
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public IllegalArgException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
    
    /**
     * HTTP 응답상태(잘못된 요청 : {@link HttpStatus#BAD_REQUEST})를 반환한다.
     * 
     * @return HTTP 응답 상태
     */
    @Override
    public HttpStatus getResponseHttpStatus() {
    	return HttpStatus.BAD_REQUEST;
    }
}
