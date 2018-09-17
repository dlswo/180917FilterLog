package org.zerock.filter;

import org.zerock.web.util.Converter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/board/*")
public class SampleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init sample filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("do sample filter"); //필터를 막는 역할

        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String pageStr = servletRequest.getParameter("page");

        try{
            if(pageStr == null){ throw new Exception("NULL"); }

            int page = Integer.parseInt(pageStr);

            if(page <= 1){ // 페이지 숫자가 1이하면 예외발생
                throw new NumberFormatException("PAGE NUM");
            }

        }catch(NumberFormatException e){
            resp.sendRedirect("/error");
            return;
        } catch (Exception e) {

        }


        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy sample filter");
    }
}
