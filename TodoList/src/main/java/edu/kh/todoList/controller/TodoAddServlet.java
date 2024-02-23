package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/add")
public class TodoAddServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get방식이므로
		
		TodoListService service=null; //TodoListService 참조 변수 선언
		
		try {
			//1. 전달 받은 파라미터 얻어와 변수에 저장하기
			String title = req.getParameter("title");
			String detail = req.getParameter("detail");
			
			//2. TodoListService를 상속 받은 자식 객체 생성하기
			service = new TodoListServiceImpl();
			
			//3. 할 일을 추가하는 Service 호출 후 결과를 반환받기
			int index = service.todoAdd(title, detail);
			//호출 시 성공 시 추가된 index번호, 실패 시 -1 반환되는 메서드
			
			//4. session을 이용해서 성공, 실패 메시지를 redirect시에 담아서 전달하기
			
			String message=null;
			if(index != -1) {
				//성공 시
				message = "추가 성공!!";
			} else {
				//실패 시
				message="추가 실패..";
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			
			//5. 메인 페이지로 redirect하기(할 일 추가 성공 시 별도 화면 필요없음)
			resp.sendRedirect("/");  //메인페이지인 index.jsp를 호출하는데 ->/main->main.jsp 가 보여짐
			//근데 main.jsp 보여질 때 파일을 읽어옴
			//main.jsp에 어디에 나타나면 좋을까
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
