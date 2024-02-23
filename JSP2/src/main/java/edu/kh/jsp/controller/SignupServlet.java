package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//a태그의 요청
		//a태그이니까 get방식
		
		//forward -> 요청에 따른 응답 화면이 존재하는 경우(요청에 맞는 알맞은 응답화면 보여줘야지!)
		//forward할 JSP가 어디있는지 지정
		String path = "/WEB-INF/views/redirect/signup.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	//form 태그 제출(POST)을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파라미터 얻어오기 (파라미터는 다 String임!)
		//소괄호 안에 name속성을 그대로 쓰기!!!
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");
		
		//pw, pwCheck가 일치하면 "(id) 회원 가입 성공!!"
		//일치하지 않으면 "비밀번호 불일치"
		//라는 메시지를 message 변수에 저장할거다
		//pw, pwCheck는 모두 String이므로 equals 사용!
		String message=null;
		if(pw.equals(pwCheck)) {
			//같은 경우
			message =id+"회원 가입 성공!!";
		} else {
			//다른 경우
			message = "비밀번호 불일치";
		}
		
		//redirect할 때 메세지 보낼거임
		//req.setAttribute("message", message); //안됨
		
		//request scope 객체에 담아서 안나옴
		//redirect == 재요청이므로
		//왜 안될까??
		//-> 밑에서 redirect(재요청) 수행 시
		//		이전 요청이 담긴 request 객체가 삭제되고
		//		새로운 요청이 담긴 request 객체가 다시 생성된다!!!
		//위에서 쓴 req 객체는 이전 것의 객체임. 여기에 id,pw,pwCheck, 메세지도 담았는데
		//redirect를 수행하면 이 객체가 사라진다
		//이전 request 객체에 message가 세팅되었기 때문에
		//새로운 request 객체에는 존재하지 않는다 -> 따라서 출력되지 않는다!!!
		
		//===>[해결 방법]
		//		session을 이용하면 문제 해결이 가능하다!!!!!
		//		session 객체를 이용하면 새결 가능!!!
		//왜? 
		//	session은 redirect를 하든 forward를 하든 상관 없이
		//	세션 만료나 브라우저 종료를 하지 않는 이상 유지된다
		
		
		//Servlet 코드 수행이 완료된 후, 딱히 결과 보여줄 페이지 없거나(로그인 성공 후 처럼), 
		//원래 존재하던 페이지로 이동하고 싶은 경우,
		//이럴 때 redirect를 한다!!!!
		
		//->회원가입 할 때 정보를 입력하던 페이지로 이동하도록 해보기
		
		
		
		//소괄호 안에 /로 하면 index로 돌아감
		//Get 방식으로 /signup 주소를 요청한다
		//redirect : 보통 응답 페이지를 필요로 하지 않는 경우에 사용
		//게시판에 글 썼을 때 성공 시 성공!!이런 페이지 별도로 안나오고 내가 쓴 글이 나오는 페이지로 이동됨(==redirect)
		
		//redirect할 때 메세지 보낼거임
		HttpSession session = req.getSession(); //session 객체 얻어오기
		session.setAttribute("message", message); //이전의 request 객체가 아닌 session에 메세지 추가
		//그러면 redirect를 해도 session에 저장했기에 메세지가 없어지지 않음
		//->회원가입 위에 메세지가 출력될것이다
		
		// - redirect는 무조건 GET방식 요청이다!!!!
		
		resp.sendRedirect("/signup"); //이 코드 안쓰면 그냥 흰색 화면으로 나온다->그러면 안되니까 뭐라도 다른 요청 하는거임
			
		
		//근데 session을 쓰면 단점이 있음
		//session은 브라우저가 꺼지거나 세션 만료시까지 계속 남아있는 문제가 생긴다!!!
		//해결하는 방법!
		//자바 코드 이용 removeAttribute
	}
}
