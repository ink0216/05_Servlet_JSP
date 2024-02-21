package edu.kh.jsp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@WebServlet("주소")
//1) 현재 클래스를 Servlet으로 등록함(서버 실행 시 자동으로 이 클래스를 객체화 함)
//2) 소괄호 내의 주소와 Servlet을 매핑해서 요청/응답 처리할 수 있게 함
@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	//method="GET" == doGet() 오버라이딩
	//method="POST" == doPOST() 오버라이딩
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터(전달 인자 == 전달된 input 태그값들) (항상 String)(HTML에서 작성된 것은 모두 String으로 인식됨)
		
		//전달된 값들은 요청객체(req)에 담겨있다.
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		String inputPwCheck = req.getParameter("inputPwCheck");
		String inputName = req.getParameter("inputName");
		String inputAge = req.getParameter("inputAge");
		
		System.out.println("inputId : "+inputId);
		System.out.println("inputPw : "+inputPw);
		System.out.println("inputPwCheck : "+inputPwCheck);
		System.out.println("inputName : "+inputName);
		System.out.println("inputAge : "+inputAge);
		
		//회원가입 시 적은 정보를 보여줄 것(출력하기 위한 구문 로직 작성)
		
		//조건 : 
		
		//비밀번호, 비밀번호 확인이 불일치하면 
		//<h3>비밀번호가 일치하지 않습니다</h3>
		
		//비밀번호는 일치하는데 나이가 14세 미만인 경우
		//<h3>14세 이상만 가입 가능 합니다</h3>
		
		//위의 두 조건 모두 충족 시
		//<h3>가입 성공!!!</h3>
		//아이디, 비밀번호, 이름, 나이 (ul, li이용해서 출력)
	//=================================================================================================
	/*JSP를 이용해서 응답하기*/
		//1) JSP 파일 작성법
		//		- src/main/webapp/WEB_INF/views폴더에 작성
		//VSCode : 자바, JSP랑 안친함
		//VSCode : HTML, CSS, JS 작성
		//Eclipse : Java, JSP 작성
		
		//2) Servlet이 요청될 때 생성된
		//		HttpServletRequest, HttpServletResponse 객체를
		//		응답 화면을 대신 만들어서 응답해줄 JSP에게 전달
		//		== 요청 위임(forward, JSP앞으로 넘기겠다)
		
		//JSP에게 대신 해달라고 하는건데 그 대신 만든 것을 2번 손님한테 갖다주라고 지정하기
		
		//포워드 할 때 필요한 객체 하나 있음
		//* RequestDispatcher 객체 *
		//	- 어떤 JSP로 요청을 위임할 지 지정하고 발송해주는 객체
		//	- HttpServletRequest에 내장되어 있음
		//Dispatcher : (요청을)발송해주는 객체
//		RequestDispatcher dispatcher = req.getRequestDispatcher("경로");
		
		//요청 위임할 JSP 경로 미리 저장해두기
		//	->src/main/webapp 폴더를 기준으로 해서 파일 경로를 작성
		String path = "/WEB-INF/views/signup_result.jsp";
		// 맨 처음의 / : webapp폴더
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		
	//쌍따옴표 유무에 따른 둘의 차이는 ? 
//		RequestDispatcher dispatcher = req.getRequestDispatcher(path); : 변수명을 적어야 그 값이 나옴
//		RequestDispatcher dispatcher = req.getRequestDispatcher("path"); : 값을 path로 읽음
		
		//인터페이스는 단독 객체 생성은 불가능하지만 부모 타입의 참조 변수로는 사용 가능
		
		//발송
		dispatcher.forward(req, resp); //지정된 JSP로 요청 위임
		//JSP에 req, resp가 전달되고, 알아서 JSP가 다 해줌 
	//=================================================================================================
//		/*응답 형식 지정*/
//		resp.setContentType("text/html; charset=utf-8"); //내가 내보내려는 것이 어떤 것인지부터 설정해야 함
//		
//		/*출력용 스트림 준비*/
//		PrintWriter out = resp.getWriter();
//		
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append("<!DOCTYPE HTML>");
//		sb.append("<html>");
//		sb.append("<head>");
//		sb.append("<title>회원 가입 결과</title>");
//		
//		sb.append("</head>");
//		sb.append("<body>");
//		if(inputPw.equals(inputPwCheck)==false) {
//			//비밀번호 일치하지 않으면
//			sb.append("<h3>비밀번호가 일치하지 않습니다</h3>");
//		} else if(Integer.parseInt(inputAge)<14) {
//			//나이 따져보기
//			//나이가 14세 미만인지 확인
//			//inputAge<14로 쓰면 inputAge는 String이어서 타입이 14와 달라서 비교 불가
//			sb.append("<h3>14세 이상만 가입 가능 합니다</h3>");
//		} else {
//			sb.append("<h3>가입 성공!!!</h3>");
//			sb.append("<ul>");
//			sb.append(String.format("<li>ID : %s</li>", inputId));
//			sb.append(String.format("<li>PW : %s</li>", inputPw));
//			sb.append(String.format("<li>NAME : %s</li>", inputName));
//			sb.append(String.format("<li>AGE : %s</li>", inputAge));
//			sb.append("</ul>");
//		}
//		sb.append("</body>");
//		
//		sb.append("</html>");
//		out.write(sb.toString()); //==out.print(sb.toString());
		//이거 만들기 힘드니까 JSP 이용!!!
		//=================================================================================================
//		sb.append(""" 
//				<!DOCTYPE html>
//				<html>
//				<head>
//				<title>회원 가입 결과</title>
//				</head>
//				<body>
//				
//				</body>
//				</html>
//				
//				
//				
//				""");
		///따옴표 세 쌍 쓰면 안에 쓰여진 모양 그대로 출력됨
	}

}
