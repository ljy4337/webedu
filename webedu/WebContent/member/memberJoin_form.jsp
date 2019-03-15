<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <link rel="stylesheet" href="../css/common.css" />
  <link rel="stylesheet" href="../css/memberJoin.css">
  <script type="text/javascript">
    function checkForm() {
      var id = document.getElementById('id');
      var pass = document.getElementById('pass');
      var passcheck = document.getElementById('passcheck');
      var nickname = document.getElementById('nickname');
      var tel = document.getElementById('tel');
      var gender = document.getElementById('gender');
      var region = document.getElementById('region');
      var year = document.getElementById('year');
      var month = document.getElementById('month');
      var day = document.getElementById('day');
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
      if (id_pattern.test(id.value)) {
        ck1.innerHTML = "";
        ck11.innerHTML = "멋진 아이디네요!";
      }
      //PW 입력 유무 체크 및 유효성 체크
      if (pass.value == "") {
        ck2.innerHTML = '필수정보 입니다.';
        pass.focus();
        return false;
      }
      if (!(pass.value == "")) {
        ck2.innerHTML = '';

      }
      if (passcheck.value == "") {
        ck3.innerHTML = '필수정보 입니다.';
        passcheck.focus();
        return false;
      }
      if (pass.value != passcheck.value) {
        ck3.innerHTML = '비밀번호가 일치하지 않습니다.';
        passcheck.focus();
        return false;
      }
      if (pass.value == passcheck.value) {
        ck3.innerHTML = "";
        ck33.innerHTML = '사용하셔도 좋습니다.';
      }
      //닉네임 입력 유무 체크
      if (nickname.value == "") {
        ck4.innerHTML = '필수정보 입니다.';
        nickname.focus();
        return false;
      }
      if (nickname.value != "") {
        ck4.innerHTML = '';
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
      console.log(pass.value);
      console.log(nickname.value);
      console.log(tel.value);
      console.log(gender.value);
      console.log(region.value);
      /*       console.log(year.value+"년"+month.value+"월"+day.value+"일");
       */
      console.log(birth);

      document.frm.submit();

    }

    function whatKeyDown(e) {
      ck1.innerHTML = "";
      ck2.innerHTML = "";
      ck3.innerHTML = "";
      ck4.innerHTML = "";
      ck5.innerHTML = "";
      ck6.innerHTML = "";

    }

  </script>

<body>

  <%@include file="/top.jsp"%>
  <nav></nav>
 <div id="div">
  <section>
  <header id="join">회원가입</header>
    <form name="frm" action="memberJoin.do" method="post">

      <label for="id">
        <h3>아이디</h3>
      </label> <input class="put" name="id" id="id" type="email" placeholder="id@domain.com" autocomplete="off" onkeydown="whatKeyDown(event)"> 
      <label for="id" id="ck1" class="errmsg"></label> 
      <label id="ck11" class="msg"></label> 
      
      <label for="pass">
        <h3>비밀번호</h3>
      </label> <input class="put" name="pw" id="pass" type="password" onkeydown="whatKeyDown(event)"> <label id="ck2" class="errmsg"></label> <label for="passcheck" type="password">
        <h3>비밀번호 확인</h3>
      </label> <input class="put" id="passcheck" type="password" onkeydown="whatKeyDown(event)"> <label id="ck3" class="errmsg"></label> <label id="ck33" class="msg"></label> <label for="nickname">
        <h3>닉네임</h3>
      </label> <input class="put" name="nickname" id="nickname" type="text" onkeydown="whatKeyDown(event)" autocomplete="off"> <label id="ck4" class="errmsg"></label> <label for="tel">
        <h3>전화번호</h3>
      </label> <input class="put" name="tel" id="tel" type="text" onkeydown="whatKeyDown(event)" autocomplete="off"> <label id="ck5" class="errmsg"></label> <label for="gender">
        <h3>
          성별<span style="font-size: 13px">(선택)</span>
        </h3>
      </label> <select class="put" id="gender" name="gender">
        <option value="">성별</option>
        <option value="남">남자</option>
        <option value="여">여자</option>
      </select> 
      <label for="region">
        <h3>
          지역<span style="font-size: 13px">(선택)</span>
        </h3>
      </label> 
      <select class="put" id="region" name="region">
        <option value="" selected>지역</option>
        <option value="서울">서울</option>
        <option value="경기">경기</option>
        <option value="충북">충북</option>
        <option value="충남">충남</option>
        <option value="전북">전북</option>
        <option value="전남">전남</option>
        <option value="경북">경북</option>
        <option value="경남">경남</option>
        <option value="강원">강원</option>
        <option value="부산">부산</option>
        <option value="울산">울산</option>
        <option value="대구">대구</option>
        <option value="인천">인천</option>
        <option value="광주">광주</option>
        <option value="대전">대전</option>
        <option value="제주">제주</option>
      </select> <label for="birth">
        <h3>생년월일</h3>
      </label> <input class="put" name="birth" id="birth" type="date"> <label id="ck6" class="errmsg"></label>

    </form>

    <button class="put1" id="btn1" type="button" name="button" onclick="checkForm()">회
      원 가 입</button>

    <hr>


    <h5 id="etc">
      <a href="FindId_view.do">아이디 찾기</a> | <a href="FindPw_view.do">비밀번호
        찾기</a> | <a href="memberJoin_view.do">회원가입</a>
    </h5>

  </section>
  </div>
  <aside></aside>
  <%@include file="/footer.jsp"%>


</body>


</html>