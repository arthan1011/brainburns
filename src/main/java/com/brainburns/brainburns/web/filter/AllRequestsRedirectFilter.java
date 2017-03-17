package com.brainburns.brainburns.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Arthan on 05.01.2017. Project: brainburns
 */


public class AllRequestsRedirectFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String contextPath = ((HttpServletRequest) request).getServletPath();
        boolean isResourceUrl = contextPath.contains(".js") || contextPath.contains(".css") || contextPath.contains(".html");
        boolean isLoginUrl = contextPath.equals("/signin") || contextPath.equals("/signup") || contextPath.equals("/user/signup");
        boolean isApiUrl = contextPath.startsWith("/api/");
        if (isResourceUrl || isLoginUrl || isApiUrl) {
            chain.doFilter(request, response);
        } else {
            // todo: I don't know why "/main.html" work but "/main" doesn't:
            request.getRequestDispatcher("/main.html").forward(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
