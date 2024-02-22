<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>
<h1>EL을 이용해서 request에 세팅된 속성 얻어와 출력하기</h1>
<ul>
<li>[작성법 : \${K} ]</li>
<%-- K == Key, V == Value --%>
<li>기본 자료형 : ${score}</li>
<li>String(객체) : ${address}</li>
<li>
List  객체 : ${strList}  <!-- 이 안에 값 네개 세팅했음 -->
<ul>
<%-- EL 구문에서는 배열/리스트 관계 없이 요소 하나 얻어올 땐 [index] 사용한다 --%>
<li>${strList[0]} </li>
<li>${strList[1]} </li>
<li>${strList[2]} </li>
<li>${strList[3]} </li>
</ul>
</li>

<li>
DTO : ${book1}
<ul>

<%-- DTO는 필드가 여러개인데 어떻게 하지 ?  --%>
<%--  \${K.필드명} : 객체의 필드 값을 얻어오기 위한 getter 호출하는 것 --%>
<%--  ***** EL을 이용해서 필드 값을 얻어오려면 getter를 필수로 이용해야 한다!!!!!! ***** --%>
<li>${book1.title} </li> <%--  == book1.getTitle()을 호출하는 것임!!!!! --> <!-- book1의 제목 얻어오기 --%>
<li>${book1.writer} </li>
<li>${book1.price} </li>
</ul> 
</li>
</ul>

<hr>
<hr>
<%--2. null ,빈칸, 비어있음에 대한 EL 처리 확인 --%>
<h1> empty 연산자 </h1>
  <pre>
    - \${empty 객체 | 배열 | 리스트}

    - null 또는 빈칸 또는 비어있으면 true
      아니면 false

    - EL은 null을 빈칸으로 표현
      -> null == ""(빈칸)  ==  비어있음(출력할게 없어서 빈칸) -> 안보여서 empty 연산자로 확인하기
      -> null과 빈칸인 경우를 한 번에 처리 가능
  </pre>

  <ul>
    <li> test1 = null; ->   ${empty test1}</li>
    <li> test2 = ""; ->     ${empty test2}</li>
    <li> test3 = null; ->   ${empty test3}</li>
    <li> test4 = new ArrayList<String>(); ->  ${empty test4}</li> 
    <li> test5 = new ArrayList<String>(); + add()->  ${empty test5}</li>
  </ul>
  <hr> <hr>
  <h1>El을 이용해 파라미터 출력하기</h1>
	<pre>
	- \${param.Key} 형식으로 작성 (파라미터중에서 어떤 것을 얻어오겠다)
	* 테스트 진행 시 form태그가 아닌 a태그로 넘어와서 제출된 값이 하나도 없음 ->쿼리스트링 이용 * 
	- 쿼리스트링 : 주소에 담긴 값을 표현하는(나타내는) 문자열
		-> 요청주소?K=V&K=V&K=V ....
		-> ?가 쿼리스트링 시작 부분
	- form 태그 GET 방식 제출
		또는
		a태그, JS, 주소창에 직접 작성하는 것도 가능!!
	
	</pre>
	<h3>\${param.input} : ${param.input}</h3> <%--EL는 null을 빈칸으로 표시해서 아무것도 안보임 --%>
	<%-- http://localhost/el : 요청주소 --%>
	<%--주소창, 쿼리스트링에는 띄어쓰기 존재안함 -> 주소창에 적음 --%>
	<%--http://localhost/el?input=안녕하세요 : 요청을 할 때 파라미터로 input을 보내겠다 --%>
	<h3>\${param.message} : ${param.message}</h3>



</body>
</html>