package org.quickmap.apiservice.security;

import org.quickMap.constant.ApiServiceConstant;
import org.quickmap.apiservice.service.ILoginService;
import org.quickmap.apiservice.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationTokenFilter extends RequestHeaderAuthenticationFilter {

    @Autowired
    ITokenService tokenService;

    @Autowired
    ILoginService loginService;

    public JwtAuthenticationTokenFilter() {

        this.setExceptionIfHeaderMissing(false);
        this.setPrincipalRequestHeader(ApiServiceConstant.AUTH_TOKEN);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
