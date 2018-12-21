package org.quickMap.apiservice.security;

import org.quickMap.constant.ApiServiceConstant;
import org.quickMap.apiservice.service.ITokenService;
import org.quickMap.dataService.dao.model.UserInfo;
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
import java.util.stream.Collectors;

/**
 * jwt token身份验证过滤器
 * 从cookie中获取httpOnly的AUTH_TOKEN,
 * 解析 AUTH_TOKEN 获取身份验证信息
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private ITokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Authentication authentication;
        String token;
        UserInfo userInfo;
        if ((token = SecurityHelper.getTokenFrom(cookies)) != null
                && ((userInfo = tokenService.getUserByToken(token)) != null)) {
            //获取&设置权限字符
            List<GrantedAuthority> authorities = StringUtils.hasText(userInfo.getRoles()) ? Arrays.stream(userInfo.getRoles().split(","))
                    .map(roleName -> new SimpleGrantedAuthority(roleName.startsWith(ApiServiceConstant.ROLE_PREFIX) ? roleName :
                            (ApiServiceConstant.ROLE_PREFIX + roleName))).collect(Collectors.toList()) : Collections.emptyList();
            authentication = new UsernamePasswordAuthenticationToken(
                    new User(userInfo.getLoginName(), "", authorities), null, authorities);

        } else {
            authentication = getGuestAuthentication();
        }
        //向Security上下文添加身份验证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    /**
     * 获取游客角色验证信息
     *
     * @return
     */
    private Authentication getGuestAuthentication() {
        return new UsernamePasswordAuthenticationToken(
                new User("guest", "", Collections.emptyList()), null, Collections.emptyList());
    }
}