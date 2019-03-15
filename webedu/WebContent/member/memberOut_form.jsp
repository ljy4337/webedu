<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>탈퇴</title>
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/login.css">
<script type="text/javascript">
	function log() {
		var id = document.getElementById('id');
		var password = document.getElementById('password');

		var idlabel = document.getElementById('idlabel');
		var pwlabel = document.getElementById('pwlabel');

		if (id.value == "") {
			idlabel.innerHTML = '아이디를 입력하세요.';
			id.focus();
			return false;
		}
		if (!(id.value == "")) {
			idlabel.innerHTML = '';

		}
		if (password.value == "") {
			pwlabel.innerHTML = '비밀번호를 입력하세요.';
			password.focus();
			return false;
		}
		if (!(password.value == "")) {
			pwlabel.innerHTML = '';

		} 
		
		 var jbResult = confirm( '탈퇴하시겟습니까?' );
		 if(jbResult == true){
				document.frm4.submit();
		 }
	      

		
	}
</script>
<body>
	<%@include file="/top.jsp"%>
	<nav></nav>
	<section>
		<form name="frm4" action="memberDel.do" method="post">
			<h1 id="login">회 원 탈 퇴</h1>

			<%String id = (String)session.getAttribute("id"); %>
      </label>
      <input class="put" name="id" id="id" type="email"  value="<%=id %>" readonly>
				
				
			<label class="err" id="idlabel" for=""></label> <input class="put"
				type="password" id="password" name="pw" placeholder="비밀번호">
			<label class="err" id="pwlabel" for=""></label>
			<span class="err">${memberChk}</span>
			<!-- capslook 이 켜져있습니다 -->

			<button class="put1" id="loginbtn" type="button" name="button"
				onclick="log()">탈퇴</button>
			<br>
			<hr>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>

		</form>
	</section>
	<aside></aside>
	<%@include file="/footer.jsp"%>


</body>


</html>