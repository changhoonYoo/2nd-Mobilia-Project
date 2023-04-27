package net.mobilia.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.ProductDAOImpl;
import net.mobilia.dao.ReviewDAOImpl;
import net.mobilia.vo.ProductVO;
import net.mobilia.vo.QnaBoardVO;
import net.mobilia.vo.ReviewVO;

public class ProductInfoController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int p_no =Integer.parseInt(request.getParameter("p_no"));
		
		ProductDAOImpl pdao=new ProductDAOImpl();
		
		ProductVO pv=pdao.getProductInfo(p_no); //상품 정보 불러오기
		
		String colorList[] = pv.getP_color().split(",");
		String sizeList[] = pv.getP_size().split(",");
		String p_info = pv.getP_info().replace("\n","<br>");
		int brCount = p_info.length() - p_info.replace("<br>","").length();
		if(brCount<4) {
			p_info= p_info+"<br><br><br>";
		}else if(brCount<8) {
			p_info= p_info+"<br><br>";
		}else if(brCount<12) {
			p_info= p_info+"<br>";
		}
		
		int page=1;//쪽번호
		int limit=5;//한 페이지에 보여지는 목록개수
		
		if(request.getParameter("page") != null) {//get으로 전달된 페이지 번호가 있는경우 실행
			
			page=Integer.parseInt(request.getParameter("page"));
			//페이지 번호를 정수 숫자로 변경
		}
		
		
		ReviewDAOImpl rdao=new ReviewDAOImpl();
		int listcount=rdao.getReviewCount(p_no);//리뷰 개수
		
		List<ReviewVO> rlist=rdao.getReviewList(page,limit,p_no);//리뷰 목록
		
		/* 페이징 연산 */
		int maxpage=(int)((double)listcount/limit+0.95);//총 페이지 수
		
		int startpage=(((int)((double)page/5+0.9))-1)*5+1;//시작 페이지
		
		int endpage=maxpage;//마지막 페이지
		
		if(endpage > startpage+5-1) endpage=startpage+5-1;

		request.setAttribute("pv", pv);
		request.setAttribute("colorList", colorList);
		request.setAttribute("sizeList", sizeList);
		request.setAttribute("p_info", p_info);
		request.setAttribute("page", page);//쪽번호
		request.setAttribute("startpage", startpage);//시작 페이지
		request.setAttribute("endpage", endpage);//마지막 페이지
		request.setAttribute("maxpage", maxpage);//총 페이지 수
		request.setAttribute("listcount", listcount);
		request.setAttribute("rlist", rlist);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./views/product/product_info.jsp"); 
		return forward;
	}
}
