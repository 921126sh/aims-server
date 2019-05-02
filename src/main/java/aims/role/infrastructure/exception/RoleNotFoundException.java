package aims.role.infrastructure.exception;

import aims.core.infrastructure.exception.NotFoundDataException;

public class RoleNotFoundException extends NotFoundDataException {
    private static final long serialVersionUID = 5890910652008627415L;

    /**
     * 오류코드 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     */
    public RoleNotFoundException(String code) {
        this(code, new Object[] {}, null);
    }
    
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param cause 원인예외
     */
    public RoleNotFoundException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }
    
    /**
     * 오류코드를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public RoleNotFoundException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }
    
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public RoleNotFoundException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
}
