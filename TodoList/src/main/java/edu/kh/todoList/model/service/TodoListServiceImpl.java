package edu.kh.todoList.model.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoListDAO;
import edu.kh.todoList.model.dao.TodoListDAOImpl;
import edu.kh.todoList.model.dto.Todo;

public class TodoListServiceImpl implements TodoListService{
	
	//필드
	private TodoListDAO dao = null;
	
	//기본생성자
	public TodoListServiceImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		//TodoListServiceImpl 객체가 생성될 때
		dao = new TodoListDAOImpl(); //TodoListDAOImpl 객체도 같이 생성시키기
	}
	
	
	
	
	
	
//상속받았으므로 이거 다 오버라이딩 해야 함
	//-----------------------------------------------------------------------------------------------------------------------
	@Override
	public Map<String, Object> todoListFullView() {
		//1. 할 일 목록을 DAO에서 얻어오기
		List<Todo> todoList=dao.todoListFullView();
		
		//2. 할 일 목록에서 완료(complete필드 값이 true)인 요소가 몇 개인지 카운트하기
		int completeCount = 0;
		//리스트 안에 완료된 것이 몇 개인지 하나씩 꺼내서 보기 향상된 for문
		for(Todo todo:todoList) {
			//todoList에서 하나씩 꺼내면 Todo형임
			//todoList 순차 접근
			if(todo.isComplete()) {
				//boolean은 getter 이름 앞에 get대신 is가 붙음
				//isComplete() == getComplete()
				//true인 경우
				completeCount++; //1씩 증가
			}
		}
		
		//3. todoList , completeCount를 저장할 Map 생성하기
		//	->왜 Map을 쓰냐? 메서드는 반환을 한개밖에 못해서 여러 개를 반환해야 하는 경우 Map으로 묶어서 반환함
		Map<String, Object> map = new HashMap<String, Object>();
		//아무거나 받을 수 있게 최상위 부모인 Object로
		//HashMap이 가장 성능 좋음
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		//4. Map 다 만들었으니 Map 반환하기 ->View에서 Map으로 묶여있던 것을 다시 푸는 코드 작성하면 됨
		return map;
	}
	//-----------------------------------------------------------------------------------------------------------------------
	@Override
	public String dateFormat(LocalDateTime regDate) {
	// 날짜와 시간을 원하는 포맷으로 출력하기
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDateTime = regDate.format(formatter); 
		//전달받은 regDatd에 저장된 날짜 값을 formatter에 지정된 형식으로 변경하겠다
		return formattedDateTime;
	}
/*todoDetailView (할 일 상세조회)*/
	@Override
	public Todo todoDetailView(int index) {
		//1. DAO에 있는 todoList에서 index번째 요소(Todo) 반환받기
		//		없으면 null이 반환됨
		Todo todo = dao.todoDetailView(index);
		return todo;
		//todo 얻어온 다음에 todo를 반환하면 끝
		
		
	}
//-----------------------------------------------------------------------------------------------------------------------
	@Override
	public int todoAdd(String title, String detail) throws FileNotFoundException, IOException {
		//DAO는 todo를 보내주면 추가된 index번호를 보내줌
		//보내줄 Todo 객체 생성
		Todo todo = new Todo(title, detail, false, LocalDateTime.now()); //현재 시간이 들어감
		//LocalDateTime.now() : 현재 시간 반환
		
		//DAO 메서드 호출 후 결과 반환 받기
		int index = dao.todoAdd(todo); //추가된 index 또는 -1이 반환됨
		return index;
	}
//-----------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean todoComplete(int index) throws FileNotFoundException, IOException {
		return dao.todoComplete(index);
		
	}
//-----------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
		return dao.todoUpdate(index, title, detail); //할게 없어도 거쳐 가야함
	}
//-----------------------------------------------------------------------------------------------------------------------
	@Override
	public String todoDelete(int index) throws FileNotFoundException, IOException {
		//데이터 가공할 필요 없음
		//DAO 메서드 호출 후 결과 반환 받기
		//		->삭제된 Todo 또는 null이 반환됨
		Todo todo = dao.todoDelete(index);
		if(todo ==null) return null;
		return todo.getTitle(); //제목 반환
	}
	//같은 인터페이스 상속받은 자식들은 다 비슷한 구조 가짐
	//성능향상 시 상속받는 implements TodoListService에 뒤에 2만 붙이면 됨 ->유지보수성 증가

}
