package net.mobilia.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.ProductDAOImpl;
import net.mobilia.vo.ProductVO;

public class InquiryWriteController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		
		if(id==null) {
			out.println("<script>");
			out.println("alert('로그인 후 작성해주세요!');");
			out.println("self.close();");
			out.println("</script>");
		}else {
			int p_no =Integer.parseInt(request.getParameter("p_no"));
			
			ProductDAOImpl pdao=new ProductDAOImpl();
			
			ProductVO pv=pdao.getProductInfo(p_no);
			
			request.setAttribute("pv", pv);
			ActionForward forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./views/product/inquiry_write.jsp");
			
			return forward;
		}
		return null;
	}
}
