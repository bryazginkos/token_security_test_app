package ru.kos.shop.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Константин on 05.04.2016.
 */
public class AuthenticationTokenFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(null);
        Map<String, String[]> params = req.getParameterMap();
        String token = !params.isEmpty() && params.containsKey("auth_token") ? params.get("auth_token")[0] : null; //todo header

        Authentication auth = new TokenAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        fc.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}