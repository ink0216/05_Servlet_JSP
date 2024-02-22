package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/scope/check") //이 요청으로 오면 여기서 처리
public class ScopeCheckServlet extends HttpServlet{
//a태그 요청 클릭 시 request 는 새로 또 만들어져서 안나올거임
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//제일 앞 "/" == webapp 폴더
		String path="/WEB-INF/views/scope/check.jsp"; //여기로 요청위임
		req.getRequestDispatcher(path).forward(req, resp); //RequestDispatcher 요청위임해줌(보내줌)
	}
}
