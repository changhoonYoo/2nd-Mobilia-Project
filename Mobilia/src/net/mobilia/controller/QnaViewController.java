package net.mobilia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mobilia.dao.QnaBoardDAOImpl;
import net.mobilia.vo.QnaBoardVO;

public class QnaViewController implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession();

		String id=(String)session.getAttribute("id");

		int board_no = Integer.parseInt(request.getParameter("board_no"));
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		String state = request.getParameter("state");

		QnaBoardDAOImpl qdao = new QnaBoardDAOImpl();
		if(state.equals("cont")) {
			qdao.updatetHit(board_no);
		}

		QnaBoardVO qvo = qdao.getBoardCont(board_no);
		String board_cont = qvo.getBoard_cont().replace("\n","<br>");
		//텍스트 에어리어에서 엔터키 친부분을 다음줄로 줄바꿈해서 보여지게
		
		request.setAttribute("qvo", qvo);
		request.setAttribute("board_cont", board_cont);
		request.setAttribute("page", page);
		request.setAttribute("id", id);
		
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		
		if(state.equals("cont")) {//내용보기 일때
			forward.setPath("./views/board/board_qna_view.jsp");
		}else if(state.equals("edit")) {//수정 폼일때
			forward.setPath("./views/board/board_qna_edit.jsp");
		}else if(state.equals("del")) {//삭제 폼 일때
			forward.setPath("./views/board/board_qna_del.jsp");
		}
		return forward;
	}

}
