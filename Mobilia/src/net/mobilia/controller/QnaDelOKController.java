package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mobilia.dao.QnaBoardDAOImpl;
import net.mobilia.vo.QnaBoardVO;

public class QnaDelOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		  response.setContentType("text/html;charset=utf-8");
	      PrintWriter out=response.getWriter();
	      
	      int board_no=Integer.parseInt(request.getParameter("board_no"));
	      int page=1;
	      if(request.getParameter("page") != null) {
	         page=Integer.parseInt(request.getParameter("page"));
	      }
	      
	      String board_name=request.getParameter("board_name");
	      
	      QnaBoardDAOImpl pdao=new QnaBoardDAOImpl();
	      QnaBoardVO db_id=pdao.getBoardCont(board_no);//오라클로 부터 비번을 가져옴.
	        
	        if(!db_id.getBoard_name().equals(board_name)) {
	           out.println("<script>");
	           out.println("alert('아이디가 다릅니다!');");
	           out.println("history.go(-1);");
	           out.println("</script>");
	        }else {
	           pdao.delBoard(board_no);
	           
	           out.println("<script>");
	           out.println("alert('게시물이 삭제되었습니다!');");
	           out.println("location='qna.net?page="+page+"';");
	           out.println("</script>");
	           
	          
	}
	        return null;
	}
}
