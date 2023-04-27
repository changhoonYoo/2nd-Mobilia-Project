<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" type="text/css" href="./css/board/board_write.css">
<link rel="stylesheet" type="text/css" href="./css/board/board_view.css">

<script src="./js/jquery.js"></script>
<script>
 function edit_check(){
	 $board_title=$.trim($("#board_title").val());
		if($board_title == ''){
			alert('글제목을 입력하세요!');
			$("#board_title").val("").focus();
			return false;
		}
		
	$board_title = $.trim($("#board_cont").val());
		if ($board_title == '') {
			alert('글내용을 입력하세요!');
			$("#board_cont").val("").focus();
			return false;
		}
	}
</script>

<div id="board-wrap">
<div class="title-area">
	<h2>Community</h2>
	<label class="write-info-text">Community 게시글 내용을 수정하는 공간입니다.</label>
	<hr>
</div>

	<div class="board-write-area">
	
	<form method="post" action="qna_edit.net?page=${page}"
         onsubmit="return edit_check();">

         
         <input type="hidden" name="board_no" value="${qvo.board_no}">
	
			<table>
				<tr>
					<th>글쓴이</th>
					<td><label><input name="board_name" value="${qvo.board_name}" readonly></label></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><label><input name="board_title" id="board_title" value="${qvo.board_title}"></label></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td><textarea id="board_cont" name="board_cont">${board_cont}</textarea></td>
				</tr>
			</table>
			<div class="board-state-button-area">
				<c:if test="${qvo.board_name == id}">
					<span id="state-left"> <input type="submit" value="수정"  />
					</span>
				</c:if>
				<span id="state-right"> <input type="button" value="목록"
					onclick="location='qna.net?page=${page}';" />
				</span>
			</div>
		</form>
	</div>
</div>

<jsp:include page="../include/footer.jsp" />