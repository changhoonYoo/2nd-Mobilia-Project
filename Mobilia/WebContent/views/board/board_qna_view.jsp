<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" type="text/css" href="./css/board/board_write.css">
<link rel="stylesheet" type="text/css" href="./css/board/board_view.css">
<div id="board-wrap">
<div class="title-area">
	<h2>Community</h2>
	<label class="write-info-text">Community 게시글 내용을 볼수있는 공간입니다.</label>
	<hr>
</div>
<div class="board-state-button-area">
<c:if test="${qvo.board_name == id}">
 <span id="state-left">
  
  <a href="qna_view.net?board_no=${qvo.board_no}&page=${page}&state=edit" class="edit">수정</a>
  /
  <a href="qna_view.net?board_no=${qvo.board_no}&page=${page}&state=del" class="del">삭제</a>
 </span>
</c:if>
 <span id="state-right">
  <input type="button" value="목록" onclick="location='qna.net?page=${page}';"/>
 </span>
</div>
	<div class="board-write-area">
			<table>
				<tr>
					<th>글쓴이</th>
					<td><label>${qvo.board_name}</label></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><label>${qvo.board_title}</label></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><label>${board_cont}</label></td>
				</tr>
			</table>
	</div>
</div>
<jsp:include page="../include/footer.jsp" />