package oo.core.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import oo.core.application.AuthenticationService;
import oo.core.infrastructure.sysauth.RestAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    // 스프링 컨텍스트
    @Autowired
    private Environment springEnv;
        
    // 로그인 서비스
    @Autowired
    private AuthenticationService authenticationService;
    
    // 서버포트 환경 변수
    @Value("${server.port}")
    private int serverPort;
    
    // 인증되지 않은 접근에 대해 로그인 폼으로 redirect(302)시키지 않고 401 Status만 리턴
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    
    // 인증관리자빈
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
//    // 인증필터
//    @Bean
//    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
//        authenticationTokenFilter.setAuthenticationManager(this.authenticationManagerBean());
//        return authenticationTokenFilter;
//    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 한글이 안깨지게 필터 적용
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        
        // 운영모드일때는 사용 불가 :: 개발,테스트일때만 사용
        if (springEnv.acceptsProfiles("dev", "test")) {
            // Swagger2 접속 허용
            http.authorizeRequests().antMatchers("/developers/**", "/configuration/**", "/v2/api-docs", "/swagger-resources").permitAll();
        }
        
        // 런타임에서의 접속은 인증에서 제외 시킨다.
        http.authorizeRequests().antMatchers("/rts/**").permitAll();
        
        http.csrf().disable().headers().frameOptions().disable().and()
                // 인증 실패시 Redirect(302) 대신 401 Status만 리턴
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                // STATELESS : Security 인증 세션을 유지하지 않음
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        // static resources
        http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/webjars/**", "/document-images/**", "/**").permitAll();
        
        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/auth/refresh").permitAll()
                .antMatchers(HttpMethod.GET, "/check-update/**").permitAll()
                .antMatchers(HttpMethod.GET, "/down-app/**").permitAll()
                //TODO TEST용
//                .antMatchers(HttpMethod.POST, "/upload-app/**").permitAll()
                .anyRequest().authenticated().and()
                // SSL 설정 후 적용
                // .requiresCh().anyRequest().requiresSecure().and()
                .formLogin().disable();
        
        // SSL 설정 후 적용
        // http.portMapper().http(serverPort).mapsTo(8443);
        
//        // 인증 필터 등록
//        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
//        
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService);
    }
}
