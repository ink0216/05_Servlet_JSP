<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%-- Servlet/JSP 프로젝트 실행 시 
"/" 주소로 요청이 오면
webapp 폴더에 있는 index.html 또는 index.jsp를 이용해
메인페이지 화면을 응답해준다!

Dynamic WebProject에서는
"/"로 요청 왔을 때 다른 곳으로 포워드하고 싶어서 @WebServlet("/")으로 해도 절대로 연결되지 않고
무조건 webapp 폴더에 있는 index.html 또는 index.jsp로 연결됨!!!
근데 Spring에서는 가능!!(요청위임으로 할 수 있음)
보통은 서블릿이 JSP에 forward하는데,
JSP가 서블릿에 forward하는 것으로 하면 가능
서블릿, JSP가 서로 서로 4가지 경우의 수로 다 forward할 수 있음
 서블릿->서블릿
 서블릿->JSP
 JSP->서블릿
 JSP->JSP
--%>

<jsp:forward page="/main"></jsp:forward>
<%-- jsp 액션(동작) 태그 : JSP에서만 할 수 있는 기능들을 담아놓은 태그, 종류 많음--%>
<%-- "/" 요청이 들어온 경우 index 파일이 "/main"이라는 요청을 처리하는 서블릿에게 요청 위임(네가 대신 응답좀 해줘..)--%>
<%-- Post 방식 요청은 매우 제한적 -> 앵간하면 다 get방식임!!(이것도)--%>