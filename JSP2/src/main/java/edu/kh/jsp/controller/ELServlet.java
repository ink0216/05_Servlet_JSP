package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp.model.dto.Book;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/el") ///el요청이 오면 처리해줄거다
//이 클래스를 서블릿으로 등록 하고 어떤 주소와 매핑하겠다는 어노테이션
//이거 없으면 이 클ㄹ래스는 그냥 일반클래스임
public class ELServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//a태그는 무조건 get방식임!!!(아무리 용을 써도 post방식 될 수 없음!!!!!!) ->doGet 오버라이딩
	
	//새로운 값 마니마니 세팅하기
	// HttpServletRequest 객체에 여러 속성 마니마니 추가하기
	// - 속성 추가 : req.setAttribute("Key", Value ) 이용
	//							이때 Key는 무조건 String으로 들어감. Value는 아무 값이나 들어갈 수 있음!!!!
	// - 추가된 속성 값은 
	//		위임받은 JSP에서 ${Key} 형태로 작성하면 
	//		출력 시 해당 위치에 Value가 출력됨
	
	//기본 자료형 세팅
	req.setAttribute("score", 90); //점수 = 90
	
	//String 세팅
	req.setAttribute("address", "서울시 중구 남대문로 120");
	
	//컬렉션(List, Set, Map) 모두 세팅 가능
	//List 세팅
	List<String> strList = new ArrayList<String>(); //스트링만 저장하는 리스트
	//ArrayList : 배열 모양인 리스트! (배열 아니고 리스트임)
	
	strList.add("Collection");
	strList.add("List");
	strList.add("Set");
	strList.add("Map");
	req.setAttribute("strList", strList);
	
	//DTO 세팅
	//책이라는 DTO (클래스) 만들어서 세팅
	Book book1 = new Book("나는 행복한 푸바오 할부지입니다", "강철원", 18000); //Book 객체 생성
	req.setAttribute("book1", book1);
	//===========================================================================================================
	/*2. null ,빈칸, 비어있음에 대한 EL 처리 확인*/
	String test1 = null; //test1을 세팅해서 EL로 출력하면 뭐가 나올까
	String test2 = ""; //빈칸인 test2를 세팅해서 EL로 출력하면 뭐가 나올까
	
	List<String> test3 = null; //String으로 타입 제한된 List가 null
	
	List<String> test4 = new ArrayList<String>(); //비어있는 List
	
	List<String> test5 = new ArrayList<String>(); //비어있지 않은, 요소가 존재하는 List
	test5.add("aaa");
	test5.add("bbb");
	
	req.setAttribute("test1", test1);
	req.setAttribute("test2", test2);
	req.setAttribute("test3", test3);
	req.setAttribute("test4", test4);
	req.setAttribute("test5", test5);
	
	
	
	
	
	
	
	
	
	
	
	
	//===========================================================================================================
	
	
	
	
	//forward 구문
	//여기서 응답하는 HTML 코드 만들기 힘드니까 JSP로 요청 위임하는 것
	//원래 서블릿이 요청 받고 결과화면(HTML)까지 만들어서 응답해야 하는데
	//Java에서 HTML 코드 쓰기 힘들어...
	//==>JSP에게 요청, 응답 객체를 넘겨줘서 
	//		대신 결과 화면 만들고 응답하도록 하는 것 == 요청 위임(forward)
	
	//요청 위임은 3단계로 나뉨!!!!!!!!!!!!!!
	//1) 요청 위임할 JSP 파일의 경로 지정(어디에 있는 JSP파일에 넘겨줄 것인지)
	String path = "/WEB-INF/views/el/el.jsp";
	//WEB-INF : 브라우저 주소창에 적어서는 들어갈 수 없지만, 파일 경로로는 접근 가능한 폴더
	// / : webapp폴더
	
	//2) 요청 발송자(RequestDispatcher) 생성(요청을 넘겨줄 객체 필요)
	RequestDispatcher dispatcher = req.getRequestDispatcher(path); //지정돼있는 path한테 발송할거다
	
	//3) 진짜로 요청위임하기(dispatcher를 이용해서)
	dispatcher.forward(req, resp);
}
}
