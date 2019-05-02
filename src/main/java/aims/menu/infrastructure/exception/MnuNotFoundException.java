package aims.menu.infrastructure.exception;

import aims.core.infrastructure.exception.NotFoundDataException;

public class MnuNotFoundException extends NotFoundDataException {
    private static final long serialVersionUID = 5553111688636277836L;

    /**
     * 오류코드 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     */
    public MnuNotFoundException(String code) {
        this(code, new Object[] {}, null);
    }
    
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param cause 원인예외
     */
    public MnuNotFoundException(String code, Throwable cause) {
        this(code, new Object[] {}, cause);
    }
    
    /**
     * 오류코드를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     */
    public MnuNotFoundException(String code, Object[] codeMessageArgs) {
        super(code, codeMessageArgs);
    }
    
    /**
     * 오류코드와 원인예외를 초기화하는 생성자이다.
     * 
     * @param code 오류코드
     * @param codeMessageArgs 메세지 치환 아귀먼트 목록
     * @param cause 원인예외
     */
    public MnuNotFoundException(String code, Object[] codeMessageArgs, Throwable cause) {
        super(code, codeMessageArgs, cause);
    }
}
