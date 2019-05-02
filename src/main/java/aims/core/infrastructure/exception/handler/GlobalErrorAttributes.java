package aims.core.infrastructure.exception.handler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;

import aims.core.infrastructure.exception.BaseRuntimeException;

/**
 * 모든 예외에 대한 공통 기본 오류응답을 제공하는 클래스 이다.
 * 
 * @author sh
 */
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {
//	/**
//	 * 메시지 서비스
//	 */
//	@Autowired
//	private RetrieveMsgService retrieveMsgService;
//	
//	@Override
//	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
//		String msgId = "Unknown";
//		
//		Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
//		errorAttributes.put("code", msgId);
//		
//		Throwable thrown = getError(requestAttributes);
//		
//		//발생된 예외객체를 구하여 의미있는 메세지를 재설정한다.
//		if (thrown instanceof BaseRuntimeException) {
//			BaseRuntimeException bdrte = (BaseRuntimeException) thrown;			
//			String msgCtnt = bdrte.getMessage();
//			
//			try {
//				msgId = retrieveMsgService.retrieveMsg(bdrte.getMsgCd()).getMsgId();				
//				msgCtnt = retrieveMsgService.retrieveMsg(msgId, bdrte.getMessage(), bdrte.getMsgArgs());
//			}
//			catch(MsgNotFoundException mnfe) {
//			}			
//			
//			//메세지 내용을 재설정한다.
//			errorAttributes.put("message", msgCtnt);
//		}		
//		
//		//메세지 코드를 설정한다.
//		errorAttributes.put("code", msgId);
//		
//		return errorAttributes;
//	}
}
