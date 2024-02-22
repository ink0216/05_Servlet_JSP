<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 범위(scope) 확인</title>
</head>
<body>
  <h3>page scope :        ${pageMessage}</h3>

  <h3>request scope :     ${requestMessage}</h3>
	(방금 누른 버튼 눌러서 새 요청 만들어 여기 들어와서 request 객체 없어진거임)
  <h3>session scope :     ${sessionMessage}</h3>
  (시크릿 창 등 다른 브라우저에서 현재 페이지 주소로 접속하면 session이 보이지 않음)
  (세션이 다시 만들어져서 A라는 기존 세션에는 메세지 들어있었는데 B라는 새 세션에는 메세지 안들어있음)

  <h3>application scope : ${applicationMessage}</h3>
	(얘는 어디서든지 항상 쓸 수 있다)
</body>
</html>