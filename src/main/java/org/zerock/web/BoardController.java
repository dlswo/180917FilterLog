package org.zerock.web;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.zerock.dao.BoardDAO;
import org.zerock.domain.PageDTO;
import org.zerock.domain.PageMaker;
import org.zerock.web.util.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Log4j
@WebServlet(urlPatterns = "/board/*")
public class BoardController extends AbstractController {

    BoardDAO dao = new BoardDAO();


    @Override
    public String getBasic() {
        return "/board/";
    }

    public String writeGET(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        System.out.println("writeGET................................");



        return "write";
    }

    public String listGET(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        System.out.println("listGET................................");
        log.trace("Trace.........");
        log.debug("debug..................");
        log.info("info..........");
        log.warn("warn............");
        log.error("error...........");
        log.fatal("fatal.............");

        PageDTO dto = PageDTO.of()
                .setPage(Converter.getInt(req.getParameter("page"),1))
                .setSize(Converter.getInt(req.getParameter("size"),10));

        int total = 320;
        PageMaker pageMaker = new PageMaker(total,dto);


        req.setAttribute("pageMaker",pageMaker);
        req.setAttribute("list",dao.getList(dto));
        pageMaker.getPageDTO().getPage();

        return "list";
    }

    public String readGET(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        System.out.println("readGET................................");

        String bnoStr = req.getParameter("bno");
        int bno = Converter.getInt(bnoStr,-1);
        boolean updateable = false;

        if(bno==-1){ throw new Exception("Invalid data");}

        Cookie[] cookies = req.getCookies();
        Cookie viewCookie = null;
        for(Cookie ck:cookies){
            if(ck.getName().equals("views")){
                viewCookie = ck;
                break;
            }


        }





        //쿠키가 없다면
        if(viewCookie==null){
            Cookie newCookie = new Cookie("views",bnoStr+"%"); // 구분하기위해서 %를 붙여줌
            newCookie.setMaxAge(60*60*24); //하루동안 유지
            viewCookie = newCookie;
            updateable = true;
        }else { //쿠키가 있다면
            String cookieValue = viewCookie.getValue();
            System.out.println("cookieValue : " + cookieValue );
            updateable = cookieValue.contains(bnoStr+"%") == false;

            if(updateable){
                cookieValue += bno + "%";
                viewCookie.setValue(cookieValue);
            }
            System.out.println("cookieValue after :" + cookieValue);
            System.out.println("updateable :" + updateable);
        }

        System.out.println("---------------------------------");
        System.out.println(viewCookie);
        resp.addCookie(viewCookie); // 쿠키추가가

       req.setAttribute("board",dao.getBoard(bno,updateable));

        return "read";
    }
}
