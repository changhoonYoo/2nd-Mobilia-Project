package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mobilia.dao.MemberDAOImpl;
import net.mobilia.vo.MemberVO;

public class FindPwdOKController implements Action {
	

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		//브라우저에 출력되는 문자/태그 언어코딩 타입을 설정
		PrintWriter out=response.getWriter();//출력 스트림 생성
		MemberDAOImpl mdao=new MemberDAOImpl();
		MemberVO m=new MemberVO();
		ActionForward forward=new ActionForward();
		
		request.setCharacterEncoding("UTF-8");//post방식으로 전달된 한글을 안깨지게 한다.
		
		String pwd_id = request.getParameter("m_id");
		String pwd_name = request.getParameter("m_name");
		String pwd_email = request.getParameter("m_email");
		
		String[] email = pwd_email.split("@");
		
		m.setM_id(pwd_id); m.setM_name(pwd_name); m.setMail_id(email[0]); m.setMail_domain(email[1]);
		
		MemberVO pm=mdao.pwdMember(m);//아이디와 회원이름을 기준으로 오라클로 부터 회원정보를 검색
		
		if(pm == null) {
			out.println("<script>");
			out.println("alert('회원정보를 찾을 수 없습니다!');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			request.setAttribute("find_pwd",pm.getM_pwd());
			forward.setRedirect(false);
			forward.setPath("./views/find_account/find_pwd_ok.jsp");
			return forward;
		}
		return null;
	}

}


