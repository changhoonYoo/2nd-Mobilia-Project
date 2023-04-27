package net.mobilia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mobilia.dao.ProductDAOImpl;
import net.mobilia.vo.ProductVO;

public class ListController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		ProductVO p=new ProductVO();
		ProductDAOImpl pdao=new ProductDAOImpl();
		
		String c=request.getParameter("c");
		String state=request.getParameter("state");
		//state에 값에 따라 출력 정보 변경
		
		if(state.equals("all")) { //전체 상품
			int listCount=pdao.getListCount(p,c);
			List<ProductVO> plist=pdao.getProductList(p,c);
			request.setAttribute("plist", plist);
			request.setAttribute("listCount", listCount);
		}else{ //카테고리별 상품
			int listCount=pdao.getListCount(p,c,state);
			List<ProductVO> plist=pdao.getProductList(p,c,state);
			request.setAttribute("plist", plist);
			request.setAttribute("listCount", listCount);
		}
		request.setAttribute("state", state);
		if(state.equals("all") && request.getParameter("m") != null) { //전체상품 중 정렬순서 선택 시
			String m=request.getParameter("m");
			int listCount=pdao.getListCount(p,c);
			List<ProductVO> plist=pdao.getProductListMethod(p,c,m);
			request.setAttribute("plist", plist);
			request.setAttribute("listCount", listCount);
		}
		if(!state.equals("all") && request.getParameter("m") != null) { //카테고리 선택, 정렬순서 선택 시
			String m=request.getParameter("m");
			int listCount=pdao.getListCount(p,c,state);
			List<ProductVO> plist=pdao.getProductListMethod(p,c,state,m);
			request.setAttribute("plist", plist);
			request.setAttribute("listCount", listCount);
		}
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		if(c.equals("bed")) {
			forward.setPath("./views/product/list_bed.jsp"); 
		}else if(c.equals("sofa")) {
			forward.setPath("./views/product/list_sofa.jsp"); 
		}else if(c.equals("table")) {
			forward.setPath("./views/product/list_table.jsp"); 
		}else if(c.equals("chair")) {
			forward.setPath("./views/product/list_chair.jsp"); 
		}else if(c.equals("cabinet")) {
			forward.setPath("./views/product/list_cabinet.jsp"); 
		}
		return forward;
	}
	
	
}

