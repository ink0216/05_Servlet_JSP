package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*Servlet 작성 순서
 * 1) HttpServlet 상속받기
 * 2) @WebServlet() 어노테이션(컴파일러에게 지시하는 구문) 작성하기
 * 		- 오버라이드 어노테이션 : 컴파일러에게 오버라이딩 잘 하는지 검사해달라 하는것
 * 		- 어노테이션 : 컴파일러에게 주석 남겨놓기(커멘트) 
 * 3) doGet() 또는 doPost() 오버라이딩 하기(form 태그 메서드에 따라서 수행)
 * 4) 필요한 로직 처리
 * 		- 파라미터 얻어오기
 * 		- 필요한 요청 처리 구문 작성
 * 5) 응답 형태 지정 + 응답 스트림 얻어오기
 * 6) 스트림을 통해서 응답 데이터(html 코드) 출력하기*/

//@WebServlet : 다음 두 가지 태그를 컴파일 시 추가하라고 컴파일러에게 얘기하는 어노테이션
//	-1) 현재 클래스를 Servlet으로 등록 -> <servlet> 태그 역할 함
//	-2) 등록된 Servlet과 () 내 요청 주소를 매핑 -> <servlet-mapping>역할 함
@WebServlet("/ex2")
public class ExampleServlet2 extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//오버라이드 내부에 자동완성된 내용 전체 지우기! -> 나중에 오류날 수도 있어서
	
	//파라미터 얻어오기
	//치킨 종류 하나밖에 선택 못해서 파라미터 한개
	String chicken = req.getParameter("chicken"); //치킨 얻어옴
	
	//라디오 버튼도 네임 같으면 하나밖에 선택 못해서 파라미터 하나
	String type = req.getParameter("type"); //뼈/순살 얻어옴
	String ordererName = req.getParameter("ordererName"); //주문자명 얻어옴
	String ordererAddress = req.getParameter("ordererAddress"); //주문자 주소 얻어옴
	
	
	
	//체크박스 : 다중 선택 가능 -> String 하나로 여러 개 받을 수 없음 ->String[]을 반환하는 values 사용
	//	->같은 네임인 것들을 String[]로 반환
	
	////String[] req.getParameterValues("name속성값")
	// -> 같은 name 속성 값을 가지는 값을 모두 모아
	//   하나의 String[]로 만들어 반환하는 메서드
	
	//배열은 크기가 줄었다 늘었다 할 수 없다
	String[] options = req.getParameterValues("opt");
	//-아무것도 선택하지 않으면 null,
	//	하나라도 선택하면 String[] 반환됨
	System.out.println("[서버] /ex2 요청됨");
	
	//---------------------------------------------------------------------
	//선택한 치킨 종류 , 순살 여부, 옵션에 따라 달라지는 가격 계산하기
	int price = 0;
	switch(chicken) {
	case "후라이드" : price+=10000; break;
	case "양념" : price+=11000; break;
	case "마늘" : 
	case "간장" : price+=12000; break;
	}
	if(type.equals("boneless")) {
		//순살인 경우에 가격 추가
		price+=2000;
	}
	//선택된 옵션이 있을 경우
	if(options !=null) {
		for(String opt : options) {
			//향상된 for문
			//배열에 저장된 모든 요소에 하나씩 순차 접근
			switch(opt) {
			case "치킨무" : price+=500; break;
			case "콜라" : price+=2000; break;
			case "치즈볼" : price+=3000; break;
			}
		}
	}
	//price 계산 완료 (4번 과정까지 완료)
	
	//-------------------------------------------------------------------------------------
	//응답 형태 지정하기
	resp.setContentType("text/html; charset=utf-8");
	
	//응답용(출력용) 스트림 얻어오기
	PrintWriter out = resp.getWriter(); //요청한 클라이언트와 연결된 스트림을 줌
	
	StringBuilder sb = new StringBuilder();
	sb.append("<!DOCTYPE html>");
	sb.append("<html>");
	sb.append("<head>");
	sb.append("<title>");
	sb.append(String.format("%s님 주문 영수증", ordererName));
	sb.append("</title>");
	sb.append("</head>");
	sb.append("<body>");
	sb.append("<h3>");
	sb.append("주문자명 : ");
	sb.append(ordererName);
	sb.append("</h3>");
	
	sb.append(String.format("<h3>주소 : %s</h3>", ordererAddress));
	sb.append("<ul>");
	sb.append(String.format("<li>치킨 : %s</li>", chicken));
	String temp = type.equals("bone") ? "뼈" : "순살"; //삼항연산자
	sb.append(String.format("<li>뼈/순살 : %s</li>", temp));
	if(options !=null) {
		//선택된 옵션이 있을 경우에만
		sb.append("<li>");
		sb.append("선택한 옵션 : ");
		for(String opt : options) sb.append(opt+" ");
		//options에서 opt를 하나씩 꺼내서 sb에 추가하는데 사이사이에 띄어쓰기 추가
		sb.append("</li>");
	}
	sb.append("</ul>");
	sb.append(String.format("<h3>금액 : %d 원 </h3>", price));
	sb.append("</body>");
	
	sb.append("</html>");
	out.print(sb.toString()); //위에 만들어둔 출력용 스트림 out으로 
	//StringBuilder를 String으로 바꾸어
	//출력하는 구문
}
}
