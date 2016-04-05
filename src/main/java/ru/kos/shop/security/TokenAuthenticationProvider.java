package ru.kos.shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import ru.kos.shop.domain.User;

import javax.security.auth.login.AccountExpiredException;

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
        if (auth.isAuthenticated())
            return auth;

        String token = auth.getCredentials().toString();

        try {
            TokenInfo tokenInfo = tokenService.getTokenInfo(token);
            if (tokenInfo.isExpired()) {
                throw new AccountExpiredException("token is expired");
            }
            User user = authorizedUsersStorage.get(tokenInfo.getUserId());
            if (user != null) {
                Authentication filled = new PreAuthenticatedAuthenticationToken(user, token);
                filled.setAuthenticated(true);
                return filled;
            } else {
                throw new AuthenticationServiceException("System error");
            }
        } catch (Exception e) {
            //todo log error
            throw new BadCredentialsException("Invalid token " + token);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
