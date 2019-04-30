package oo.core.infrastructure.converter;

import org.springframework.core.convert.converter.Converter;

import oo.core.infrastructure.constant.YesOrNo;

/**
 * 문자열({@link java.lang.String})을 여부({@link YesOrNo}) 열거형 상수로 변환해주는 SPRING 오브젝트 변환기이다.
 * 
 * @author sh
 */
public class YesOrNoEnumConverter implements Converter<String, YesOrNo> {
    @Override
    public YesOrNo convert(String value) {
        return YesOrNo.valueOf(value);
    }
}
