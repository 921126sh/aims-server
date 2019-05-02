package aims.core.infrastructure.exception;

import org.springframework.http.HttpStatus;

/**
 * 런타임(unchecked) 예외 계열의 데이터 중복 예외 오브젝트이다.
 * 
 * @author itt
 */
public class DuplicateDataException extends BaseRuntimeException {
    private static final long serialVersionUID = -1458057755530225190L;
    
    /**
     * 오류코드 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     */
    public DuplicateDataException(String code) {
        this(code, new Object[] {}, null);
    }

    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param cause 원인예외
     */
    public DuplicateDataException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }

    /**
     * 오류코드를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public DuplicateDataException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }
    
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public DuplicateDataException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
    
    /**
     * HTTP 응답상태(중복 됨 : {@link HttpStatus#CONFLICT})를 반환한다.
     * 
     * @return HTTP 응답 상태
     */
    @Override
    public HttpStatus getResponseHttpStatus() {
    	return HttpStatus.CONFLICT;
    }
}
