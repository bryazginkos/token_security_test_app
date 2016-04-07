package ru.kos.shop.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Фильтр ищет токен в заголовке X_ACCESS_TOKEN и прописывает в SecurityContext Authentication
 * типа {@link TokenAuthentication } с токеном в качестве credentials <br/>
 * Created by Константин on 05.04.2016.
 */
public class AuthenticationTokenFilter implements Filter {

    private static final String X_ACCESS_TOKEN = "X_ACCESS_TOKEN";

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        String token = null;
        if (req instanceof HttpServletRequest) {
            token = ((HttpServletRequest) req).getHeader(X_ACCESS_TOKEN);
        }

        Authentication auth = new TokenAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        fc.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}