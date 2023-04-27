package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.MemberDAOImpl;
import net.mobilia.dao.ReviewDAOImpl;
import net.mobilia.vo.ReviewVO;

public class ReviewWriteOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out=response.getWriter();
		
		ReviewVO rv=new ReviewVO();
		
		int p_no =Integer.parseInt(request.getParameter("p_no"));
		HttpSession session=request.getSession();
		String m_id=(String)session.getAttribute("id");
		String p_img1=request.getParameter("p_img1");
		String p_name=request.getParameter("p_name");
		String re_cont=request.getParameter("re_cont");
		int re_star=Integer.parseInt(request.getParameter("re_star"));
		
		rv.setP_no(p_no); rv.setM_id(m_id); rv.setP_img1(p_img1);
		rv.setP_name(p_name); rv.setRe_cont(re_cont); rv.setRe_star(re_star);
		
		ReviewDAOImpl rdao=new ReviewDAOImpl();
		
		int re=rdao.insertReview(rv);
		
		if(re==1) {
			out.println("<script>");
			out.println("alert('후기가 등록 되었습니다.');");
			out.println("opener.parent.location.reload();");//공지창을 부른 부모창을 새로고침함
			out.println("window.close();");//공지창 닫음
			out.println("</script>");
		}
		return null;
	}

}
