<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Servlet/JSP 내장 객체와 범위</title>
</head>
<body>
<h1>Servlet/JSP 내장 객체와 범위</h1>
<ul>
<%--자바코드로 page scope 만들기 --%>
<%-- pageContext : page scope 객체--%>
<% 
pageContext.setAttribute("pageMessage", "page scope입니다.");
%>
<%--여기에 만들어놓으면 밑에서 값으로 쓸 수 있다 --%>
<li>page scope : ${pageMessage} </li> 
<li>request scope : ${requestMessage} </li> 
<li>session scope : ${sessionMessage} </li> 
<li>application scope : ${applicationMessage} </li> 
<li>
<a href="/scope/check">scope 확인하기</a> <%--/scope/check라는 요청 보낼 링크 --%>
</li>
</ul>
<hr> <hr>
<h1>범위 별 우선 순위 확인</h1>
<pre>
 - \${key} 작성 시,
 		page -> request -> session -> application 순서로(우선순위 순서대로)
 		내장 객체를 탐색하여
 		일치하는 key가 있으면 해당 key의 value를 출력한다 
 		각각의 scope의 



</pre>
<%
// page scope
pageContext.setAttribute("str", "PAGE"); //이거 주석처리 하면 REQUEST가 나옴
%>
<h2>${str}</h2> <%--PAGE라고 나올거임 --%>
<hr>
<h2>\${ OOOscope.key}</h2>
<pre>
 - OOO은 각 scope 작성(page, request, session, application)
 - 해당 scope에 존재하는 key를 찾아서 value출력(어떤 scope인 것을 찾을 지 scope구분!)
 		(우선순위대로 모든 scope를 순서대로 찾지 않음)

</pre>
<h4>${pageScope.str}</h4>
<h4>${requestScope.str}</h4>
<h4>${sessionScope.str}</h4>
<h4>${applicationScope.str}</h4>
</body>
</html>