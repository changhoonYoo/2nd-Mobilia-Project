package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.MemberDAOImpl;
import net.mobilia.vo.MemberVO;

public class LoginOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		request.setCharacterEncoding("utf-8");
		
		String m_id=request.getParameter("m_id");
		String m_pwd=request.getParameter("m_pwd");
		
		MemberDAOImpl mdao=new MemberDAOImpl();
		MemberVO mvo = mdao.loginCheck(m_id);//로그인 인증
		
		if(mvo == null) {
			out.println("<script>");
			out.println("alert('가입 안된 회원입니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(!mvo.getM_pwd().equals(m_pwd)){
				out.println("<script>");
				out.println("alert('비번이 다릅니다!');");
				out.println("history.go(-1);");
				out.println("</script>");
			}else {
				HttpSession session=request.getSession();//세션 객체 생성
				session.setAttribute("id",m_id);
				ActionForward forward=new ActionForward();
				forward.setRedirect(true);//새로운 매핑주소로 이동
				forward.setPath("main.net");//메인화면으로 이동
				return forward;
			}
		}
		return null;
	}
}

