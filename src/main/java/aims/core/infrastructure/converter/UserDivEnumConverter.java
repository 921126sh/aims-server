package aims.core.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;

import aims.core.infrastructure.constant.UserDiv;

/**
 * 문자열({@link java.lang.String})을 여부({@link UserDiv}) 열거형 상수로 변환해주는 SPRING 오브젝트 변환기이다.
 * 
 * @author sh
 */
public class UserDivEnumConverter implements Converter<String, UserDiv> {
    @Override
    public UserDiv convert(String value) {
        return UserDiv.valueOf(value);
    }
}
