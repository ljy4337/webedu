<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="../css/common.css" />
<link rel="stylesheet" href="../css/memberJoin.css">

<script>
	function checkForm() {
		var id = document.getElementById('id');
		var tel = document.getElementById('tel');
		var birth = document.getElementById('birth')

		var ck1 = document.getElementById('ck1');
		var ck11 = document.getElementById('ck11');
		var ck2 = document.getElementById('ck2');
		var ck3 = document.getElementById('ck3');
		var ck33 = document.getElementById('ck33');
		var ck4 = document.getElementById('ck4');
		var ck5 = document.getElementById('ck5');
		var ck6 = document.getElementById('ck6');

		//ID 유효성 검사(이메일 정규표현식)
		var id_pattern = /^\w+@\w+\.\w+(\.\w+)?$/;
		//전화번호 유효성 검사
		var tel_pattern = /^\d{2,3}-\d{3,4}-\d{4}$/;
		/* 		var birth = year.value + month.value + day.value;
		 */
		//ID 입력 유무 체크
		if (id.value == "") {
			id.focus();
			ck1.innerHTML = '필수정보 입니다.';
			ck11.innerHTML = "";
			return false;
		}
		if (!(id_pattern.test(id.value))) {
			ck1.innerHTML = '아이디는 이메일이어야 합니다.';
			ck11.innerHTML = "";
			id.focus();
			return false;
		}

		//전화번호 입력 유무 체크
		if (tel.value == "") {
			ck5.innerHTML = '필수정보 입니다.';
			tel.focus();
			return false;
		}
		// if(tel.value.length < 10 || tel.value.length >12){
		//   ck5.innerHTML = '올바른 번호가 아닙니다.';
		//   tel.focus();
		//   return false;
		// }
		if (tel.value != "") {
			ck5.innerHTML = '';
		}

		//생년월일 입력 유무 체크
		if (birth.value == "") {
			ck6.innerHTML = '필수정보 입니다.';
			birth.focus();
			return false;
		}

		console.log(id.value);
		console.log(tel.value);

		/*       console.log(year.value+"년"+month.value+"월"+day.value+"일");
		 */
		console.log(birth);

		document.frm.submit();

	}
	function whatKeyDown(e) {
		ck1.innerHTML = "";
		ck5.innerHTML = "";
		ck6.innerHTML = "";

	}
</script>

</head>

<body>
	<header id="join">아이디 찾기</header>
	<nav id="nav"></nav>


	<section id="section">
		<div id="div">
			<form name="frm" action="FindPw.do" method="post">
				<label>
					<h3>아이디</h3>
				</label> <input class="put" name="id" id="id" type="email"
					placeholder="id@domain.com" autocomplete="off"
					onkeydown="whatKeyDown(event)"> <label id="ck1"
					class="errmsg"></label> <label id="ck11" class="msg"></label>
				<h3>전화번호</h3>
				</label> <input class="put" name="tel" id="tel" type="tel"
					onkeydown="whatKeyDown(event)" autocomplete="off"> <label
					id="ck5" class="errmsg"></label> <label for="gender"> <label
					for="birth">
						<h3>생년월일</h3>
				</label> <input class="put" name="birth" id="birth" type="date"> <label
					id="ck6" class="errmsg"></label>
			</form>
			<span class="errmsg">${memberChk}</span><br>
			<button class="put1" id="btn1" type="button" name="button" onclick="checkForm()">아이디
				찾기</button>
			<br>
			<hr>
			<br> <br> <br> <br> <br> <br> <br>
			<br>
			<h5 id="etc">
				<a href="FindId_view.do">아이디 찾기</a> | <a href="FindPw_view.do">비밀번호
					찾기</a> | <a href="memberJoin_view.do">회원가입</a>
			</h5>
		</div>
	</section>
	<aside id="aside"></aside>

	<%@include file="/footer.jsp"%>
</body>


</html>

