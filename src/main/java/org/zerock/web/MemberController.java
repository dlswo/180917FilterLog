package org.zerock.web;

import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@WebServlet("/member/*")
@Log4j
public class MemberController extends AbstractController {

    public String loginGET(HttpServletRequest req, HttpServletResponse resp)throws Exception{

        log.info("login Post");

        Cookie loginCookie = new Cookie("login", URLEncoder.encode("Hwang In Jae","UTF-8"));
        loginCookie.setMaxAge(60*60*24);
        loginCookie.setPath("/"); //없으면 멤버에 들어갈때만 쿠키가 있음
        resp.addCookie(loginCookie);

        return "/board/list";
    }

    @Override
    public String getBasic() {
        return "/member/";
    }
}
