package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.MemberDAOImpl;
import net.mobilia.vo.MemberVO;

public class ModifyEditOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out=response.getWriter();
	    HttpSession session=request.getSession();
	    
	    
	    String id=(String)session.getAttribute("id");//세션 아이디값을 구함.
	    
	    if(id==null) {
	    	out.println("<script>");
	    	out.println("alert('다시 로그인 하세요!');");
	    	out.println("location='main.net';");
	    	out.println("</script>");
	    }else {
	    	
	    	request.setCharacterEncoding("utf-8");
	    	
	    	String m_pwd = request.getParameter("m_pwd");
	    	String m_name = request.getParameter("m_name");
	    	String m_post = request.getParameter("m_post");
	    	String m_roadAddr = request.getParameter("m_roadAddr");
	    	String m_jibunAddr = request.getParameter("m_jibunAddr");
	    	String m_detailAddr = request.getParameter("m_detailAddr");
	    	String m_phone01 = request.getParameter("m_phone01");
	    	String m_phone02 = request.getParameter("m_phone02");
	    	String m_phone03 = request.getParameter("m_phone03");
	    	String mail_id = request.getParameter("mail_id");
	    	String mail_domain = request.getParameter("mail_domain");
	    	String m_birth01 = request.getParameter("m_birth01");
	    	String m_birth02 = request.getParameter("m_birth02");
	    	String m_birth03 = request.getParameter("m_birth03");
	    	
	    	MemberDAOImpl mdao = new MemberDAOImpl();
	    	MemberVO m = new MemberVO();
	    	
	    	m.setM_pwd(m_pwd); m.setM_name(m_name); m.setM_post(m_post); m.setM_roadAddr(m_roadAddr);
	    	m.setM_jibunAddr(m_jibunAddr); m.setM_detailAddr(m_detailAddr); m.setM_phone01(m_phone01);
	    	m.setM_phone02(m_phone02); m.setM_phone03(m_phone03); m.setMail_id(mail_id); m.setMail_domain(mail_domain);
	    	m.setM_birth01(m_birth01); m.setM_birth02(m_birth02); m.setM_birth03(m_birth03); m.setM_id(id);
	    	
	    	int re = mdao.updateMember(m);
	    	
	    	if(re == -1) {
	    		out.println("<script>");
		    	out.println("alert('회원정보 수정 안됨"+m.getM_name()+m.getM_post()+"');");
		    	out.println("history.go(-1);;");
		    	out.println("</script>");
	    	}else {
	    	out.println("<script>");
	    	out.println("alert('회원정보 수정 성공!');");
	    	out.println("location='modify.net';");
	    	out.println("</script>");
	    	}
	    }
		return null;
	}

}
