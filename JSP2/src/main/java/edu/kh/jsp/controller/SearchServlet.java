package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/search")
public class SearchServlet extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<String> nameList = new ArrayList<String>();
	nameList.add("김씨");
	nameList.add("이씨");
	nameList.add("박씨");
	nameList.add("최씨");
	nameList.add("손씨");
	nameList.add("백씨");
	nameList.add("우씨");
	
	//List에 입력 받은 이름(파라미터)가 존재하는지 검사하기
	String inputName =req.getParameter("inputName"); //입력받은 이름이 파라미터로 넘어옴
	if(nameList.contains(inputName)) {
		//List 에 값이 포함되어 있는 경우
	// 존재하는 경우 
//	 "OOO은 X번째 인덱스에 존재 합니다" 라는 메시지
//	 "/WEB-INF/views/practice/search_result.jsp"를 이용해서 출력
//	 (forward)
	
	String searchMessage 
		= String.format("%s은/는 %d번째 인덱스에 존재 합니다", 
								inputName, nameList.indexOf(inputName));
	//forward 시 request가 유지된다!!!
	req.setAttribute("searchMessage", searchMessage); //여기에 추가해서 같이 보내겠다
	
	String path = "/WEB-INF/views/practice/search_result.jsp";
	req.getRequestDispatcher(path).forward(req, resp);
	}else {
		//List에 값이 포함되지 않은 경우
//		List에 입력 받은 이름이 없으면
//    "OOO은 존재하지 않습니다"라는 메시지를,
//    "/"(메인페이지를 재요청, 실패하면 redirect수행)에 돌아와서 출력해보겠다
		String searchMessage=inputName+"은/는 존재하지 않습니다.";
		//이걸 redirect로 해보기
		//req로 하면 안된다
		HttpSession session = req.getSession(); //session 객체 얻어오기
		session.setAttribute("searchMessage", searchMessage); //session에 설정함
		// "/error"로 redirect하기
		resp.sendRedirect("/error"); //여기로 재요청 보내겠다 -> 에러라는 요청을 처리하는 서블릿을 찾아감
		//redirect는 무조건 GET 방식!! -> 그 서블릿에서 doGET 오버라이딩하기
		//index가 JSP가 아닌 HTML이어서 안됐었음
	}
}
}
