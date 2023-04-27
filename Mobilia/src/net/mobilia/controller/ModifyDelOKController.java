package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.MemberDAOImpl;
import net.mobilia.vo.MemberVO;

public class ModifyDelOKController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		if(id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요!');");
			out.println("location='login.net';");
			out.println("</script>");
		}else {
			MemberDAOImpl mdao = new MemberDAOImpl();
			request.setCharacterEncoding("UTF-8");
			
			String del_pwd = request.getParameter("del_pwd");
			String m_delcont = request.getParameter("m_delcont");
			
			MemberVO m_pwd = mdao.getMember(id);
			
			if(!m_pwd.getM_pwd().equals(del_pwd)) {
				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다!');");
				out.println("window.location = document.referrer;");//이전 페이지로 이동하면서 새로고침 하기(탈퇴사유 선택된거 리셋)
				out.println("</script>");
			}else {
				MemberVO mvo = new MemberVO();
				mvo.setM_id(id); mvo.setM_delcont(m_delcont);
				mdao.delMem(mvo);
				
				session.invalidate();
				
				out.println("<script>");
				out.println("alert('회원탈퇴가 처리되었습니다.');");
				out.println("opener.parent.location.reload();");//공지창을 부른 부모창을 새로고침함
				out.println("window.close();");//공지창 닫음
				out.println("</script>");
			}
		}
		return null;
	}

}
