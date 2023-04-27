<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" type="text/css" href="./css/board/board_write.css">
<link rel="stylesheet" type="text/css" href="./css/board/board_view.css">

<script src="./js/jquery.js"></script>
<script>
 function del_check(){
	 if($.trim($('#board_name').val()) == ''){
	       alert('아이디를 입력하세요!');
	       $('#board_name').val('').focus();
	       return false;
	    }
 }
</script>

<div id="board-wrap">
<div class="title-area">
	<h2>Community</h2>
	<label class="write-info-text">Community 게시글 내용을 삭제하는 공간입니다.</label>
	<hr>
</div>

	<div class="board-write-area">
	
	<form method="post" action="qna_del.net?page=${page}"
         onsubmit="return del_check();">

         
         <input type="hidden" name="board_no" value="${qvo.board_no}">
         <input type="hidden" name="page" value="${page}" />
	
			<table>
				<tr>
					<th>아이디</th>
					<td><label><input name="board_name" id="board_name"placeholder="삭제를 원하시면 아이디를 입력하세요"></label></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><label><input name="board_title" id="board_title" value="${qvo.board_title}" readonly></label></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea id="board_cont" name="board_cont" readonly>${board_cont}</textarea></td>
				</tr>
			</table>
			<div class="board-state-button-area">
				<c:if test="${qvo.board_name == id}">
					<span id="state-left"> <input type="submit" value="삭제"  />
					</span>
				</c:if>
				<span id="state-right"> <input type="button" value="취소"
					onclick="location='qna.net?page=${page}';" />
				</span>
			</div>
		</form>
	</div>
</div>

<jsp:include page="../include/footer.jsp" />