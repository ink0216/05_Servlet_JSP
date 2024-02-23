<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%-- JSTL(Jsp Standard Tag Library) (태그처럼 만들어서 준 것)
    - JSP에서 자주 사용하는 Java 기능(if, for, 날짜 관련 기능 등)을  태그 형식으로 만들어 제공
    - JSP 개발을 간소화하고 유지 관리를 용이하게 하는 강력한 도구(스크립틀릿 안쓰려고)
    요즘엔 스프링부트 : 이제 JSP지원안함(JSP 너무 옛날거여서)
    JSP에서 자바 코드 스크립틀릿으로 쓰는거 어려워서 이거 만듦
--%>
    
    <%-- JSTL 라이브러리를 현재 JSP에서 사용하겠다는 JSP지시자를 작성해야함 --%>
<%--이것도 자바 파일이라서 여기도 import 비슷한 것 해야함 --%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%-- JSTL에서 제공하는 가장 중심되는 라이브러리 core(중심)--%>
    <%-- prefix = "접두사(앞에 붙는 단어)" --%>
    <%--if문을 쓸 수 있게 되는데 태그 앞에 붙는 단어임 , c가 붙은 애가 이거 이용하는거구나 알 수 있음 --%>
    <%--꼭 한 글자 아니어도 되고 core이런걸로 해도 됨 --%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%-- fn == 함수이므로 함수 관련된 것 이거 사용 --%>
    
    <%-- c==core(if,for문 등) --%>
    <%-- fn==functions(배열/리스트 길이 반환, 자르기, 나누기, 등 기능 제공)  --%>
    <%--fn은 el구문에서 씀 --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo List</title>
</head>
<body>
<h1>Todo List</h1>

<!--/io_test/TodoList.dat파일 다 있을 건데 이 파일에 저장된 
List<Todo> 객체를 읽어와 화면에 모두 출력하기  -->
<!-- 메인페이지를 요청하자마자 여기로 포워드 될건데 여기에 객체 얻어와서 여기에 뿌리겠다 -->
<%-- ${todoList[0]} <br>
${todoList[1]} <br> --%>
<% //이게 자바 코드 쓸 수 있는 "스크립틀릿"!!! 가독성 안좋기도 하고 그래서 톰캣이 라이브러리 만들어줌  %>
<h3>전체 Todo 개수(할 일이 전체 몇개인가) : ${fn:length(todoList)} /  완료된 Todo 개수 : ${completeCount}</h3>
<%--${completeCount} : 서블릿에서 request에 담아서 보냈음 --%>
<%-- ${fn:length(todoList)} : todoList의 길이 반환해줌 --%>
<hr>
 <form action="/todo/add"> <%-- method 미작성 시 기본값=GET방식 --%>
     <h4> 할 일 추가 </h4>
     <div>
         제목 : <input type="text" name="title">
     </div>
     <div> <%--textarea 얘도 input태그같은것 --%>
         <textarea name="detail" cols="50" rows="3" placeholder="상세 내용"></textarea>
     </div>
     <button>추가</button>
   </form>





<hr>
<table border="1" style="border-collapse: collapse;">

<%-- //테두리 줄건데 테두리가 다 붙어있으면 좋겠어--%>
<thead>
	<tr>
		<th>할 일 제목</th>
		<th>완료 여부</th>
		<th>등록 날짜</th>
	</tr>
</thead>
<tbody>
	<%--for문 이용해서 몇개있는지 모르니까 다 출력하기 --%>
	<%-- c:forEach == JSTL의 향상된 for문(처음부터 끝까지 순차접근) --%>
	<%-- 여기에는 주석을 이 자바 주석으로 쓰기 --%>
	<c:forEach var="todo" items="${todoList}" varStatus="vs"> 
	<%--아이템들이 묶여 있으니까 items에 배열 넣기 --%>
	<%--var :List,배열에서 하나씩 꺼내서 저장할 변수의 이름 지정
			items : 순차 접근할 List,배열 
			var"Status" : 반복 상태 관련 객체(지금 몇 번째 인덱스를 반복중인지, 첫 바퀴? 마지막?등이 이 안에 저장돼있음)  --%>
			<tr>
			<td> <%--a태그 : get방식!!!! --%>
			<%--쿼리스트링 : 주소에 데이터를 담은 문자열이고, ?부터 시작됨 --%>
			<%-- ?key=value 형태로 적어야 함 --%>
			<a href="/todo/detail?index=${vs.index}">${todo.title}</a>
			<%-- ${vs.index} :  --%>
			</td> <%--todo에서 제목만 얻어오겠다 --%>
			<th><%--여기엔 O/X 나오게 할거임 근데 ${todo.complete}하면 true/false로 나와서 if문 작성 --%>
			<%--근데 JSPL의 if문에는 조건문이랑 else 존재 안함 --%>
			<%--test 속성 : 조건식을 작성하는 속성 --%>
				<c:if test="${todo.complete}">O</c:if>
				<c:if test="${not todo.complete}">X</c:if>
				<%-- !가 태그를 사용하는 언어에서 다른 의미로 해석될 수 있어서 not으로 사용(논리 부정 연산자) --%>
			</th> 
			<td>${todo.regDate}</td>
			</tr>
	</c:forEach>
	
</tbody>

</table>
<hr>
${completeCount}
<hr>
<%-- JSP에서 Java(EL, JSTL), Front(HTML, CSS, JS) 중 
			Java가 해석 우선 순위가 가장 높다!!!(Java 코드가 가장 먼저 해석된다는 뜻) --%>
<c:if test="${not empty sessionScope.message }">
<%-- sessionScope인 message가 비어있지 않으면 true여서 이 스크립트 태그를 화면에 추가해서 실행 --%>

	<script >
	<%--JS 코드 --%>
	const message = "${message}";
	<%-- JSP파일은 Java코드로 변환되는데 JSP안에 HTML,JS코드 쓸 수 있음--%>
	<%-- EL을 이용해서 String message값을 출력할 때 꼭 양쪽에 ""를 추가하기! 안쓰면 JS 오류난다--%>
	alert(message);
	
	</script>
	<%-- session은 브라우저 끄기 전까지는 새로고침할 때마다 계속 나와서 한번만 쓰고 안뜨도록 만들기 --%>
	<%-- sessionScope.message를 한 번만 출력한 후 제거하기 --%>
	<c:remove var="message" scope="session"/>
	<%-- session scope에서 message를 제거하겠다 --%>
	
</c:if>
</body>
</html>