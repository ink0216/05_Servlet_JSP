<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
</head>
<body>
<h1>${searchMessage}</h1>
둘 다 되는 거임
<h1>${requestScope.searchMessage}</h1>
근데 이렇게 특정지어서 requestScope에 있는 searchMessage출력할거야 해도 됨
</body>
</html>