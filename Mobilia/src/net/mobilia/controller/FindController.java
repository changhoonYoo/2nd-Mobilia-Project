package net.mobilia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mobilia.dao.ProductDAOImpl;
import net.mobilia.vo.ProductVO;

public class FindController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		String search_text = null;
		
		if(request.getParameter("search_text") != null) {//검색어가 있다면
			search_text = request.getParameter("search_text").trim(); //양쪽 공백을 제거하고 검색어를 가져온다.
		}
		
		ProductDAOImpl pdao = new ProductDAOImpl();
		ProductVO pvo = new ProductVO();
		pvo.setP_name("%"+search_text+"%");
		
		List<ProductVO> plist = pdao.findProductList(pvo);
		
		request.setAttribute("plist", plist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);//false로 하면 기존 매핑주소값을 유지하고 request.setAttribute(
		forward.setPath("./views/product/find_list.jsp");
		return forward;
	}

}
