<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
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
		document.frm1.submit();
	}
</script>
<body>
	<%@include file="/top.jsp"%>
	<nav></nav>
	<section>
		<form name="frm1" action="login.do" method="post">
			<h1 id="login">로 그 인</h1>

			<input class="put" type="text" id="id" name="id" placeholder="아이디">
			
			
			<label class="err" id="idlabel" for=""></label> <input class="put"
				type="password" id="password" name="pw" placeholder="비밀번호">
			<label class="err" id="pwlabel" for=""></label>
			<span class="err">${memberChk}</span>
			<!-- capslook 이 켜져있습니다 -->

			<button class="put1" id="loginbtn" type="button" name="button"
				onclick="log()">로그인</button>
		
			<label class="put"><input  type="checkbox" name="khssidlimit" id="khssidlimit" value="y"/>
			로그인상태유지</label>
			
			
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
			<h5 id="etc">
				<a href="FindId_view.do">아이디 찾기</a>  | <a
					href="FindPw_view.do">비밀번호 찾기</a> | <a
					href="memberJoin_view.do">회원가입</a>
			</h5>
		</form>
	</section>
	<aside></aside>
	<%@include file="/footer.jsp"%>


</body>


</html>