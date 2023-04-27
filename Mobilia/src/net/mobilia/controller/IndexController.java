package net.mobilia.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.ProductDAOImpl;
import net.mobilia.vo.ProductVO;

public class IndexController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();
		
		String id=(String)session.getAttribute("id");
		
		ProductDAOImpl pdao=new ProductDAOImpl();
		
		
		List<ProductVO> nlist=pdao.getNewItem(); //New Item 8개
		List<ProductVO> mlist=pdao.getMdChoice();//MD's Choice 8개
		
		request.setAttribute("nlist", nlist);
		request.setAttribute("mlist", mlist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./views/index.jsp"); 
		return forward;
	}
}
