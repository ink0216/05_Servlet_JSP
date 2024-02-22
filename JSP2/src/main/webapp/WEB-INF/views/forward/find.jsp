<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward 확인하기</title>
</head>
<body>
  <form action="/find" method="POST">
  <!-- 파라미터로 이름 하나 넘어가고 키값이 findName ->getParameter로 얻어오기 -->
       <h3>검색할 이름을 입력하세요</h3>
       <input type="text" name="findName">
       <button>검색</button>
   </form>

</body>
</html>