package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mobilia.dao.QnaBoardDAOImpl;
import net.mobilia.vo.QnaBoardVO;

public class QnaEditOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		
		int page=1;
		
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
			//get으로 전달된 쪽번호르 정수 숫자로 바꿔서 저장
		}
		
		int board_no=Integer.parseInt(request.getParameter("board_no"));
		//히든으로 전달된 게시판 번호값을 받아서 저장
		
		String board_name=request.getParameter("board_name");
		String board_title=request.getParameter("board_title");
		String board_cont=request.getParameter("board_cont");
		
		QnaBoardDAOImpl qdao=new QnaBoardDAOImpl();
		
		QnaBoardVO qvo=new QnaBoardVO();
		qvo.setBoard_no(board_no); qvo.setBoard_name(board_name);
		qvo.setBoard_title(board_title); qvo.setBoard_cont(board_cont);
		
		int re=qdao.editQnaBoard(qvo);
		
			if(re == 1) {
				
				   out.println("<script>");
		           out.println("alert('게시물이 수정되었습니다!');");
		           out.println("location='qna.net?page="+page+"';");
		           out.println("</script>");
			}
		
		
		return null;
	}
}
