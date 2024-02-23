package edu.kh.todoList.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// "/"요청 시 위임 받을 Servlet
@WebServlet("/main")
public class MainServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	//post방식 요청은 매우 제한적이라
	//앵간하면 다 get방식임!!
	
	TodoListService service=null; //TodoListService 참조 변수 선언(값은 아직 안 넣음)
	//어느 문에서 사용할 지 몰라 전역변수로 선언
	
	//RequestDispatcher 참조 변수 선언
	RequestDispatcher dispatcher=null; //RequestDispatcher : forward할 때 JSP로 옮겨주는 택배기사 객체
	
	
	
	
	
	try {
		//TodoList를 구현한 자식 객체 생성 후 service에 대입(다형성 업캐스팅 적용)
		service = new TodoListServiceImpl();
		
		//전체 목록 + 완료된 Todo 개수가 담긴 Map 얻어오기(Map으로 반환해주니까 Map으로 받아야 함)
		Map<String, Object> map = service.todoListFullView();
		//key는 보통 string이고 value는 아무거나 받을 수 있게 object 타입
		//근데 그때 왜 Map으로 담아서 받아왔을까?
		//메서드는 하나의 값만 반환할 수 있는데 이 경우 두 값을 반환해야 해서 일시적으로 묶어서 반환한거임
		
		//map에 묶여있는 값 다시 풀어내기
		List<Todo> todoList = (List<Todo>)map.get("todoList"); 
		//		map에서todoList를 얻어와서 그걸 List<Todo>이걸로 다운캐스팅하겠다
		int completeCount = (int)map.get("completeCount");
		
		//service에서 얻어온 이 두 값을 req에 속성을 담아서 forward할 거임
		req.setAttribute("todoList", todoList);
		req.setAttribute("completeCount", completeCount);
		
		String path="/WEB-INF/views/main.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
		// "/" -> index -> "/main" -> main.jsp
		//forward의 연속
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
