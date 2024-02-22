<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이름 찾기 결과</title>
</head>
<body>
<h1>${message}</h1>
(page -> request -> session -> application)
<%--이렇게 쓰면 page -> request -> session -> application 순서로 탐색하면서 message있는지 검사해서 가장 먼저 있는 것의 message를 보여줌 --%>

<h1>${requestScope.message}</h1>
(request scope의 message값을 특정해서 얻어옴)
</body>
</html>