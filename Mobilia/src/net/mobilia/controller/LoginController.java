package net.mobilia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./views/member/login.jsp");//뷰페이지 경로 설정.
		return forward;
	}

}
