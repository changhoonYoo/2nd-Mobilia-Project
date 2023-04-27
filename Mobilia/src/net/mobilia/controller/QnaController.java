package net.mobilia.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.QnaBoardDAOImpl;
import net.mobilia.vo.QnaBoardVO;

public class QnaController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int page = 1;
		int maxview=10;
		
		String find_field = null;
		String find_name = null;
		
		if(request.getParameter("page") != null) {//get으로 전달된 페이지 번호가 있으면
			page = Integer.parseInt(request.getParameter("page"));//페이지 번호를 정수숫자로 변환
		}
		
		find_field = request.getParameter("find_field");
		if(request.getParameter("find_name") != null) {//검색어가 있다면
			find_name = request.getParameter("find_name").trim(); //양쪽 공백을 제거하고 검색어를 가져온다.
		}
		
		QnaBoardVO findqna = new QnaBoardVO();
	    findqna.setFind_field(find_field); findqna.setFind_name("%"+find_name+"%");
	    //%는 sql 와일드 카드문자
		
		QnaBoardDAOImpl qdao = new QnaBoardDAOImpl();
		
		int listcount = qdao.getListCount(findqna);
		List<QnaBoardVO> qlist = qdao.getBoardData(page, maxview, findqna);
		
		//페이지 연산
		int maxpage=(int)((double)listcount/maxview+0.95);//총 페이지수
        int startpage=(((int)((double)page/10+0.9))-1)*10+1;//시작페이지
        int endpage=maxpage;//마지막 페이지
        if(endpage>startpage+10-1) endpage=startpage+10-1;
		
		request.setAttribute("qlist", qlist);
		request.setAttribute("page", page);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("find_field", find_field);
		request.setAttribute("find_name", find_name);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);//false로 하면 기존 매핑주소값을 유지하고 request.setAttribute(
		forward.setPath("./views/board/board_qna.jsp");
		return forward;
	}

}
