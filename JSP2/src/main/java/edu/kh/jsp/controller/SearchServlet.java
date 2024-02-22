package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	}
}
}
