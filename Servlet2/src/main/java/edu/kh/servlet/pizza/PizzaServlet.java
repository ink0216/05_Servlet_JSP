package edu.kh.servlet.pizza;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/ex3")
public class PizzaServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//피자 종류 하나밖에 선택 못해서 파라미터 한개
		String pizza = req.getParameter("pizza");
		//라디오 버튼도 네임 같으면 하나밖에 선택 못해서 파라미터 하나
		String dough = req.getParameter("dough"); //도우 타입 얻어옴
		String ordererName = req.getParameter("ordererName"); //주문자명 얻어옴
		String ordererAddress = req.getParameter("orde"
				+ "rerAddress"); //주문자 주소 얻어옴
		
		String[] options = req.getParameterValues("opt");
		System.out.println("[서버] /ex3 요청됨");
		
		//---------------------------------------------------------------------
		//선택한 피자 종류 , 도우 타입, 옵션에 따라 달라지는 가격 계산하기
		int price =0;
		switch(pizza) {
		case "페퍼로니" : price +=15000; break;
		case "포테이토" : price +=16000; break;
		case "치즈" : price +=17000; break;
		case "새우" : price +=18000; break;
		case "하와이안" : price +=19000; break;
		case "불고기" : price +=20000; break;
		}
		if(dough.equals("original")) price+=2000;
		if(dough.equals("napoli")) price+=3000;
		
		if(options !=null) {
			//선택한 옵션이 있는 경우
			for(String option : options) {
				switch(option) {
				case "핫소스" : price+=1000; break;
				case "콜라" : price+=3000; break;
				case "감자튀김" : price+=4000; break;
				}
			}
		}
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
		sb.append(String.format("<li>피자 : %s</li>", pizza));
		String temp 
		= dough.equals("thin") ? "씬" : (dough.equals("original") ?"오리지널" :"나폴리"); //삼항연산자
		sb.append(String.format("<li>도우 : %s</li>", temp));
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
