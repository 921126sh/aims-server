package aims.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 런타임(unchecked) 예외 계열의 데이터 없음 예외 오브젝트이다.
 * 
 * @author itt
 */
public class NotFoundDataException extends BaseRuntimeException {
    private static final long serialVersionUID = 8297299072345356382L;

    /**
     * 오류코드 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     */
    public NotFoundDataException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param cause 원인예외
     */
    public NotFoundDataException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 오류코드를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public NotFoundDataException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }
    
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public NotFoundDataException(String code, Object[] codeMessageArgs, Throwable cause) {
    	super(code, codeMessageArgs, cause);
    }
    
    /**
     * HTTP 응답상태(존재하지 않음 : {@link HttpStatus#NOT_FOUND})를 반환한다.
     * 
     * @return HTTP 응답상태
     */
    @Override
    public HttpStatus getResponseHttpStatus() {
    	return HttpStatus.NOT_FOUND;
    }
}
