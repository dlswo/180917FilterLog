package org.zerock.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/board/*")
public class IPFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)servletRequest;

        System.out.println("-------------------------------------------");
        System.out.println(servletRequest.getRemoteAddr());

        String content = req.getParameter("content");

        if(content != null){
            System.out.println(content);
        }

        filterChain.doFilter(servletRequest,servletResponse);

    }
}
