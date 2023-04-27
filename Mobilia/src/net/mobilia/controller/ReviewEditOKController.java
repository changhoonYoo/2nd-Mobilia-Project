package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mobilia.dao.ReviewDAOImpl;
import net.mobilia.vo.ReviewVO;

public class ReviewEditOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		
		request.setCharacterEncoding("UTF-8");
		
		int page=1;
		
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
			//get으로 전달된 쪽번호르 정수 숫자로 바꿔서 저장
		}
		
		int p_no=Integer.parseInt(request.getParameter("p_no"));
		int re_no=Integer.parseInt(request.getParameter("re_no"));
		int re_star=Integer.parseInt(request.getParameter("re_star"));
		String re_cont=request.getParameter("re_cont");
		
		ReviewDAOImpl rdao= new ReviewDAOImpl();
		
		ReviewVO rv=new ReviewVO();
		
		rv.setRe_no(re_no);
		rv.setRe_star(re_star); rv.setRe_cont(re_cont);
		
		int re=rdao.updateReview(rv);
		
		if(re==1) {
			out.println("<script>");
			out.println("alert('후기를 수정하였습니다.');");
			out.println("self.close();");
			out.println("opener.parent.location.href='product_info.net?p_no="+p_no+"&page="+page+"';");
			out.println("</script>");
		}
		return null;
	}
}
