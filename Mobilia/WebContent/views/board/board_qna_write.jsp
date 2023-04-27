<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" type="text/css" href="./css/board/board_write.css">
<div id="board-wrap">
<div class="title-area">
	<h2>Community</h2>
	<label class="write-info-text">Community 게시글을 작성하는 공간입니다.</label>
	<hr>
</div>
<form name="b" method="post" action="qna_write_ok.net">
	<div class="board-write-area">
		
			<table>
				<tr>
					<th>글쓴이</th>
					<td><label>${id}</label></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input id="board_title" name="board_title"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea id="board_cont" name="board_cont"></textarea></td>
				</tr>
			</table>
		
	</div>
	<div class="write-button-area">
	 <input type=submit id="write-submit-button" value="게시글등록">
	 <input type="reset" id="write-list-button" value="게시글목록" onclick="location='qna.net';">
	</div>
</form>
</div>
<jsp:include page="../include/footer.jsp" />