package edu.kh.jsp.model.dto;
//책이라는 dto 만들기
public class Book {
	//필드(==멤버 변수)
private String title;
private String writer;
private int price;
//private == 캡슐화(외부로부터의 직접접근 막는 것)

//생성자 != 객체를 생성하는 것
//생성자 == 객체 생성할 때 실행되는 것
//기본생성자(반환형 쓰지 않고 클래스명과 동일하게)
public Book() {}

//매개변수 생성자 
public Book(String title, String writer, int price) {
	super();
	this.title = title;
	this.writer = writer;
	this.price = price;
}

//getter/setter 생성: selectAll 누르고 하기
public String getTitle() {
	return title;
}

public void setTitle(String title) {
	this.title = title;
}

public String getWriter() {
	return writer;
}

public void setWriter(String writer) {
	this.writer = writer;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}

//toString 오버라이딩
@Override
public String toString() {
	return "Book [title=" + title + ", writer=" + writer + ", price=" + price + "]";
}





}

