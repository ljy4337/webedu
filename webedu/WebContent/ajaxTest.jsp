<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX TEST</title>
<script>

	/* Ajax
	1. XMLHttpRequest() : var xhttp = new XMLHttpRequest();
	2. 서버에 정보 요청 URL : open("전송방식","url",동기 비동기);
	3. xhttp.onreadystatechange : 이벤트 정의
		상태정보속성
		1) readyState
			0 : request not initialized : 초기화 되지 않음(open()가 호출되지 않은 상태)
			1 : server connection established : opin()가 실행된 상태
			2 : request received : 서버가 클라이언트의 요청을 받았음. send()가 실행된 상태
			3 : processing request :서버가 클라이언트의 요청을 처리중
			4 : request finished and response is ready : 서버가 클라이언트의 요청을 완료했고 서버도 응답이 완료된 상태
		2) status
			200: "OK" : 서버의 응답이 정상적으로 이루어진 상
			403: "Forbidden" : 클라이언트의 요청 권한이 없는 상태
			404: "Page not found" : 클라이언트가 잘못된 url경로를 요청시 응답 결과
			
	4. 서버에 정보 요청 : xhttp.send();
	5. xhttp.responseText or xhttp.responseXML	 */
	
	window.onload = function() {
		var btn1 = document.getElementById("btn1");
		btn1.onclick = ajaxCall;
	
	}
	
	function ajaxCall(){
		var xhttp = new XMLHttpRequest();
		
		xhttp.onreadystatechange = function() {
			console.log(this.readyState +":"+ this.status)
			
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				document.getElementById("demo").innerHTML = "응답성공!!"+this.responseText;
			}
		};

		xhttp.open("GET", "hi.jsp",true);
		xhttp.send();
	}
</script>

</head>
<body>
<div id="demo">

</div>
	<input type="button" value="실행" id="btn1"/>
</body>
</html>