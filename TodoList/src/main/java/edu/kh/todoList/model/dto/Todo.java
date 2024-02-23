package edu.kh.todoList.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

//DTO (Data Transfer Object) : 값(관련된 값의 묶음) 전달 역할
//== VO (Value Object) : 값 전달하는 역할 ==domain == entity
public class Todo implements Serializable{
	//내용 작성한 것을 파일에 저장할 건데 너무 커서 직렬화 필요
	//스트림 입출력을 위한 직렬화
	
	//할 일 저장
	//import 자동 = ctrl + shift + o
	
	private String title; // 할 일 제목
	private String detail; // 상세 내용
	private boolean complete; // 완료 여부 (O / X)
	private LocalDateTime regDate; // 등록 날짜
  
	//Date, Calendar, LocalDate, LocalDateTime
	
	/*LocalDateTime*/
	// java.time 패키지
	// LocalDateTime : 날짜, 시간을 나타내는 클래스
	// LocalDateTime.now() : 현재 시간 반환

	// 날짜와 시간을 원하는 포맷으로 출력하기
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
	// HH:mm:ss");
	// String formattedDateTime = currentDateTime.format(formatter);
	
	//생성자란 무엇인가  : 접근제한자 + void이런거 안쓰고 +이름은 클래스와 같게
	//기본 생성자
	public Todo() {
		super(); //Object
	}
	//매개변수 생성자 alt shift s -> o

	public Todo(String title, String detail, boolean complete, LocalDateTime regDate) {
		super();
		this.title = title;
		this.detail = detail;
		this.complete = complete;
		this.regDate = regDate;
	}
	//getter/setter alt shift s -> r

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	//toString alt shift s ->s
	//Object.toString() 오버라이딩 : 모든 필드 값을 문자열로 표현(객체에 뭐가 있는지 서술해주는 것)

	@Override
	public String toString() {
		return "Todo [title=" + title + ", detail=" + detail + ", complete=" + complete + ", regDate=" + regDate + "]";
	}
	
	
	
	
}
