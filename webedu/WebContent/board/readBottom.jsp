<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#rcontents {
	padding: 0;
}
/* 댓글 */
#modifyDiv{
	position : absolute;
	top : 20%; left : 50%;
	width : 400px; height : 180px;
	padding : 20px;
	background:#666;
	z-index : 10;
}
#rcontents div {
	border: 1px solid #666;
}

#rcontents label {
	display: inline-block;
	width: 10%;
}

#rcontents input {
	width: 80%;
	padding: 2px;
	border: 1px solid #ccc;
}

#rcontents textarea {
	width: 80%;
	height: 100px;
	padding: 2px;
	border: 1px solid #ccc;
}

#rcontents input[type=button] {
	width: 100px;
}

/* 페이징 */
#pageNumList li {
	display: inline-block;
}
</style>
<!-- jquery 사용하기 -->
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	var bnum = 10063; //원글번호
	var rereqPage = 1; //요청페이지

	//document가 전부 로딩 됐을때 실행
	$(function() {
		//댓글 목록 보이기
		replyList(rereqPage);

		// 댓글 작성
		$("#replybtn").on("click", function() { //$(#"replybtn").click(function{})
			var rid = $("#rid").val(); //작성자
			var rcontent = $("#rcontent").val(); //댓글본문

			console.log(rid);
			console.log(rcontent);

			//AJAX 비동기 처리기술
			$.ajax({
				type : "POST", //post, get, put, delete
				url : "/webedu/rboard/write",
				dataType : "text",
				data : {
					bnum : bnum,
					rcontent : rcontent
				},

				success : function(result) {
					//댓글 목록 삽입
					replyList(rereqPage);

				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
			});

		});

		// 댓글 목록의 수정버튼 클릭시
		$("#modifyDiv").hide();
		$("#replyList").on("click", "button#b1", function() {
			/* console.log("b1버튼 클릭됨");	 */
			var li = $(this).parent();
			/* 			console.log(li); */
			var rnum = li.attr("data-rnum");
			/* var rcontent = li.text(); */
			var rcontent = li.attr("data-rcontent");	// 수정버튼 클릭시 댓글 value만 보이게
			console.log(rnum);
			console.log(rcontent);

			$(".title-dialog").html(rnum);
			$("#rrcontent").val(rcontent);
			$("#modifyDiv").show("slow");
		});

		// 댓글 수정
		$("#reModifyBtn").on("click", function() {
			var rnum = $(".title-dialog").text();
			/* alert(rnum); */
			var rcontent = $("#rrcontent").val();
			/* 			console.log(rnum);
			 console.log(rrcontent); */
			$.ajax({
				type : "post",
				url : "/webedu/rboard/modify",
				dataType : "text",
				data : {
					rnum : rnum,
					rcontent : rcontent
				},

				success : function(data) {
					console.log("data" + data);
					replyList(rereqPage);

				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
			})
		});

		// 댓글 삭제(버튼 이벤트)

		$("#reDelBtn").on("click", function() {
			var rnum = $(".title-dialog").text();

			$.ajax({
				type : "post",
				url : "/webedu/rboard/delete",
				dataType : "text",
				data : {
					rnum : rnum,

				},

				success : function(data) {
					console.log("data" + data);
					replyList(rereqPage);

				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
			})
		});

		// 댓글 좋아요(버튼 이벤트)
	
		$("#goodBtn").on("click", function() {
			var rnum = $(".title-dialog").text();

			$.ajax({
				type : "post",
				url : "/webedu/rboard/good",
				dataType : "text",
				data : {
					rnum : rnum,
					goodOrbad : "good"
				},

				success : function(data) {
					console.log("data" + data);
					replyList(rereqPage);

				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
			})
		});

		// 댓글 싫어요(버튼 이벤트)
		$("#badBtn").on("click", function() {
			var rnum = $(".title-dialog").text();
			console.log(rnum);
			$.ajax({
				type : "post",
				url : "/webedu/rboard/bad",
				dataType : "text",
				data : {
					rnum : rnum,
					goodOrbad : "bad"
				},

				success : function(data) {
					console.log("data" + data);
					replyList(rereqPage);

				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
			})
		});

		// 댓글 닫기(버튼 이벤트)
		$("#closeBtn").on("click", function() {
			$("#modifyDiv").hide();
		});
		
		//리댓글 작성
		$("#reReplyBtn").on("click", function() {
			var rnum = $(".title-dialog").text(); 
			var rrcontent = $("#rrcontent").val();
			console.log(rnum);
			console.log(rrcontent);

			$.ajax({
				type : "POST",
				url : "/webedu/rboard/reReply",
				dataType : "text",
				data : {
					rnum : rnum,
					rcontent : rrcontent
				},

				success : function(result) {
					
					//댓글 목록 삽입
					replyList(rereqPage);

				},
				error : function(xhr, status, err) {
					console.log("xhr : " + xhr);
					console.log("status : " + status);
					console.log("err : " + err);
				}
			});
		});

		// 페이지 클릭 이벤트
		$("#pageNumList").on("click", "a", function(evt) {
			//이벤트 본래의 행위를 방지 (화면 이동이 일어나면 안됨!)
			evt.preventDefault();
			rereqPage = $(this).attr("href");
			replyList(rereqPage);
		})
	});
	//요청페이지에 대한 댓글목록 가져오기
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

					str += "<span>"
						// 개발자의 필요에 의해 속성을 넣어줄 수 있다.
						// li에 data-rNum 의 속성을 넣어줌(data-rnum에 rec.rnum을 넣어줌)
						str += "<li data-rnum='"+rec.rnum+"' data-rcontent='"+rec.rcontent+"' class='reList'>"
						//들여쓰기
						for (i = 0; i < rec.rindent; i++) {
							str += "&nbsp&nbsp;";
						}
						str += rec.rnum + "|" 
							+ rec.bnum + "|" 
							+ rec.rid + "|"
							+ rec.rnickname + "|" 
							+ rec.rcdate + "|"
							+ rec.rcontent + "|" 
							+ rec.rgood + "|" 
							+ rec.rbad
							+ "|" + "<button id='b1'>수정</button>"
							+ "</li></span>"
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

	// 페이지 구현
	function showPageList(pagecriteria) {

		var str = "";
		console.log(pagecriteria);

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
<body>
	<div id="modifyDiv">
		<span class="title-dialog"></span>번 댓글
		<div>
			<textarea name="rrcontent" id="rrcontent" cols="30" rows="10"></textarea>
		</div>
		<div>
			<button id="reReplyBtn">댓글</button>
			<button id="reModifyBtn">수정</button>
			<button id="reDelBtn">삭제</button>
			<button id="goodBtn">좋아요</button>
			<button id="badBtn">싫어요</button>
			<button id="closeBtn">닫기</button>

		</div>
	</div>
	<!-- 댓글 쓰기 -->
	<div id="rcontents">
		<div>
			<label>작성자</label><input type="text" id="rid" name="rid" />
		</div>
		<div>
			<label>댓글</label>
			<textarea name="rcontent" id="rcontent" cols="30" rows="10"></textarea>
		</div>
		<div>
			<label></label><input type="button" value="작성" id="replybtn" />
		</div>
	</div>
	<!-- 댓글목록 -->
	<div id="replyList"></div>
	<!-- 댓글페이징 -->
	<div id="pageNumList"></div>
</body>
</html>