package aims.core.infrastructure.util;

import java.util.Collection;

import javax.annotation.Nullable;


/**
 * 검사 관련한 기능을 제공하는 유틸리티이다.
 * 
 * @author itt
 */
public class CheckUtil {
    private CheckUtil() {
    }
    
    /**
     * 값이 NULL 이거나 공백인지 여부를 반환한다.
     * 
     * @param value 값
     * @return 값이 NULL 이거나 공백인지 여부
     */
    public static boolean isNullOrEmpty(String value) {
        return (null == value || value.trim().isEmpty());
    }
    
    /**
     * 값이 NULL 이거나 공백이 아닌지 여부를 반환한다.
     * 
     * @param value 값
     * @return 값이 NULL 또는 공백이 아닌지 여부
     */
    public static boolean isNotNullOrNotEmpty(String value) {
        return !isNullOrEmpty(value);
    }
    
    /**
     * 주어진 값이 NULL 이거나 공백이면 기본값을 반환한다.
     * 
     * @param src 주어진값
     * @param defaultSrc 기본값
     * @return 주어진 값이 NULL 이거나 공백이면 기본값
     */
    public static String nvl(String src, String defaultSrc) {
        return (isNullOrEmpty(src) ? defaultSrc : src);
    }

    /**
     * 주어진 인자 목록의 하나라도 NULL 또는 공백이면 에외를 발생시킨다.
     * 
     * @param arg 체크할 인자 목록
     * @param errCd 오류코드
     * @throws IllegalArgException 인자가 NULL 또는 공백이면 발생되어진다.
     */
    public static void checkArgumentNullOrEmpty(Collection<String> args, String errCd) {
//        boolean isNullOrEmpty = Iterators.any(args.iterator(), new Predicate<String>() {
//            @Override
//            public boolean apply(String arg) {
//                return CheckUtil.isNullOrEmpty(arg);
//            }
//        });
//        
//        try { 
//        	Preconditions.checkArgument(!isNullOrEmpty, errCd);
//        }
//        catch (IllegalArgumentException iae) {
//        	throw new IllegalArgException(errCd, (String[])args.toArray(), iae);
//        }
    }
    
    /**
     * 주어진 인자가 NULL 또는 공백이면 에외를 발생시킨다.
     * 
     * @param arg 체크할 인자
     * @param errCd 오류코드
     * @throws IllegalArgException 인자가 NULL 또는 공백이면 발생되어진다.
     */
    public static void checkArgumentNullOrEmpty(String arg, @Nullable String errCd) {
//    	try { 
//        	Preconditions.checkArgument(CheckUtil.isNotNullOrNotEmpty(arg), errCd);
//        }
//        catch (IllegalArgumentException iae) {
//        	throw new IllegalArgException(errCd, new Object[] {arg}, iae);
//        }
    	
    }
}
