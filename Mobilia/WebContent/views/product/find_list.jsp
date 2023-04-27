<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../include/header.jsp" />
<div class="list_wrap">
<div id="list_title">
			<h1 style="color:#333; font-size:32px;">검색결과</h1>
		</div>
<c:if test="${!empty plist}">
<div id="product_list">
		<c:forEach var="p" items="${plist}">
				<ul id="list_ul">
					<li id="list">
						<div id="list_img">
							<a href="product_info.net?p_no=${p.p_no}"><img
								src="./upload${p.p_img1}" width="300" height="300"
								onmouseover="this.src='./upload${p.p_img2}'"
								onmouseout="this.src='./upload${p.p_img1}'"></a>
						</div>
						<ul id="list_info">
							<li id="list_name"><a href="#"><span id="p_before_price"
									style="font-size: 15px; color: #333333; font-weight: bold;">${p.p_name}
								</span></a></li>
							<li id="list_price"><span
								style="font-size: 14px; color: #a1a1a1; font-weight: bold; text-decoration: line-through;">
									<fmt:formatNumber value="${p.p_before_price}"
										pattern="###,###,###" />원
							</span></li>
							<li id="list_price"><span
								style="font-size: 14px; color: #971215; font-weight: bold;"><fmt:formatNumber
										value="${p.p_price}" pattern="##,###,###" />원</span> <span
								id="discount_rate"
								style="font-size: 14px; color: #045443; font-weight: bold;">${p.p_rate}%</span></li>
						</ul>
					</li>
				</ul>
				
		</c:forEach>
		</div>
</c:if>
<c:if test="${empty plist}">
		<div style="margin-top:120px;">
			<p style="font-size: 24px; font-weight: 700; color: #a3a3a3; text-align: center;">
				등록된 상품이 없습니다.</p>
		</div>
</c:if>
</div>
<jsp:include page="../include/footer.jsp" />