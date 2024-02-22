<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>redirect 확인하기</title>
</head>
<body>
<h1>${message}</h1>
처음 들어가면 null이어서 el에서는 빈칸으로 나옴
<%--메세지를 한번만 쓰고 안보이게 하고싶은 경우->자바 코드 이용 --%>
<% //session에 존재하는 속성 중 message 속성을 제거 -> 딱 한번만 
session.removeAttribute("message");//메세지 속성을 제거한다

//redirect 시 데이터를 유지하기 위해 session을 이용했는데,
//1회성으로 사용 불가능한 문제가 생김!!!(계속 유지되고 있음...)
//	->1회 출력 후 session에서 message 데이터를 제거함!!!(한번만 출력하고 없애야즹~)
//스프링에서는 이런 용도의 객체가 따로 존재함!
%>
<h1>회원가입 화면 만들기!!!</h1>
<form action="/signup" method="POST">

	<div>
	ID : <input type="text" name="id">
	</div>
	
	<div>
	PW : <input type="password" name="pw">
	</div>
	
	<div>
	PW CHECK : <input type="password" name="pwCheck">
	</div>
	<button>회원 가입</button>
	<%--세 개의 값이 파라미터로 전달된다 --%>
</form>
</body>
</html>