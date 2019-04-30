/**
 * 
 */
package oo.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 런타임(unchecked) 예외 계열의 사전조건 위반 예외 오브젝트이다.
 * 
 * @author jjh
 */
public class PreconditionFailedException extends BaseRuntimeException {
	private static final long serialVersionUID = 3857827664544497001L;

	/**
     * 오류코드 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     */
    public PreconditionFailedException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param cause 원인예외
     */
    public PreconditionFailedException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 오류코드를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public PreconditionFailedException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public PreconditionFailedException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
    
    /**
     * HTTP 응답상태(요청을 처리하는데 문제 있음 오류 : {@link HttpStatus#CONFLICT})를 반환한다.
     * 
     * @return HTTP 응답상태
     */
    public HttpStatus getResponseHttpStatus() {
    	return HttpStatus.PRECONDITION_FAILED;
    }
}
