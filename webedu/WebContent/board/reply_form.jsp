<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>답글작성</title>
	<link rel="stylesheet" href="../css/common.css" />

		<style>
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
				/* border-right : 1px solid red; */
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





		</style>

		<script>



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
				document.frm.submit;
			}

			function move(obj) {

				var b1 = document.getElementById('btn1');
				var b2 = document.getElementById('btn2');
				var b3 = document.getElementById('btn3');

				switch (obj.id) {
					case 'btn1': //목록
						location.href = 'boardList_view.do';
						break;

					case 'btn2': //저장
						if(validationCheck()){
							document.frm.submit();
<%-- 							location.href = '<%=request.getContextPath()%>/board/boardList_view.do';
 --%>						}else{
								false;
							}
						break;

					case 'btn3' : //취소
						document.frm.reset();
						return;

				}


			}

		</script>
</head>

<body>




	<%@include file="/top.jsp"%>
	<section id="section">
		<div class="boardTb1">
			<form name="frm" action="reply.do" method="post">
			<input type="hidden" name="bgroup" value="${replyView.bgroup }"/>
			<input type="hidden" name="bstep" value="${replyView.bstep }"/>
			<input type="hidden" name="bindent"value="${replyView.bindent }" />
				<table summary="답글작성">
					<caption> <b>답글작성</b></caption>
					<colgroup>
						<col width=30%>
						<col width="">
					</colgroup>

					<thead>
						<tr>
							<th>번</th>
							<td ><input name="bnum" id="bnum" type="text" readonly value="${replyView.bnum }"></td>
						</tr>
						<tr>
							<th>제목</th>
							<td ><input name="btitle" id="btitle" type="text" value="${replyView.btitle }"></td>
						</tr>
						<tr>
							<th>작성자</th>
							
							<td >
							<%-- <%= session.getAttribute("nickname") %> ( <%= session.getAttribute("id") %> ) --%>
							${replyView.bnickname } / (${replyView.bid })
							<!-- <input name="bnickname" id="bnickname" type="text" placeholder="작성자(10자 이하)"> --></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
								<textarea name="bcontent" id="bcontent" cols="50" rows="15" >${replyView.bcontent }</textarea>
							</td>
						</tr>

					</thead>

				</table>
				<div class="btngrp">
							<input class="button" type="button" name="btn1" id="btn1"value="목록" onclick="move(this)">
							<input class="button" type="button" name="btn2" id="btn2"value="저장" onclick="move(this)">
							<input class="button" type="button" name="btn3" id="btn3"value="취소" onclick="move(this)">
				</div>
			</form>
		</div>

	</section>

	<%@include file="/footer.jsp"%>

</body>

</html>
