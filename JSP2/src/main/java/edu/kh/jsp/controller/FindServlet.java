package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/find")
public class FindServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//get방식 오면 화면만 전환할거다
	String path="/WEB-INF/views/forward/find.jsp";
	req.getRequestDispatcher(path).forward(req, resp);;
	//위임할 때 필요한 RequestDispatcher 객체 필요해서 얻어오고 넘겨줘라
  }
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	///find를 처리하는 곳에 doPost 실행
		//전달 받은 파라미터 얻어오기
	//ctrl shift o == 임폴트 한번에 다 하는 단축키
	String findName = req.getParameter("findName"); //name 속성값을 쓰기
	
	List<String> nameList = new ArrayList<String>();
	
	nameList.add("김길동");
	nameList.add("나길동");
	nameList.add("최길동");
	nameList.add("홍길동");
	nameList.add("고길동");
	nameList.add("우길동");
	nameList.add("박길동");
	
	//----------------------------------------------------------------------
	//입력된 이름이 List에 있다면 몇 번 인덱스에 있는지,
	//없으면 존재하지 않는다고 메시지를 만들어서
	// /forward/result.jsp로 요청 위임하겠다
	
	String message=null;//결과를 저장할 메세지
	//findName과 같은 값이 nameList에 포함되어 있는지 검사하기(있으면 true, 없으면 false)
	if(nameList.contains(findName)) {
		//있음->몇 번 인덱스에 있는지 담아야 함
		message=nameList.indexOf(findName)+"번째 인덱스에 있습니다."; //리스트에 같은 것이 몇 번 인덱스에 있는지 출력해줌
		
	} else {
		//없음
		message = "존재하지 않습니다.";
	}
	
	//만든 메세지가 forward한 곳까지 전달이 돼야 함 -> request scope 이용
	//request scope에 message를 추가하기
	req.setAttribute("message", message);
	
	//forward구문 작성
	String path = "WEB-INF/views/forward/result.jsp";
	req.getRequestDispatcher(path).forward(req, resp); //path 만들어서 forward 해주기
	//여기로 요청위임하겠다
	
	
	}
}
