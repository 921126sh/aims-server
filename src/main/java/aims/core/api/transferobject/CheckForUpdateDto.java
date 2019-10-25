package aims.core.api.transferobject;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.pojomatic.annotations.AutoProperty;
import org.springframework.hateoas.ResourceSupport;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 * 어플리케이션 버전 업데이트 TO이다.
 * 
 * @author KJS
 */
@AllArgsConstructor
@NoArgsConstructor
//@JsonFilter("allExceptFilter")
@AutoProperty
@XmlRootElement(name = "checkforupdate-dto")
public class CheckForUpdateDto  extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = -2415215035965760296L;

    /**
     * 버전
     */
    @NonNull
    @Getter
    @Setter
    private String version;
    
    /**
     * 파일이름
     */
    @NonNull
    @Getter
    @Setter
    private String fileName;
    
    /**
     * 등록일자
     */
    @NonNull
    @Getter
    @Setter
    private String date;
    
    /**
     * 중요한 변경내용
     */
    @NonNull
    @Getter
    @Setter
    private List<String> notableChanges = Lists.newArrayList();
}