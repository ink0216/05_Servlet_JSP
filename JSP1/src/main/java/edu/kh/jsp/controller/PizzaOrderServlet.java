package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pizza/order")
public class PizzaOrderServlet extends HttpServlet{
//GET 방식 요청 : form 태그(GET), a 태그, JS(location.href), 주소창 직접 입력
//POST 방식 요청 : form 태그(POST), JS(ajax/REST API)
	
	//a태그 요청 == GET방식
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//JSP로 요청 위임되는 HttpServletRequest 객체에
		//임의의 값을 추가하기!
		String myName = "이인경";
//		req.setAttribute(myName, myName); : 
		//Attribute(속성) == 객체의 속성 == 변수(필드)
		req.setAttribute("myName", myName); //req라는 객체에 새 변수  ->JSP에서 꺼내올 수 있음
//		private String myName = "이름";
		
		//==================================================================================================================
		
		//결과화면 대신 만들어줄 pizza_order.jsp가 응답 화면을 대신 만들어 출력할 수 있도록
		//HttpServletRequest, HttpServletResponse를 요청 위임(forward)
		String path="/WEB-INF/views/pizza_order.jsp"; 
		//pizza_order까지의 경로 적을 것인데 (/ : webapp폴더)부터 "pizza_order.jsp"까지의 파일 경로를 작성
		//내부 요청 Servlet이 옆에있는 jsp를 요청
		
		//RequestDispatcher 객체 생성
		//(요청 위임 JSP 지정 + req/resp 객체 발송)(넘겨줌)
		RequestDispatcher dispatcher = req.getRequestDispatcher(path); 
		
		//요청 위임
		dispatcher.forward(req, resp);
	}
	//요청 주소는 하나인데 get/post 방식에 따라서 동작 다르게 할 수 있음
	/*요청 주소(/pizza/order)가 같아도 method가 GET/POST로 다른 경우
	 * 따로 처리하는 코드를 작성할 수 있다!!!!!!!*/
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//POST 방식 요청 처리
		//피자 주문 페이지 요청 처리(POST 방식)
		
		//파라미터 세가지 pizza, size, amount
		String pizza = req.getParameter("pizza"); // 치즈 피자-8000
		String size = req.getParameter("size");  // R || L
		int amount = Integer.parseInt(req.getParameter("amount")); // 1  (개)
		
		/* 피자 이름, 가격 나누기 */
		String[] arr = pizza.split("-");  // "-" 구분자로 쪼개어 String[] 반환
		// arr == {"치즈 피자", "8000"}
		
		//두개로 쪼갰으니 각각 0,1번 인덱스가 됨
		String pizzaName = arr[0]; // "치즈 피자"
		int price = Integer.parseInt(arr[1]); // 8000
		//문자열을 이렇게 쪼개서 데이터를 구분할 수 있다
		
		/* L 사이즈인 경우 4000원 추가 */
		if(size.equals("L"))	price += 4000;
		
		
		/* 수량 만큼 price에 곱하기 */
		price *= amount;
		
		//pizzaName, price를 요청 위임(req,resp객체 넘어감)할 JSP로 넘겨주기 위해
		// HttpServletRequest 객체에 속성으로 추가
		req.setAttribute("pizzaName", pizzaName);
		req.setAttribute("price", price); //앞이 변수명, 뒤가 전달되는 값
		
		//요청 위임할 JSP 경로 지정
		String path = "/WEB-INF/views/order_result.jsp";
		
		//요청 발송자(RequestDispatcher)를 이용해서 JSP 지정 + 요청 위임
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
		}
}
