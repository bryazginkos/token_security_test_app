package ru.kos.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.kos.shop.domain.User;

import java.util.Arrays;

/**
 * Created by Константин on 05.04.2016.
 */
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizedUsersStorage authorizedUsersStorage;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String token = auth.getCredentials() != null ? auth.getCredentials().toString() : null;

        if (token == null) {
            return auth;
        }

        //todo do not throw exception if bad token. Just only do not grant admin_role
        try {
            TokenInfo tokenInfo = tokenService.getTokenInfo(token);
            if (tokenInfo.isExpired()) {
                return auth;
            }
            User user = authorizedUsersStorage.get(tokenInfo.getUserId());
            if (user != null) {
                Authentication filled = new PreAuthenticatedAuthenticationToken(user, token, Arrays.asList(new SimpleGrantedAuthority(Roles.ROLE_ADMIN)));
                filled.setAuthenticated(true);
                return filled;
            } else {
                //system error
                return auth;
            }
        } catch (Exception e) {
            return auth;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
