package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/detail")
//쿼리스트링은 주소가 아닌, 전달하는 값이므로 ?부터는 안써도됨
public class TodoDetailServlet extends HttpServlet{
//a태그니까 get
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TodoListService service=null; //이건 어디서 쓸 지 몰라서 전역변수로 함
		try {
			service = new TodoListServiceImpl(); //TodoListServiceImpl 생성 시 던지는 예외 많아서 try-catch 사용
			
			//쿼리스트링으로 전달 받은 index(파라미터) 얻어오기
			int index = Integer.parseInt(req.getParameter("index")); 
			
			//index를 이용해서 index번째 Todo를 조회하는 서비스 호출 후 결과 반환받아서 저장하기
			Todo todo =service.todoDetailView(index);
			
			if(todo ==null) {
				//index번째에 todo가 없다
				//==index를 잘못 입력한 경우
				//쿼리스트링으로 입력하는 거여서 인터넷 주소창에서도 입력 가능
				
				//실패해서 뭘 보여줄 게 없으므로
				//메인페이지로 redirect 할거다
				//redirect시 메시지 담아서 보내고싶어서 req가 아닌 session으로 하기!
				HttpSession session = req.getSession();
				session.setAttribute("message", "index가 올바르지 않습니다.");
				resp.sendRedirect("/"); //그럼 메인페이지로 가는데 message라는 값이 있넹 있으면 alert하는 JS 코드 존재
			}else {
				//정상 범위 index인 경우
				
				//결과 페이지를 보여줄거임
				//forward로 JSP로 넘겨서 보여줄거임
				req.setAttribute("todo", todo) ;//요청 위임된 JSP에서 쓸 수 있게 값을 세팅하기
				
				String path = "/WEB-INF/views/todo/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
