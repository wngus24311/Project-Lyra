package com.lyra.project_lyra.configuration;

import com.lyra.project_lyra.service.implement.MemberServiceImpl;
import com.lyra.project_lyra.util.Authority;
import com.lyra.project_lyra.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
public class JwtFilter extends OncePerRequestFilter {
    private Authority authority;
    private final MemberServiceImpl memberService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authentication =======> " + authorization);

        // token 없으면 block
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            log.info("authorization ======> isNull");
            log.error("Authorization을 잘못 보냈습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // Token 추출
        String token = authorization.split(" ")[1];

        // Token Expired 되었는지 확인
        if (JwtUtil.isExpired(token, secretKey)) {
            log.error("Token이 만료 되었습니다.");
            filterChain.doFilter(request, response);
            return;
        }

        // Username Token 에서 꺼내기
        String username = JwtUtil.getUserName(token, secretKey);
        log.info("userName ========> " + username);
        // 권한 부여
        // Todo: 권한 수정 해줘야함
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("USER")));

        // Detail 넣어줌
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
