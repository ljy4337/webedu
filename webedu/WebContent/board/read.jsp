<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="webedu.member.dto.MemberDTO"%>


<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>글작성</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" />
<!-- jquery 사용하기 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
body{
	height: 230%;
	
}

	caption{
	margin-top : 40px;
	font-size: 2em;
	margin-bottom: 30px;
	letter-spacing: 1.1em;
}
	.boardTb1{
		margin: 0 auto;
		width: 70%;
		/* height: 60%; */
		padding-top: 20px;
	}
	
		.boardTb2, .boardTb4{
		margin: 0 auto;
		width: 70%;
		padding-top: 20px;
    margin-top: 50px;

	}
  .boardTb3{
    margin: 0 auto;
    width: 70%;
    padding-top: 20px;
    /* outline: 1px solid #dbdbdb; */
    margin-top: 50px;
  }

	/* .boardTb1 form{
		width : 100%;
	} */
	.boardTb1 table{
		width : 100%;
		margin : 0 auto;
		border-top : 2px solid #8d8d8d;
		border-bottom: 2px solid #8d8d8d;
		border-collapse : collapse;
	}

	.boardTb1 th{
		color : #666;
		border-right : 1px solid #dbdbdb; 
		line-height: 1.25em;
		padding : 20px 30px; /*내부요소 간격 (x축, y축)*/
		color : #666;
		border-bottom : 1px solid #dbdbdb;
		text-align: right;
	}

	/* .boardTb1 tr{
		padding : 10px 8px;

	} */
	.boardTb1 td{
		height: 30%;
		padding : 10px 8px;
		color : #666;
		border-bottom : 1px solid #dbdbdb;
		text-align: left;

	}
	
	.boardinput, textarea{
		border : none;
	} 



	 /* .boardTb1 td input{
		width: 100%;
		height: 2em;
		padding-left: 2px;
		font-size: 13px;

	}

	.boardTb1 td textarea{
		padding: 4px 5px;
		font-size: 13px;
	} */
	.boardTb1 td input,
	.boardTb1 td textarea{
		width: 70%;
		padding : 5px 10px;
		resize: none; /* 사용자 임의 변경 불가 */
	}

	.btngrp{
		margin-top: 5px;
		width: 100%;
		text-align: right;
	}

	.btngrp .button{
		width : 7%;
		background : #4caf50;
		color : #fff;
		font-weight : bold;
		padding : 10px;
		border : none;
		cursor : pointer;
	}

	.boardTb2, .boartTb4 h3{
		margin-bottom: 10px;
	}
	.boardTb2, .boardTb4 form{
		padding : 20px 10px;
		border-top : 2px solid #8d8d8d;

		border-bottom : 1px solid #dbdbdb;

	}
  .boardTb2, .boardTb4 td textarea{
    resize: none;
	text-align: left;

  }

	.boardTb2 #rep, .boardTb4 #rep2{
		width: 78%;
		height: 50px;
	}
	
	.boardTb2 #rebtn, .boardTb4 #rebtn2{

		width: 18%;
		height: 55px;
		border: none;
		background : #4caf50;
		color : #fff;


	}
	.boardTb3{

	}
	.boardTb3 #title3{
		padding-bottom: 20px;
		border-bottom : 1px solid #dbdbdb;

	}

  .boardTb3 li{
    
    list-style-type: none;
    
  }
  .boardTb3 li>a{
  	 margin-right : 10px;
    
  }
  .boardTb3 span{
  	 margin-right : 10px;
    
  }
  .boardTb3 #list2{
    text-align: right;
	margin-bottom: 20px;
	margin-right : 20px;
  }

	.boardTb3 ul{
		margin-top: 20px;
	}

	.rcontent{
		text-indent: 0px;
		padding : 5px 5px;
		margin-bottom: 10px;
	}
	.boardTb3 {
	text-aling : center;
	}
#pageNumList li {
	display: inline-block;
	text-align : center;
}




</style>
<%!String usernickname = null;%>
		<%
			usernickname = (String) session.getAttribute("nickname");
		%>
		
<script>
	var rmode;
	var umode;
	var btitle;
	var bcontent;
	//본문이 로딩완료되면
	function init(){
		rmode = document.getElementById("rmode");
		umode = document.getElementById("umode");
		btitle = document.getElementById("btitle");
		bcontent = document.getElementById("bcontent");

		change_rmode();
	}
	//읽기모드
	function change_rmode(){
		//버튼
		rmode.style.display = "";
		umode.style.display = "none";

		//제목, 내용
		btitle.setAttribute("readonly", "");
		bcontent.setAttribute("readonly", "");

		//caption 변경
		document.getElementsByTagName("caption")[0].children[0].innerHTML="<b>게시글보기</b>"
	}
	//수정모드
	function change_umode(){
		//버튼
		rmode.style.display = "none";
		umode.style.display = "";

		//제목, 내용
		btitle.removeAttribute("readonly");
		bcontent.removeAttribute("readonly");

		//caption 변경
		document.getElementsByTagName("caption")[0].children[0].innerHTML="<b>게시글수정</b>"
		
		// 수정가능 배경색 표시
		document.getElementById("btitle").style.background="rgba(100%,0%,0%,5%)";
		document.getElementById("bcontent").style.background="rgba(100%,0%,0%,5%)";

		// 포커스이동
		document.forms[0].bcontent.focus();
		document.forms[0].bcontent.select();
		


	}
	

	  function validationCheck(){
	
	 	var btitle = document.getElementById('btitle');
	 	var bnickname = document.getElementById('bnickname');
	 	var bcontent = document.getElementById('bcontent');
	
	 	if(btitle.value.trim().length == 0){
	 		alert('제목을 입력하세요.');
	 		btitle.focus();
	 		return false;
	 	}
	 	if(btitle.value.trim().length > 30){
	 		alert('제목이 너무 긴데요?????(30자 이하로 입력해주세요.)');
	 		btitle.focus();
	 		return false;
 		}
	 	if(bnickname.value.trim().length == 0){
	 		alert('작성자를 입력하세요.');
	 		bnickname.focus();
	 		return false;
	 	}
	 	if(bnickname.value.trim().length > 10){
	 		alert('닉네임이 너무 길어요 ㅠㅠ (10자 이하로 입력해주세요.)');
	 		bnickname.focus();
	 		return false;
	 	}
	 	if(bcontent.value.trim().length == 0){
	 		alert('내용을 입력하세요.');
 		bcontent.focus();
	 		return false;
	 	}
	 	if(bcontent.value.trim().length > 100){
	 		alert('내용도 좀 많군요.......(100자 이하로 입력해주세요.)');
 		bcontent.focus();
	 		return false;
	 	}
	 	return true;
	 } 
	 
	 //수정모드 유효성 검사
	 function form_val_chk(){
		 var btitle = document.forms[0].btitle.value;
		 var bcontent = document.forms[0].bcontent.value;
		 //제목
		 if(btitle.trim().length == 0){
		 		alert('제목을 입력하세요.');
		 		return false;
		 	}
		 	if(btitle.trim().length > 30){
		 		alert('제목이 너무 긴데요?????(30자 이하로 입력해주세요.)');
		 		return false;
		 	}
		 	if(bcontent.trim().length == 0){
		 		alert('내용을 입력하세요.');
		 		return false;
		 	}
		 	if(bcontent.trim().length > 100){
		 		alert('내용도 좀 많군요.......(100자 이하로 입력해주세요.)');
		 		return false;
		 	}

		 	document.forms[0].submit();

	 }

	function move(obj) {
		//---------------읽기모드 버튼--------------
		var b1 = document.getElementById('btn1'); //답글
		var b2 = document.getElementById('btn2'); //수정하기
		var b3 = document.getElementById('btn3'); //삭제
		var b4 = document.getElementById('btn4'); //목록

		//--------------수정모드 버튼---------------
		var b5 = document.getElementById('btn5'); //수정완료
		var b6 = document.getElementById('btn6'); //목록
		var b7 = document.getElementById('btn7'); //취소
	

		switch (obj.id) {
			case 'btn1':
				location.href = 'reply_form.do?reqPage=${rc.reqPage}&bnum=${view.bnum}'; //답글이동reply_form.do
				break;

			case 'btn2': //수정모드 변환
				change_umode();
				break;

			case 'btn3' : //삭제
				if(confirm('삭제하시겠습니까?')){
					alert('삭제되었습니다.');
					location.href = 'delete.do?reqPage=${rc.reqPage}&bnum=${view.bnum}';
					
				}
					break;
			


			case 'btn5' : //읽기모드 변환(수정처리)
				if(confirm("수정된 내용을 저장하시겠습니까?")){
					form_val_chk();
					
				}
/* 				change_rmode(); 
 */					break;
			case 'btn4' : //목록이동
			case 'btn6' : //목록이동
				location.href = 'boardList_view.do?reqPage=${rc.reqPage}';
					break;
			case 'btn7' :
				location.href = 'view.do?reqPage=${rc.reqPage}&bnum=${view.bnum}';
				break;
				
				default:
					alert('선택된 버튼이 없습니다.');
		}

	}

</script>

<script>
	var bnum = ${view.bnum } //원글번호
	var rereqPage = 1; //요청페이지

	$(function(){
		//댓글 목록 보이기
		replyList(rereqPage);
		//댓글 목록 가져오기
		function replyList(rereqPage) {
		var str = "";
		$.ajax({
			type : "GET",
			url : "/webedu/rboard/list?rereqPage=" + rereqPage + "&bnum="
					+ bnum,
			dataType : "json",

			success : function(data, status, xhr) {
				console.log("data : " + data);
				console.log("data.result : " + data.result);
				console.log("data.pagecriteria : " + data.pagecriteria);

				$.each(data.result, function(idx, rec) {
					console.log("idx : " + idx);
					console.log("rec : " + rec);

					str += "<table> <thead> <ul> <li class='reList'>"
						// 개발자의 필요에 의해 속성을 넣어줄 수 있다.
						// li에 data-rNum 의 속성을 넣어줌(data-rnum에 rec.rnum을 넣어줌)
						str += "<a href='#'>"+rec.rnickname+"</a> </li>"
						//들여쓰기
					/* 	for (i = 0; i < rec.rindent; i++) {
							str += "&nbsp&nbsp;";
						} */
						str += "<div> <span>"+ rec.rcontent + "</span> </div> <div> <li id='list2' data-rnum='"+rec.rnum+"' data-rcontent='"+rec.rcontent+"'> <a id='modify'>수정</a><a id='good'>좋아요</a><span>"+rec.rgood+"</span><a id='bad'>싫어요</a>"+rec.rbad+" <a id='del'>삭제</a></li></div></ul></thead></table>"
						
					
					console.log(str);

					// 댓글 목록 삽입
					$("#replyList").html(str);
				});

				// 페이지 리스트 호출
				showPageList(data.pagecriteria); 

			},
			error : function(xhr, status, err) {
				console.log("xhr : " + xhr);
				console.log("status : " + status);
				console.log("err : " + err);
			}

		});
	}
		// 댓글 등록
		$("#rebtn").on("click", function(){
			var nickname = $("#nickname").val(); //로그인 한 유저의 닉네임
			var rcontent = $("#rep").val();	//댓글 내용
			
			console.log(nickname);
			console.log(rcontent);
			
			//AJAX 비동기 처리기술
			 $.ajax({
				type : "POST",
				url : "/webedu/rboard/write",
				dataType : "text",
				data : {
					bnum : bnum,
					rcontent : rcontent
				},
				 success : function(result){
					//댓글 목록 삽입
					replyList(rereqPage);
				}, 
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
			})
		});
		$("#boardTb4").hide();
		// 수정 링크 클릭
		$("#replyList").on("click", "a#modify", function() {
			console.log("수정링크 클릭");
			var li = $(this).parent();
			console.log(li);
			var rnum = li.attr("data-rnum");
			console.log(rnum);
			var rcontent = li.attr("data-rcontent");
			console.log(rcontent);
			$("#rep2").focus();
			$(".rernum").html(rnum);
			$("#rep2").val(rcontent);
			$("#boardTb4").show();
		});
		$("#closereply").on("click", function(){
			console.log("댓글닫기 클릭");
			$("#boardTb4").hide();
		});
		
		//수정 댓글 등록
		$("#rebtn2").on("click", function(){
			var rnum = $(".rernum").text();
			console.log(rnum);
			var rcontent = $("#rep2").val();
			console.log(rcontent);
			
			$.ajax({
				type : "post",
				url : "/webedu/rboard/modify",
				dataType : "text",
				data : {
					rnum : rnum,
					rcontent : rcontent
				},
				success : function(data){
					console.log("data : "+data);
					replyList(rereqPage);
				},
				error : function(xhr, status, err){
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
				
			})
			$(".boardTb4").hide();
		});
		
		//댓글 삭제
		$("#replyList").on("click", "a#del", function(){
			if(confirm('댓글을 삭제하시겠습니까?')){
				console.log("del 링크 클릭");
				var li = $(this).parent();
				console.log(li);
				var rnum = li.attr("data-rnum");
				console.log(rnum);
				
				$.ajax({
					type : "post",
					url : "/webedu/rboard/delete",
					dataType : "text",
					data : {
						rnum : rnum,
					},
					success : function(data){
						console.log("data : "+data);
						replyList(rereqPage);
					},
					error : function(xhr, status, err){
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}
					
				})
				alert('삭제완료!');
				return true;
			}else{
				return false;
			}
			
			

		});
		
		//댓글 좋아요
		$("#replyList").on("click", "a#good", function(){
	
				console.log("good 링크 클릭");
				var li = $(this).parent();
				console.log(li);
				var rnum = li.attr("data-rnum");
				console.log(rnum);
				
				$.ajax({
					type : "post",
					url : "/webedu/rboard/good",
					dataType : "text",
					data : {
						rnum : rnum,
						goodOrbad : "good"
					},
					success : function(data){
						console.log("data : "+data);
						replyList(rereqPage);
					},
					error : function(xhr, status, err){
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}
					
				})
		});
		
		//댓글 좋아요
		$("#replyList").on("click", "a#bad", function(){
	
				console.log("bad 링크 클릭");
				var li = $(this).parent();
				console.log(li);
				var rnum = li.attr("data-rnum");
				console.log(rnum);
				
				$.ajax({
					type : "post",
					url : "/webedu/rboard/bad",
					dataType : "text",
					data : {
						rnum : rnum,
						goodOrbad : "bad"
					},
					success : function(data){
						console.log("data : "+data);
						replyList(rereqPage);
					},
					error : function(xhr, status, err){
						console.log("xhr : " + xhr);
						console.log("status : " + status);
						console.log("err : " + err);
					}
					
				})
		});
		
		// 페이지 클릭 이벤트
		$("#pageNumList").on("click", "a", function(evt) {
			//이벤트 본래의 행위를 방지 (화면 이동이 일어나면 안됨!)
			evt.preventDefault();
			rereqPage = $(this).attr("href");
			replyList(rereqPage);
		})
		
	});
	

	
	function showPageList(pagecriteria){
		var str = "";
		
		// 이전페이지여부
		if (pagecriteria.prev) {
			// 처음  ▶︎▷◀︎◁
			str += "<li><a href='1'>처음</a></li>";

			// 이전 페이지
			str += "<li><a href='" + (pagecriteria.startPage - 1)
					+ "'>이전</a></li>";

		}
		// 페이지 1~10
		for (i = pagecriteria.startPage, end = pagecriteria.endPage; i <= end; i++) {

			str += "<li><a href='"+i+"'>" + i + "</a></li>";
		}

		// 다음페이지여부
		if (pagecriteria.next) {
			// 다음 페이지
			str += "<li><a href='" + (pagecriteria.endPage + 1)
					+ "'>다음</a></li>";
			// 마지막 페이지
			str += "<li><a href='" + (pagecriteria.finalEndPage)
					+ "'>마지막</a></li>";
		}

		// 페이징 삽입
		$("#pageNumList").html(str);
	}
	
	
</script>
</head>
<body onload="init()">
<%@include file="/top.jsp"%>

	<section id="section">
		<div class="boardTb1">
				<form name="frm" action="modify.do" method="post">
				<input type="hidden" name="bnum" value=${view.bnum }  />
				<input type="hidden" name="reqPage" value=${rc.reqPage}  />
				<table summary="게시글보기">
					<caption> <b>게시글보기</b></caption>
					<colgroup>
						<col width=30%>
						<col width="">
					</colgroup>

					<thead>
						<tr>
							<th>조회수</th>
							<td><input class="boardinput" name="bhit" id="bhit" type="text" readonly value="${view.bhit }"></td>
						</tr>
						<tr>
						<tr>
							<th>작성일</th>
							<td><input class="boardinput" name="cdate" id="cdate" type="text" readonly value="${view.bcdate }"></td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input class="boardinput" name="btitle" id="btitle" type="text" value="${view.btitle }"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input class="boardinput" name="bnickname" id="bnickname" type="text" readonly value="${view.bnickname }(${view.bid })"></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea class="boardinput" name="bcontent" id="bcontent" cols="50" rows="15" >${view.bcontent }</textarea>
							</td>
						</tr>

					</thead>

				</table>
				<div class="btngrp" id="rmode">
					<input class="button" type="button" name="btn1" id="btn1" value="답글" onclick="move(this)">
					<input class="button" type="button" name="btn2" id="btn2" value="수정" onclick="move(this)">
					<input class="button" type="button" name="btn3" id="btn3" value="삭제" onclick="move(this)">
					<input class="button" type="button" name="btn4" id="btn4" value="목록" onclick="move(this)">
				</div>

				<div class="btngrp" id="umode">
					<input class="button" type="button" name="btn5" id="btn5" value="수정완료" onclick="move(this)">
					<input class="button" type="button" name="btn7" id="btn7" value="취소" onclick="move(this)">
					<input class="button" type="button" name="btn6" id="btn6" value="목록" onclick="move(this)">
					
				</div>
			</form>
		</div>
		<div class="boardTb2">
		<h3>댓글달기</h3>
			<form class="form2" action="">
			
			
				<h3  ><%=usernickname %></h3>
				<input id="nickname" type="hidden" name="" value="<%=usernickname %>" />
				<input id="rep" type="text" name="rep" >
				<input id="rebtn" type="button" value="등록">

			</form>
		</div>

		<div class="boardTb3">
			<div id="title3">
				<h3>댓글</h3>
			</div>
			<div id="replyList"></div>
			<div id="pageNumList"></div>

		</div>
		<div class="boardTb4" id="boardTb4">
		<h3>댓글수정</h3>
			<form class="form2" action="">
				
				<h3><%=usernickname %></h3>
				<input class="rernum" type="hidden" name="" />
				<input id="nickname" type="hidden" name="" value="<%=usernickname %>" />
				<input id="rep2" type="text" name="rep2" />
				<input id="rebtn2" type="button" value="등록"/>
				<a id="closereply">닫기</a>

			</form>
		</div>

			<!-- 댓글처리부분 -->
	<%-- <%@include file="readBottom.jsp" %> --%>

	</section>

<%@include file="/footer.jsp"%>
	

</body>

</html>
