<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
    <!--  JSP지시자 태그-->
    <!-- HTML(또는 마크업 언어)의 주석 -->
    <%-- JSP 전용 주석--%>
    <%--1) JSP에 작성된 Java코드를 주석 처리할 때 사용 
    			(JSP에는 HTML코드와 Java코드가 섞여있는데 그 중 Java코드에 주석 사용할 때 사용)
    		2) JSP 전용 주석은 , HTML 전용 주석과 달리 컴파일 단계에서 걸러지기 때문에
    				응답 화면(브라우저 개발자도구)에서 볼 수 없음
    					-> 보여주고 싶지 않은 주석 작성 시 사용함--%> 
    				
    <%--  
    <%@  %> : JSP 지시자(JSP 관련 기본 설정하는 태그)
    --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
<!-- EUC-KR : 이걸로 하면 안됨 -> UTF-8로 해야함 -->
<title>회원 가입 결과</title>
</head>
<body>
<!-- Emmet적용 안됨 ->자동완성하기(ctrl space) -->
<!-- JSP : Java기술(서버사이드) -->
<h2>회원 가입 결과 페이지 입니다</h2>
<%--
	request 변수 == 전달 받은 HttpServletRequest 객체를 참조하는 변수
	<% %> 태그 : Scriptlet(스크립틀릿) 태그
								JSP에서 자바 코드를 작성할 수 있게 하는 태그
								이 태그 안에 Java 코드 쓰면 됨
	<%= %> 태그 : Expression(표현식) 태그
								JSP에서 자바에 있는 값을 HTML 화면에 출력(표현)하는 태그
								(Java 값 -> HTML 코드로 변환해서 해석되게 만듦)
								: 옛날 기술이고, 대체 라이브러리 많아서 그걸 사용
 --%>
 <% 
 //전달받은 파라미터 얻어오기
 String inputId = request.getParameter("inputId");
 String inputPw = request.getParameter("inputPw");
 String inputPwCheck = request.getParameter("inputPwCheck");
 String inputName = request.getParameter("inputName");
 String inputAge = request.getParameter("inputAge");
 %>
 <%-- <%=inputId %>
 <%=inputPw %>
 <%=inputPwCheck %>
 <%=inputName %>
 <%=inputAge %> --%>
 <% if(!inputPw.equals(inputPwCheck)){%>
 <h3>비밀번호가 일치하지 않습니다</h3>
 <%} else if(Integer.parseInt(inputAge)<14){%>
 <h3>14세 이상만 가입 가능합니다</h3>
 <% }else{ %>
 <h3>가입 성공!!!</h3>
 <ul>
 <li>ID : <%=inputId %> </li>
 <li>PW : <%=inputPw %> </li>
 <li>NAME : <%=inputName %> </li>
 <li>AGE : <%=inputAge %> </li>
 </ul>
 <%} %>
 }
</body>
</html>