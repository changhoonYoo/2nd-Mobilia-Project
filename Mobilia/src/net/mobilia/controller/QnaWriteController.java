package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.MemberDAOImpl;

public class QnaWriteController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		MemberDAOImpl mdao=new MemberDAOImpl();

		String id=(String)session.getAttribute("id");

		if(id == null) {
			out.println("<script>");
			out.println("alert('로그인이 필요합니다');");
			out.println("location='login.net';");
			out.println("</script>");
		}else {
			
			request.setAttribute("id", id);
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./views/board/board_qna_write.jsp");
			return forward;
		}
		return null;
	}

}
