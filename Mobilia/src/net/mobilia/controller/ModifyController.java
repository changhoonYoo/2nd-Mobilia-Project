package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.MemberDAOImpl;
import net.mobilia.vo.MemberVO;

public class ModifyController implements Action {

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
	    	out.println("alert('다시 로그인 하세요!');");
	    	out.println("location='login.net';");
	    	out.println("</script>");
	    }else {
	    	
	    
		String[] phone = {"010", "011", "016", "017", "018", "019"};
		String[] email = {"naver.com", "daum.net", "gmail.com", "nate.com", "직접 입력"};
		request.setAttribute("phone", phone);
		request.setAttribute("email", email);
		
		MemberVO mvo = mdao.getMember(id);//회원정보창에 뿌리기 위한 회원정보를 가져옴
		
		request.setAttribute("mvo", mvo);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./views/member/modify.jsp"); 
		return forward;
	    }
	    return null;
	}

}
