/**
 * 
 */
package aims.core.infrastructure.sysauth;

import org.springframework.security.core.userdetails.User;

import lombok.Getter;

/**
 * @author itt
 *
 */
public class AuthUserVo extends User {
	private static final long serialVersionUID = 245056973041179686L;

    /**
     * 사용자 계정 명
     */
    @Getter
    private String userNm;
    
    /**
     * 인증정보를 초기화하는 생성자이다.
     * 
     * @param userId 사용자 계정 식별자
     * @param userNm 사용자 계정 명
     * @param password 패스워드
     * @param enabled 사용여부
     * @param accountNonExpired 사용자계정만료이전여부
     * @param credentialsNonExpired 사용자인증말료이전여부
     * @param accountNonLocked 사용자계정미제한 여부
     * @param expYmd 계정만료일자
     * @param chgPwdYmd 비밀번호 변경일자
     * @param pwdExpDay 비밀번호 변경 만료일자
     * @param userDiv 사용자구분
     * @param authorities 사용자역할목록
     */
	public AuthUserVo(String userId, String userNm, String password, boolean enabled, boolean accountNonExpired, 
	        boolean credentialsNonExpired, boolean accountNonLocked) {
		super(userId, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, null);
		
		this.userNm = userNm;
	}
	
	/**
	 * 사용자 식별자를 반환한다.
	 * 
	 * @return 사용자 식별자
	 */
	public String getUserId() {
		return getUsername();
	}
}
