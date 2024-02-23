package edu.kh.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.test.model.dto.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/join")  
public class JoinServlet extends HttpServlet {
//****index.jsp에서 form태그가 요청을 하면 JoinServlet이 요청을 받아야 하는데
	//form태그가 요청을 "/join"이라는 주소를 가진 서블릿에 요청을 보내므로
	//@WebServlet 부분에 적힌, 이 서블릿의 주소를 "/join"이라고 고쳐야 한다.
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Member> memberList = new ArrayList<Member>(); //멤버 객체만 담을 수 있는 리스트
		memberList.add(new Member("test01", "1234", "김테스트"));
		memberList.add(new Member("test02", "5678", "최테스트"));
		memberList.add(new Member("test03", "qwer", "박테스트"));
		//-----------------------------------------------------------
		String memberId = req.getParameter("memberId");  //******* 파라미터 얻어올 때 name속성값을 그대로 써서 가져와야 하므로 get파라미터 안에 각각
		String memberPw = req.getParameter("memberPw");	//"memberId", "memberPw", "memberName"이라고 써야 한다
		String memberName = req.getParameter("memberName");
		for(Member member : memberList) {
			if(member.getMemberId().equals(memberId)) {
				//같은 아이디가 있는 경우 ->redirect
				HttpSession session = req.getSession();
				session.setAttribute("message", memberId + "은/는 이미 존재하는 아이디 입니다.");
				resp.sendRedirect("/"); //get방식
				return; 
			} 
			//같은 아이디가 없는 경우 ->forward
			
			
			
		}
		Member member = new Member(memberId, memberPw, memberName);
		memberList.add(member);
		String message = String.format("%s/%s 님이 가입 되었습니다 (비밀번호 : %s)"  , memberId, memberName, memberPw);
		req.setAttribute("message", message);
		
		String path = "/WEB-INF/views/success.jsp"; 
		req.getRequestDispatcher(path).forward(req, resp);
		
		
	}
	/*
	 * 2. 입력 받은 아이디가 List에 있다면 ->redirect(req X)
"/" 로 redirect 수행.
redirect 시 "(아이디)은/는 이미 존재하는 아이디 입니다."  메시지도 전달하여 응답 화면에서 출력
	*/
//	@Override
//		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		//********
//			String message = req.getParameter("message");
////			req.setAttribute("message", message);
//			
//		}
}