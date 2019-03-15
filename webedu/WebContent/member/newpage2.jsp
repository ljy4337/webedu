<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기 페이지</title>
<link rel="stylesheet" href="/webedu/css/common.css" />

</head>
<script type="text/javascript">


</script>


<body>

	<section id="section">
		비밀번호를 보여주는 페이지 <br>
		<%
			String pw = (String) request.getAttribute("pw");
		%>

		<b>회원님의 비밀번는 <%=pw%> 입니다.
		</b><br>




	</section>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h5 id="etc">
		<a href="login_view.do">로그인</a> | <a href="memberJoin_view.do">비밀번호
			찾기</a> | <a href="memberJoin_view.do">회원가입</a>
	</h5>
</body>

</html>