import org.junit.Test;
import org.zerock.dao.BoardDAO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.PageDTO;
import org.zerock.domain.PageMaker;

import static junit.framework.TestCase.assertNotNull;


public class BoardDAOTests {

    private BoardDAO boardDAO = new BoardDAO();

    @Test
    public void testPageMaker(){

        PageDTO dto = PageDTO.of().setPage(11).setSize(10);
        int total = 123;

        PageMaker pageMaker = new PageMaker(total,dto);

        System.out.println(pageMaker);

    }

    @Test
    public void testRead() throws Exception{
        int bno = 5701658;
        System.out.println(boardDAO.getBoard(bno,true));
    }


    @Test
    public void testInsert() throws Exception {
        BoardVO vo = new BoardVO();
        vo.setTitle("테스트 제목");
        vo.setContent("테스트 내용");
        vo.setWriter("injae");
        boardDAO.create(vo);
    }

    @Test
    public void testList()throws Exception{

        boardDAO.getList(PageDTO.of().setPage(2).setSize(100))
                .forEach(vo-> System.out.println(vo));

    }


    @Test
    public void test1(){

        assertNotNull(boardDAO);
        System.out.println("test1......................");

        PageDTO pageDTO = PageDTO.of();

        System.out.println(pageDTO);

    }
}
