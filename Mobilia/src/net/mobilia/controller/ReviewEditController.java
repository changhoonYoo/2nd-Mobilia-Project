package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.ReviewDAOImpl;
import net.mobilia.vo.ReviewVO;

public class ReviewEditController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		int page=1;
		
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
			//get으로 전달된 쪽번호르 정수 숫자로 바꿔서 저장
		}
		if(id==null) {
			out.println("<script>");
			out.println("alert('로그인 후 작성해주세요!');");
			out.println("self.close();");
			out.println("opener.parent.location.href='login.net';");
			out.println("</script>");
		}else {
			int re_no =Integer.parseInt(request.getParameter("re_no"));
			ReviewDAOImpl rdao=new ReviewDAOImpl();
			
			ReviewVO rv=rdao.getReviewCont(re_no);
			
			request.setAttribute("r", rv);
			request.setAttribute("page", page);
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./views/product/review_edit.jsp");
			return forward;
		}
		return null;
	}
}
