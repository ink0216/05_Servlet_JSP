package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet; //이건 자바가 아닌, 런타임으로 설정한 Tomcat이 제공하는 클래스
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExampleServlet1 extends HttpServlet{
//상속받음
	//상속받았으니까 오버라이딩하기
	//html 태그에서 온 get방식 요청(method ="get"인 form 태그 제출)을 처리하는 메서드 
	//get방식이므로 여러 개 중에 doGet 메서드를 오버라이딩 해야함
	//doGet = get방식으로 온 요청을 처리하겠다
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//매개변수 두개 HttpServletRequest req, HttpServletResponse resp 매우 중요!!!!!
		/*매개 변수 req, resp
		 * HttpServletRequest req : 기본자료형 아닌 객체
		 * 	-	클라이언트의 요청 시 자동 생성되는 객체
		 * 	- 요청 시 전달된 데이터, 
		 * 요청한 클라이언트의 정보(->요청한 클라이언트의 ip주소 등 뜯어올 수 있음), 
		 * 요청을 유연하게 처리하기 위한 객체 등을 제공하는 객체
		 * 
		 * HttpServletResponse resp
		 * 	- 클라이언트 요청 시 자동 생성되는 객체
		 * 	- 서버가 클라이언트에게 응답할 수 있는 방법을 제공하는 객체
		 * 		(서버 -> 클라이언트로 출력하는 스트림(PrintWriter))(서버가 클라이언트에 출력해서 쓴다)
		 * 
		 * 
		 * */
		
		//요청 시 같이 제출된 이름, 나이 얻어오기
		// -> 제출된 이름, 나이 == 전달 인자(Parameter, 전달 되는 값)
		//request에 담긴 parameter꺼내기
		//요청에 담긴 특정 파라미터 얻어오기
		//String HttpServletRequest.getParameter("name속성값")
		
		//HTML에 작성한 모든 값들은 숫자도 포함해서 다 String으로 인식됨
		String inputName = req.getParameter("inputName");
		//<input name="inputName">에 작성되어 제출된 값 얻어오기
		
		String inputAge= req.getParameter("inputAge");
		//<input name="inputAge">에 작성되어 제출된 값 얻어오기
		
		System.out.println("[서버] /ex1 요청을 받음");
		
		System.out.println("전달 받은 inputName : "+inputName);
		System.out.println("전달 받은 inputAge : "+inputAge);
		
		//---------------------------------------------------------------------------------
		/*응답 처리*/
		/*서버(현재 프로젝트) -> 클라이언트(브라우저)로 
		 * 서버가 HTML 코드(HTML 문서)를 스트림을 통해서 클라이언트에게 출력하고
		 * 클라이언트가 출력 받은 HTML 코드를 해석해서 화면을 구성
		 * 
		 * 1) 응답하는 HTML 문서(데이터)의 형식과 문자 인코딩을 지정해야 함(영어로 써져있는 문서 ...)
		 * 2) 출력용 스트림 얻어오기(스트림 준비)
		 * 3) 스트림을 이용해서 HTML 코드 출력하기
		 * */
		
		//1) 응답하는 HTML 문서(데이터)의 형식과 문자 인코딩을 지정해야 함(영어로 써져있는 문서 ...)
		resp.setContentType("text/html; charset=utf-8"); //글자로만 작성돼있고 유니코드로 내보낼 문서야
		
		//2) 출력용 스트림 얻어오기(스트림 준비)
		//		resp.getWriter() : 쓰는 객체
		PrintWriter out = resp.getWriter();
		
		//3) 스트림을 이용해서 HTML 코드 출력하기
		
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE html>");
		sb.append("<html>");
		
		sb.append("<head>");
		sb.append("<title> /ex1 응답 페이지</title>");
		
		sb.append("</head>");
		
		sb.append("<body>");
		sb.append(String.format("<h1> %s님의 나이는 %s입니다.</h1>", inputName, inputAge));
		//문자열이 만들어져서 sb에 추가됨
		//printf와 비슷한 것!
		sb.append("</body>");
		
		sb.append("</html>"); //요청 할 때마다 이 코드 사용해서 새로 화면 만들어줌 == 동적 웹
		
		out.print(sb.toString());//sb에 누적된 html 코드를 스트림을 이용해서 출력하기
	}
	
	
}
