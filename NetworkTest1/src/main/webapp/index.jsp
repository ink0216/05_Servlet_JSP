<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네트워크 프로그래밍_1</title>
</head>
<body>
240223시험
	index.jsp 입니다.
	html이든 jsp이든 index 제목 가진 페이지를 메인페이지로 삼음!!
	
	<hr>
	
	인덱스와 멤버 디티오에는 오류 없음!!고칠 것 없다
[요구사항]

1. 입력 받은 아이디가 List에 없다면 ->forward(req)
List에 추가 후 "/WEB-INF/views/success.jsp"로 forward 수행.
forward 시 "(아이디)/(이름) 님이 가입 되었습니다 (비밀번호 : OOOO)"  
메시지도 전달하여 응답 화면에서 출력

2. 입력 받은 아이디가 List에 있다면 ->redirect(req X)
"/" 로 redirect 수행.
redirect 시 "(아이디)은/는 이미 존재하는 아이디 입니다."  메시지도 전달하여 응답 화면에서 출력

	<hr>
	<hr>
	  <h1>messge : ${message}</h1>
    <hr>
    
    <h1>회원가입</h1>
    <form action="/join" method="post">
        아이디 : <input type="text" name="memberId" required><br>
        비밀번호 : <input type="password" name="memberPw" required><br>
        이름 : <input type="text" name="memberName" required><br>
        <button>가입하기</button>
    </form>



</body>
</html>