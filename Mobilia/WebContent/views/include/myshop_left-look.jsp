<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="../include/header.jsp" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<link rel="stylesheet" type="text/css" href="./css/member/myshop.css">
<link rel="stylesheet" type="text/css" href="./css/member/modify.css">
 <div class="Myshop">
 <%------------ 화면좌측 카테고리 영역시작 ------------%>
  <div id="myshop_left-look">
   <div class="myshop_category-box">
    <h4>주문조회</h4>
    <ul>
     <li><a href="orderlist.net">주문조회/배송조회</a></li>
     <li><a href="orderlist.net">취소/반품/교환내역</a></li>
     <li><a href="orderlist.net">과거주문내역</a></li>
    </ul>
   </div>
   <div class="myshop_category-box">
    <h4>혜택내역</h4>
    <ul>
     <li><a href="#">적립금 내역</a></li>
     <li><a href="#">회원등급적립내역</a></li>
     <li><a href="#">쿠폰관리</a></li>
    </ul>
   </div>
   <div class="myshop_category-box">
   <h4>활동관리</h4>
   <ul>
    <li><a href="#">1:1 문의관리</a></li>
    <li><a href="#">나의 게시물 관리</a></li>
   </ul>
   </div>
   <div class="myshop_category-box" style="border:none;">
   <h4>나의정보</h4>
   <ul>
    <li><a href="#">관심상품</a></li>
    <li><a href="modify.net">회원정보수정</a></li>
   </ul>
   </div>
  </div>
 <%------------ 화면좌측 카테고리 영역끝 ------------%>
 <div id="myshop-center">
  <div id="myshop-title-area">