package org.quickmap.apiservice.security;

import org.quickMap.constant.ApiServiceConstant;
import org.quickmap.apiservice.service.ITokenService;
import org.quickmap.dataService.dao.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.quickMap.constant.ApiServiceConstant.ROLE_PREFIX;

/**
 * jwt token身份验证过滤器
 * 从cookie中获取httpOnly的AUTH_TOKEN,
 * 解析 AUTH_TOKEN 获取权限字符
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private ITokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> optional;
        Authentication authentication;
        UserInfo userInfo;
        if (cookies != null
                && (optional = Arrays.stream(cookies).filter(cookie -> ApiServiceConstant.AUTH_TOKEN.equals(cookie.getName())).findFirst()).isPresent()
                && (userInfo = tokenService.getUserByToken(optional.get().getValue())) != null) {
            //获取权限字符
            List<GrantedAuthority> authorities = StringUtils.hasText(userInfo.getRoles()) ? Arrays.stream(userInfo.getRoles().split(","))
                    .map(roleName -> new SimpleGrantedAuthority(roleName.startsWith(ApiServiceConstant.ROLE_PREFIX) ? roleName :
                            (ApiServiceConstant.ROLE_PREFIX + roleName))).collect(Collectors.toList()) : Collections.emptyList();
            //添加访问权限
            authentication = new UsernamePasswordAuthenticationToken(
                    new User(userInfo.getLoginName(), "", authorities), null, authorities);

        } else {
            authentication = getGuestAuthentication();
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    /**
     * 获取游客角色
     *
     * @return
     */
    private Authentication getGuestAuthentication() {
        return new UsernamePasswordAuthenticationToken(
                new User("guest", "", Collections.emptyList()), null, Collections.emptyList());
    }
}