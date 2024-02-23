<%-- JSTL조건 2개
1. 알집파일 세개가 webapp/WEB-INF/lib 안에 있어야함
2. 바로 아랫줄의 지시자 써야 JSTL쓸 수 있음 --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${todo.title}</title>
<style>
.detail{
	white-space : pre-wrap; 
	/* 써져있는 모양 그대로 출력하겠다 */
}
</style>
</head>
<body>
<%--도저언~?
	//상세 조회하는 페이지에 버튼 만들어도 되고 a태그 만들어서 해도 됨
	//상세 조회하는 페이지에 들어가서 완료 여부 수정, 할일 삭제, 할일 제목/내용 수정
	//상세조회 페이지를 삭제하는 것 --%>
	방금 서블릿에서 todo를 담아서 보냈기 때문에 브라우저 탭 제목에 그 제목이 되도록!
	<ul>
	<li>제목 : ${todo.title}</li>
	<li>
	완료 여부(O/X) : 
	<c:if test="${todo.complete}">O</c:if>
	<c:if test="${not todo.complete}">X</c:if>
	
	<%-- //true일 땐 O를 보여줄거다--%>
	</li>
	
	
	<li>등록일 : ${todo.regDate}</li>
	<li class="detail">${todo.detail}</li>
	<%--html은 엔터 인식 못해서 엔터 인식하도록 css 줄거임 위에 style로 --%>
	</ul>
</body>
</html>