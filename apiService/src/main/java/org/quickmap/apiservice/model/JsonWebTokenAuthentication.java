package org.quickmap.apiservice.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.util.Collection;

public class JsonWebTokenAuthentication extends AbstractAuthenticationToken {

    public JsonWebTokenAuthentication(Collection<? extends GrantedAuthority> authorities, UserDetails userDetails) {
        super(authorities);
    }

    public JsonWebTokenAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}

