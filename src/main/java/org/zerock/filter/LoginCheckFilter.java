package org.zerock.filter;

import lombok.extern.log4j.Log4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j
@WebFilter(urlPatterns = {"/board/list"})
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("----------------------------------");
        log.info("LOGIN CHECK FILTER");
        log.info("----------------------------------");

        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Cookie[] cks = req.getCookies();

        if(cks == null || cks.length == 0){
            resp.sendRedirect("/main");
        }

        boolean check = false;

        for (Cookie ck:cks) {
            if(ck.getName().equals("login")){
                check = true;
                break;
            }
        }//end for

        if(!check){
            resp.sendRedirect("/main");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
