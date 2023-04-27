package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mobilia.dao.ReviewDAOImpl;

public class ReviewDelOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		
		int re_no= Integer.parseInt(request.getParameter("re_no"));
		int page= Integer.parseInt(request.getParameter("page"));
		int p_no= Integer.parseInt(request.getParameter("p_no"));
		
		ReviewDAOImpl rdao=new ReviewDAOImpl();
		
		int re=rdao.delReview(re_no);
		
		if(re==1) {
			out.println("<script>");
	    	out.println("alert('삭제 되었습니다!');");
	    	out.println("location='product_info.net?p_no="+p_no+"&page="+page+"';");
	    	out.println("</script>");
	    	
		}
		return null;
	}
}
