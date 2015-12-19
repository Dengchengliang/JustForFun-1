package org.handy.manmadeprototype.auth.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


/**
 * Created by zhongming on 15/12/19.
 */

@Component("SiteAuthenticationEntryPoint")
public class SiteAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {
        if(exception != null) {
            response.sendRedirect("/auth/login");
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
