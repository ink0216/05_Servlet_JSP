package edu.kh.todoList.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.kh.todoList.model.dto.Todo;

public class TodoListDAOImpl implements TodoListDAO{
//인터페이스 상속받을거야 
	
	//필드
	private final String FILE_PATH = "/io_test/TodoList.dat"; 
	//이 파일로 입출력할 거여서 변수명으로 만들어놓기
	
	//private = 이 객체만 쓴다
	//final = 상수
	//pulbic final class = 상속불가인 클래스
	
	//파일에 입/출력되는 리스트를 참조할 참조 변수
	private List<Todo> todoList = null;
	//제네릭의 기능 = 타입 제한 어떤 것만 컬렉션에 저장할 지 제한
	
	//객체 입/출력 보조 스트림
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	
	
	//생성자 : 객체가 만들어지는 것이 아닌, 객체가 만들어질 때 실행되는 메서드임!!!
	//기본생성자
	public TodoListDAOImpl() throws FileNotFoundException, IOException, ClassNotFoundException {
		//이 객체 생성 시 TodoList.dat 파일의 내용 읽어오기 기능을 실행할 것임
		// -> 읽어오기 전에 이 파일이 있는지 없는지 검사를 먼저 해보기
		File file = new File(FILE_PATH); //위에 선언했던 경로
		if(file.exists()) {
			//파일이 존재하는 경우 ->읽어오면 됨
			try {
				
			
			//객체 입력 스트림을 이용해서 입력 받기
			ois = new ObjectInputStream(new FileInputStream(FILE_PATH));//보조스트림인데 기반 스트림으로 만들어야 돼서 안에다가 만들기
			//throws FileNotFoundException
			
			//읽어온 객체를 ArrayList<Todo>로 강제 형변환(다운캐스팅)
			todoList = (ArrayList<Todo>)ois.readObject(); //읽어와서 todoList에 저장 ->ois 역할 다해서 이제 없애야 함
			//Object 타입 -> ArrayList<Todo> 강제 형변환
			} finally {
				if(ois !=null ) ois.close();
				//닫으면서 예외 나면 위로 던지기로 했으니까 여기서 try-catch안써도 됨
			}
			//catch는 안써도 됨!
			//finally는 단독으로 못씀
			System.out.println("*** 데이터 읽어오기 완료 ***");
		} else {
			//파일이 존재하지 않는 경우->파일 만들어주기
			//폴더, 파일 만들기
			
			//폴더 만들기
			File directory = new File("/io_test"); 
			if( !directory.exists()) directory.mkdir(); //폴더가 없으면 생성한다
			
			//파일 만들기
			//			file.createNewFile(); //이렇게 만들어도 되고
			//OutputStream을 이용해서 만들어도 됨(이걸 추천)
			//객체 출력 스트림을 이용해서 파일 생성 + 샘플 데이터 추가
			todoList = new ArrayList<Todo>(); //아무것도 참조 안했었는데 참조할 것 만들기
			todoList.add(new Todo("자바 공부", "수업 내용 복습하기", false, LocalDateTime.now()));
			todoList.add(new Todo("CSS 공부", "수업 내용 복습하기", false, LocalDateTime.now()));
			todoList.add(new Todo("HTML 공부", "수업 내용 복습하기", false, LocalDateTime.now()));
			//complete : false : 미완료, true : 완료
			
			//리스트에 3개 들어갔는데 이걸 파일로 내보내기
			try {
				//객체 출력 스트림 생성
				oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH)); //보조스트림이라 기반스트림 필요하니까 만들기
				//객체 출력 스트림 생성 -> 파일이 없다면 자동 생성되는 코드가 포함돼있음 
				oos.writeObject(todoList); //todoList를 파일로 출력(내보내기)
				
			} finally {
				//다 썼으니까 close하기
				oos.close(); //close()안에는 flush() 가 내장되어 있어서 다 내보내고 닫는 게 자동으로 됨! 
			}
			//catch는 위에 throws 써져 있으니까 필요 없음
			
			System.out.println("*** TodoList.dat 파일 생성 완료 ***");
		}
	}
	@Override
	public void saveFile() throws FileNotFoundException, IOException {
		//todoList를 파일로 접근해서 저장하는 메서드
		//DAO : 파일로 접근하는 역할
		//출력 -> ObjectOutputStream 필요
		try {
			//던진다고 했으니까 catch 필요하지 않음
			oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH)); //연결
			//FILE_PATH 경로에 있는 파일과 연결된 객체 출력 스트림 생성
			oos.writeObject(todoList); //todoList 출력 //사용
		}finally {
			oos.close(); //다 썼으니 닫기
		}
	}
	//-----------------------------------------------------------------------------------------------------------
	/*todoListFullView*/
	@Override
	public List<Todo> todoListFullView() {
		return todoList;
	}
	//----------------------------------------------------------------------------------------------------------------
	@Override
	public Todo todoDetailView(int index) {
		//1. index 범위가 todoList 범위를 넘어서면 null을 반환하도록 코드 짜기
		if(index<0 || index>=todoList.size()) return null;
		
		//2. index가 정상 범위라면 index번째 요소를 반환해주기
		return todoList.get(index);
	}
//----------------------------------------------------------------------------------------------------------------
	@Override
	public int todoAdd(Todo todo) throws FileNotFoundException, IOException {
		//todoList에 전달받은 todo(제목, 내용, 시간 들어있음)를 추가
		//	->추가 성공 시 파일에 저장(출력) 후 삽입된 index를 반환하기
		//	->추가 실패 시 -1을 반환하기
		if(todoList.add(todo)) {
			//add : 항상 리스트의 제일 마지막에 추가가 됨!
			//추가 성공 시
			
			//파일 저장 ->저장하는 메서드 saveFile()이라고 만들어둠
			saveFile(); //호출
			//삽입된 index 반환
			return todoList.size()-1; //제일 마지막에 들어갔으니까 제일 마지막 인덱스 번호 리턴
		}
		//추가 실패 시
		return -1;
	}
//----------------------------------------------------------------------------------------------------------------
	@Override
	public boolean todoComplete(int index) throws FileNotFoundException, IOException {
		//1. index 범위 초과 시 false 반환
		if(index <0 || index>=todoList.size()) {
			//범위 초과
			return false;
		}
		
		//2. index가 정상 범위인 경우 index번째 요소의 complete값을 변경하고
		//		파일 저장 후 true를 반환
		boolean complete=todoList.get(index).isComplete();//가져와서 저장해둠
		todoList.get(index).setComplete(!complete);// O<->X 바꿔서 저장
		//!false == true
		//!true == false
		saveFile(); //파일 저장
			return true;
		
	}
	//Service 메서드가 별도 처리를 하지 않음
	//	->아무것도 안한다고 해서 서비스를 사용하지 않으면 안된다!!!
//----------------------------------------------------------------------------------------------------------------
	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
		//수정된 제목, 내용을 이용해서 Todo 객체 생성
		Todo todo = new Todo();
		todo.setTitle(title);
		todo.setDetail(detail);
		//기존 것을 대체할, 비어있는 todo객체 만들어서 제목, 내용 세팅함
		
		//index 번째 요소의 complete, regDate 값을 얻어와 todo에 세팅
		todo.setComplete(todoList.get(index).isComplete());
		todo.setRegDate(todoList.get(index).getRegDate());
		//두 값을 얻어와서 넣는다
		
		//E List.set(int index, E e) : index 번째 요소를 매개변수 e로 수정하고 이전 요소를 반환(없으면 null이 반환됨)
		//이분법적인 것 있으면 boolean값(있을 때 없을 때)
		if(todoList.set(index, todo) !=null) {
			
			//index번째에 todo를 넣었는데 null이 아닌 경우 == 수정 성공한 경우
			saveFile(); //값이 바뀌었으니, 변경된 todo를 파일에 저장
			return true;
		}
		//실패 시
		return false;
	}
//----------------------------------------------------------------------------------------------------------------
	@Override
	public Todo todoDelete(int index) throws FileNotFoundException, IOException {
		
		//index 범위 검사
		if(index<0 || index>=todoList.size()) return null;
		
		//범위가 맞다면
		//todoList에서 index번째 요소 삭제 후 저장하기
		Todo todo = todoList.remove(index); //예전 요소가 반환되고 그걸 저장
		saveFile(); //저장
		//remove : 삭제하는 것이 아닌 빼내는 것
		return todo;
	}

	
}
