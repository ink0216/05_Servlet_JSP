<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>피자 주문</title>
<!-- href에 css의 파일주소를 쓸까 요청주소를 쓸까? -->
<!-- 응답 : 서버가 클라이언트에게 HTML 코드를 보낸다. 클라이언트의 브라우저가 HTML코드 해석해서 화면만듦
						->클라이언트가 서버에 또 CSS파일을 요청해서 받아와야함(요청주소를 써야 함)
						서버가 처음에는 HTML코드를 보내주는데 CSS파일도 요청하므로 서버의 어느 위치에 CSS가 있는지 요청주소를 쓰면
						클라이언트가 그 파일 다운로드받아와서 해석함 -->
						
		<!-- CSS파일이 위치한 서버 주소를 작성  -->
		<!-- css파일은 굳이 안보이게 할 필요 없음 -> webapp폴더 안에 resources 폴더안에 css폴더 만듦 -->
		<!-- "/" (webapp) 폴더를 기준으로 원하는 css 파일 까지의 경로를 작성 -->
<link rel="stylesheet" href="/resources/css/pizza.css">
<!-- WEB-INF 폴더 아니므로 주소창에 파일 경로 쓰면 그 자체로 주소가 됨 -->
</head>
<body>

<main>
<h1> <%=request.getAttribute("myName") %> 피자 주문 페이지 </h1>
<!-- 요청이 JSP로 전달돼서 만들어져서 Servlet을 거쳐서 내보냄 -->
<form action="/pizza/order" method="POST">

		<div class="row">
			<label>피자 :</label> 
			<select name="pizza">
			<!-- option 태그에 value속성 없으면 태그 내부의값이 자동으로 value로 됨  -->
				<option>치즈 피자-8000</option>
				<option>콤비네이션 피자-9000</option>
				<option>쉬림프 피자-15000</option>
				<option>더블 포테이토 피자-14000</option>
				<option>하와이안 피자-12000</option>
			</select>
		</div>

		<div class="row">
			<label>사이즈 : </label>
			 R <input type="radio" name="size" value="R"checked> 
			 L(+4000) <input type="radio" name="size" value="L">
		</div>

		<div class="row">
			<label>수량</label>
			<button type="button">-</button>
			<input type="number" name="amount" min="1" max="9" value="1">
			<button type="button">+</button>
		</div>

		<button class="order-btn">주문하기</button>
	</form>
	</main>
<!-- js파일이 위치한 서버 주소를 작성  -->
		<!-- js파일은 굳이 안보이게 할 필요 없음 -> webapp폴더 안에 resources 폴더안에 js폴더 만듦 -->
		<!-- "/" (webapp) 폴더를 기준으로 원하는 js 파일 까지의 경로를 작성 -->
	<script src="/resources/js/pizza.js"></script>
</body>
</html>