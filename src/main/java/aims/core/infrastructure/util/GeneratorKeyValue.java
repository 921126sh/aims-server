package aims.core.infrastructure.util;

import java.util.UUID;

/**
 * 식별자를 채번하는 유틸리티이다.
 * 
 * @author itt
 */
public final class GeneratorKeyValue {
	public GeneratorKeyValue() {
	}

	/**
	 * 식별자(UID) 값을 채번하여 반환한다.
	 * 
	 * @return 식별자(UID) 값
	 */
	public static String genUidValue() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 식별자(ID) 값을 채번하여 반환한다.
	 * 
	 * @return 식별자(ID) 값
	 */
	public static String genIdValue() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
