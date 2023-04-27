<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../include/header.jsp" />
<link rel="stylesheet" type="text/css" href="./css/board/board.css">
<div class="qna-board-area">
<form method="get" action="qna.net">
	<div class="title-area">
		<h2>Community</h2>
		<label class="info-text">Community 게시글을 볼수있는 공간입니다.</label> <input
			type="button" id="write-button" name="write-button" value="글쓰기"
			onclick="location='qna_write.net';">
		<hr>
	</div>
	<div class="qna-find-area">
	 <select name="find_field">
	  <option value="board_name"
	  <c:if test="${find_field =='board_name'}">${'selected'}</c:if>>작성자</option>
	  <option value="board_title"
	  <c:if test="${find_field =='board_title'}">${'selected'}</c:if>>제목</option>
	  <option value="board_cont"
	  <c:if test="${find_field =='board_cont'}">${'selected'}</c:if>>내용</option>
	 </select>
	 <input type="search" id="find_name" name="find_name">
	 <input type="submit" id="find_button" value="검색">
	</div>
<c:if test="${!empty qlist}">
 <c:forEach var="q" items="${qlist}">
	<div class="write-list-area">
		<div id="board-title-area">
			<label><a href="qna_view.net?board_no=${q.board_no}&page=${page}&state=cont">${q.board_title} (${q.reply_hit})</a></label><br>
		</div>
		<div id="board-data-area">
			<label>${q.board_name}</label> | <label>${q.board_date}</label> | <label>조회수 ${q.board_hit}</label>
		</div>
	</div>
 </c:forEach>
</c:if>

   <%--검색전후 페이징(쪽나누기) --%>
   <div id="bList_paging">
    <%--검색 전 페이징 --%>
    <c:if test="${(empty find_field) && (empty find_name)}"> <%--검색필드와 검색어가 없는 경우 --%>
     <c:if test="${page <= 1}">
      [이전]&nbsp;
     </c:if>
     <c:if test="${page>1}">
      <a href="qna.net?page=${page-1}" class="next-button">[이전]</a>&nbsp;
     </c:if>
     
     <%--현재 쪽번호 출력 --%>
     <c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
       <c:if test="${a == page}"> <%-- 현재 페이지가 선택된 경우 --%>
        [ ${a} ]
       </c:if>
       <c:if test="${a != page}"> <%--현재 쪽번호가 선택 안 된 경우--%>
        <a href="qna.net?page=${a}">[ ${a} ]</a>&nbsp;
       </c:if>
     </c:forEach>
       
    
    <c:if test="${page >= maxpage}">
      [다음]
    </c:if>
    <c:if test="${page < maxpage}">
     <a href="qna.net?page=${page+1}" class="next-button">[다음]</a>
    </c:if>
   </c:if>
    
    <%--검색이후 페이징(쪽나누기) --%>
    <c:if test="${(!empty find_field) && (!empty find_name)}"> <%--검색필드와 검색어가 있는 경우 --%>
     <c:if test="${page <= 1}">
      [이전]&nbsp;
     </c:if>
     <c:if test="${page>1}">
      <a href="qna.net?page=${page-1}&find_field=${find_field}&find_name=${find_name}" class="next-button">[이전]</a>&nbsp;
                                             <%-- &(엠퍼센트) 구분기호로 구분하면서 find_field=검색필드&find_name=
                                             검색어를 get방식으로 전달해야 검색 이후 페이징 목록을 유지한다. 그렇지 않으면 검색전 전체 페이징 목록으로 이동해서
                                             검색 효과가 사라진다. --%>
     </c:if>
     
     <%--현재 쪽번호 출력 --%>
     <c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
       <c:if test="${a == page}"> <%-- 현재 페이지가 선택된 경우 --%>
        [ ${a} ]
       </c:if>
       <c:if test="${a != page}"> <%--현재 쪽번호가 선택 안 된 경우--%>
        <a href="qna.net?page=${a}&find_field=${find_field}&find_name=${find_name}">[ ${a} ]</a>&nbsp;
       </c:if>
     </c:forEach>      
    
    <c:if test="${page >= maxpage}">
      [다음]
    </c:if>
    <c:if test="${page < maxpage}">
     <a href="qna.net?page=${page+1}&find_field=${find_field}&find_name=${find_name}" class="next-button">[다음]</a>
    </c:if>
   </c:if> 
   </div>
   </form>
</div>
<jsp:include page="../include/footer.jsp" />