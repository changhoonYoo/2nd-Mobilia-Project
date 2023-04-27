package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.MemberDAOImpl;
import net.mobilia.dao.QnaBoardDAOImpl;
import net.mobilia.vo.QnaBoardVO;

public class QnaWriteOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();

		String id=(String)session.getAttribute("id");

		if(id == null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='login.net';");
			out.println("</script>");
		}else {

			request.setCharacterEncoding("utf-8");
			String board_title=request.getParameter("board_title");
			String board_cont=request.getParameter("board_cont");

			QnaBoardVO qnavo=new QnaBoardVO();
			qnavo.setBoard_name(id); qnavo.setBoard_title(board_title);
			qnavo.setBoard_cont(board_cont);

			QnaBoardDAOImpl bdao=new QnaBoardDAOImpl();
			int re = bdao.insertBoard(qnavo);//게시판 저장

			if(re == -1) {
				out.println("<script>");
				out.println("alert('게시판 저장 실패');");
				out.println("location='qna_write.net';");
				out.println("</script>");
			}else {
				
				   out.println("<script>");
		           out.println("alert('게시물이 등록되었습니다!');");
		           out.println("location='qna.net';");
		           out.println("</script>");
			}
		}
		return null;
	}

}
