package aims.core.infrastructure.exception.handler;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import aims.core.infrastructure.exception.BaseRuntimeException;

/**
 * 모든 예외에 대한 공통 기본 조작자 클래스 이다.
 * 
 * @author sh
 */
@ControllerAdvice
@RestController
public class GlobalDefaultExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	
	/**
	 * 시스템 오류 메세지 
	 */
	@Value("${sys.msg.error}")
	private String msgError;
	
	/**
	 * 잘못된 아귀먼트 예외을 HTTP 응답상태({@link HttpStatus#BAD_REQUEST})로 처리한다.
	 * 
	 * @param response HTTP 서블릿 응답객체
	 * @param iae 아귀먼트 예외
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public void handleIllegalArgumentException(HttpServletResponse response, IllegalArgumentException iae) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());

		if (logger.isErrorEnabled()) {
			logger.error(String.format("IllegalArgument exception HTTP-STATUS [%d]", HttpStatus.BAD_REQUEST.value()), iae);
		}
	}
	
	/**
	 * 사용자 권한없음 예외을 HTTP 응답상태({@link HttpStatus#UNAUTHORIZED})로 처리한다.
	 * 
	 * @param response HTTP 서블릿 응답객체
	 * @param bce 권한없음 예외
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public void handleBadCredentialsException(HttpServletResponse response, BadCredentialsException bce)
			throws IOException {
		response.sendError(HttpStatus.UNAUTHORIZED.value());

		if (logger.isErrorEnabled()) {
			logger.error(String.format("Authentication exception HTTP-STATUS [%d]", HttpStatus.UNAUTHORIZED.value()), bce);
		}
	}

	/**
	 * FEVER 계열 예외에 대한 HTTP 응답상태를 처리한다.
	 * 
	 * @param response HTTP 서블릿 응답객체
	 * @param bdrte FEVER 계열 예외
	 */
	@ExceptionHandler(BaseRuntimeException.class)
	public void handleAnyApiRunTimeException(HttpServletResponse response, BaseRuntimeException bdrte) throws IOException {
		// HTTP상태코드
		int httpStatusCode = (null == bdrte.getResponseHttpStatus() ? HttpStatus.INTERNAL_SERVER_ERROR.value()
				: bdrte.getResponseHttpStatus().value());
		
		response.sendError(httpStatusCode);

		if (logger.isErrorEnabled()) {
			logger.error(String.format("요청을 처리하던 중 오류가 발생했습니다. HTTP-STATUS [%d] : %s", httpStatusCode, bdrte.getMessage()), bdrte);
		}
	}
	

	/**
	 * FEVER 계열 예외 이외의 예외에 대한 HTTP 응답상태({@link HttpStatus#UNAUTHORIZED})로 처리한다.
	 * 
	 * @param response HTTP 서블릿 응답객체
	 * @param t 최상위 예외객체
	 */
	@ExceptionHandler(Throwable.class)
	public void handleAnyException(HttpServletResponse response, Throwable t) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), msgError);

		if (logger.isErrorEnabled()) {
			logger.error(String.format("요청을 처리하던 중 예상하지 못한 오류가 발생했습니다. HTTP-STATUS [%d]",
					HttpStatus.INTERNAL_SERVER_ERROR.value()), t);
		}
	}
}
