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
        if (!isResourceUrl) {
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
