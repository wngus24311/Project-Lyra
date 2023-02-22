package com.lyra.project_lyra.configuration;

import com.lyra.project_lyra.service.implement.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class AuthenticationConfig {

    private final MemberServiceImpl memberService;

    /** properties에 있는 secretKey 선언 */
    @Value("${jwt.token.secret}")
    private String secretKey;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
       log.info("여기탐");
        return httpSecurity
                .httpBasic().disable()  /** 인증을 UI로 할게 아니고 토큰으로 할거기 때문에 disable */
                .csrf().disable()   // 위와 동
                .cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/member/join", "/member/login", "/member/mypage", "/member/membership", "/main/main").permitAll()   /** join, login은 언제나 가능하도록!!! */
//                .antMatchers(HttpMethod.POST, "/book/**").authenticated()   /**모든 포스트 요청과 책 읽기는 인증 필요로 해놓음.*/
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) /**JWT를 사용하는 경우에만 씀*/
                .and()
                .addFilterBefore(new JwtFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
