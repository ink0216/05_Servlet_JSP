package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/scope")
public class ScopeServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//a태그 => 무조건 getq방식!!!
	
	/*Servlet/JSP 내장 객체와 범위*/
	// - 범위 별 내장 객체를 얻어와 여러 값을 세팅, 확인하기
	
	//1. page scope -> 여기다 쓰면 이 페이지에서만 유지돼서 JSP에서 안보이므로, JSP에서 확인할거임
	//-------------------------------------------------------------------------------------------------
	//2. request scope(==HttpServletRequest 객체 이용)
	// - 요청 받은 Servlet/JSP + 위임 받은 Servlet+JSP 에서만 유지됨
	//		(위임이 계속되면 그 위임된 파일들에서도 request도 계속 유지됨)
	req.setAttribute("requestMessage", "request scope 입니다."); //request에 값 세팅함
	//-------------------------------------------------------------------------------------------------
	//3. session scope(접속)
	// - 클라이언트가 서버에 첫 요청을 할 때(==사이트에 들어왔을 때) 생성됨
	//		(클라이언트가 사이트에 접속했을 경우)
	
	// - 지정된 세션 만료 시간 또는 브라우저 종료 시까지만 유지됨(은행어플 등)
	// - session이 유지되는 동안 어디서든지 사용 가능
	//		session은 클라이언트마다 하나씩 생겨서 어떤 클라이언트 것을 얻어올 지 지정해야함
	
	//1) Session 객체 얻어오기
	HttpSession session = req.getSession();
	//req에는 요청한 클라이언트의 정보도 담겨있음
	
	//2) Session 객체에 속성 추가하기
	session.setAttribute("sessionMessage", "session scope 입니다.");
	//-------------------------------------------------------------------------------------------------
	//4. application scope
	// - 웹 애플리케이션(==서버) 당 1개만 존재
	// - 서버 시작 시 생성, 종료 시 소멸
	//   (종료 전까지 어디서든지 사용 가능)
	//얘는 참조하는 변수명이 특이(ServletContext)
	ServletContext application = req.getServletContext();
	application.setAttribute("applicationMessage", "application scope 입니다.");
	//-------------------------------------------------------------------------------------------------
	//5. 범위 별 우선 순위 확인
	//(범위가 좁을 수록 우선 순위가 높다!!!! )
	//page > request > session > application (+scope)
	
	//page scope는 그 페이지 가서 만들어야 해서 나머지 세개만 만들기
	
	//모든 범위에 같은 key로 속성을 추가해보기
	req.setAttribute("str", "REQUEST");
	session.setAttribute("str", "SESSION");
	application.setAttribute("str", "APPLICATION");
	//그럼 현재 상황에서 가장 우선순위 높은 것이 나올 것 같다!!
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------
	// forward 구문(요청 위임)하기
	String path = "/WEB-INF/views/scope/scope.jsp"; //여기로 forward 할거다
	// / : webapp폴더
	
	req.getRequestDispatcher(path).forward(req, resp);; //Dispatcher객체를 얻어와서 path와 연결시킨 후에 바로 forward 시켜버리기
}
}
