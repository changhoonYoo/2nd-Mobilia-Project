<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="./js/jquery.js"></script>
<script src="./js/admin/admin_product.js"></script>
<title></title>
</head>
<body>
<div>
	<h2>상품 등록</h2>
	<form method="post" action="product_join_ok.net" 
	onsubmit="return product_join_check();" enctype="multipart/form-data">
		<%-- 
			파일을 첨부해서 서버에 업로드 하려면 추가 해야할 코드) => 자료실 만들 때 필요한 코드
			 1. 폼태그 내에 enctype="multipart/form-data" 속성을 지정해야 한다. 
			 2. method="post"방식만 가능하다. get방식은 안된다. 그러므로 form태그 내에서 
			 	method속성을 생략하면 안된다.(기본값 : get)
			 3. 파일을 첨부해서 서버에 업로드하려면 일반적으로 라이브러리가 필요하다.
		--%>
			<table>
				<tr>
					<th>상품명</th>
					<td>
						<input name="p_name" id="p_name" size="14">
					</td>
				</tr>
				<tr>
					<th>판매가</th>
					<td><input name="p_before_price" id="p_before_price" size="14">원</td>
				</tr>
				<tr>
					<th>할인가</th>
					<td><input name="p_price" id="p_price" size="14">원</td>
				</tr>
				<tr>
					<th>상품 수량</th>
					<td><input name="p_amount" id="p_amount" size="14">개</td>
				</tr>
				
				<tr>
					<th>상품 이미지1</th>
					<td><input type="file" name="p_img1" id="p_img2" required="required"></td>
				</tr>
				
				<tr>
					<th>상품 이미지2</th>
					<td><input type="file" name="p_img2" id="p_img2" required="required"></td>
				</tr>
				
				<tr>
					<th>MD's Choice</th>
					<td><input type="checkbox" name="p_choice" id="p_choice" value="1">등록</td>
				</tr>
				
				<tr>
					<th>상품 분류</th>
					<td>
						<select name="p_class" id="p_class" onchange="categoryChange(this)">
							<option value="">선택</option>
							<option value="bed">Bed</option>
							<option value="sofa">Sofa</option>
							<option value="table">Table</option>
							<option value="chair">Chair</option>
							<option value="cabinet">Cabinet</option>
						</select>
				</tr>
				<tr>	
					<th>카테고리</th>
					<td>
						<select name="p_category" id="p_category">
						</select>
					</td>
				</tr>
				<tr>
					<th>상품설명</th>
					<td><textarea rows="4" cols="34" id="p_info" name="p_info"></textarea></td>
				</tr>
				<tr>
					<th>색상</th>
					<td>
					<select name="color_count" id="color_count">
						<option value="">선택</option>
						<option value="1">1개</option>
						<option value="2">2개</option>
						<option value="3">3개</option>
						<option value="4">4개</option>
						<option value="5">5개</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>색상 입력</th>
					<td><div id="demo"></div>
					<input type="hidden" name="p_color" id="p_color">
					</td>
				</tr>
				<tr>
					<th>사이즈</th>
					<td>
					<select name="size_count" id="size_count">
						<option value="">선택</option>
						<option value="1">1개</option>
						<option value="2">2개</option>
						<option value="3">3개</option>
						<option value="4">4개</option>
						<option value="5">5개</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>사이즈 입력</th>
					<td><div id="demo1"></div>
					<input type="hidden" value="" name="p_size" id="p_size">
					</td>
				</tr>
			</table>
			
			<div>
				<input type="submit" value="등록">
				<input type="reset" value="취소" onclick="$('p_name').focus();">
			</div>
		</form>
	</div>
</body>
</html>