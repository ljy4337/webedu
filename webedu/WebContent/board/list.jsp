<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximem-scale=2.0">
  <!-- user-scalable=no --> 
  <title>게시판</title>
  <link rel="stylesheet" href="../css/common.css" />

</head>

 <style>
      /* table, tr, th, td{
        border : 1px solid #666;
      } */
      .boardTb1{
        width : 90%;
        margin : 0 auto;

        padding-top : 20px;
      }
      .boardTb1 table{
        width : 100%;
        margin : 0 auto;
        border-top : 2px solid #8d8d8d;
        border-collapse : collapse;
      }
      .boardTb1 th{
        padding : 10px 8px;
        color : #666;
        border-bottom : 1px solid #8d8d8d;
      }

      .boardTb1 td{
        padding : 1px 8px;    /*요소 내부 여백(x축 y축)*/
        line-height : 1.5em; /*줄간격*/
        text-align : center;  /*가로정렬*/
        color : #666;        /*폰트색지정*/
        border-bottom : 1px solid #dbdbdb;

      }

      .boardTb1 td.btitle{
        text-align : left;
      }

      .boardTb1 td.btitle a{
        color : #000;
        text-decoration : none; /* a링크 줄 없게*/
      }
      .boardTb1 td.btitle a:hover{
        color : rgb(230, 73, 200);
        text-decoration : underline;
      }
      .boardTb1 td.textR{
        text-align : right;
      }


      /*페이지 번호스타일*/
      .paging{
        margin : 0 auto;
        margin-top : 20px;
        width : 400px;
        text-align : center;

      }

      .paging .list a{
        width : 30px;
        height : 35px;
        line-height : 35px;
        text-align : center;
        font-weight : bold;
        color : #545454;
        text-decoration : none;

      }

      .paging .list a:hover{
        color : #f00;

      }
      .paging .list a.on{
        color : #00f;
        font-size : 1.6em;
        text-decoration : underline;
      }
      caption{
        margin-bottom : 30px;
        font-size : 2em;
        letter-spacing : 1.1em;
      }

      .btngrp{
        /* width : 90%; */
        text-align : right;
        margin-top : 20px;
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
      .button:hover{
      }

      /* 검색스타일 */
      .search{
        width : 90%;
        margin : 0 auto;

      }

      .search .search_box{
        /* display : inline-block; */
		text-align : center;
      }

      .search .search_form{
        margin : 0 auto;
        margin-top : 20px;
        text-align : center;


      }
      .search select,
      .search input{
        margin-left : 2px;
        padding : 2px;
        border : 1px solid #999;
        vertical-align : bottom;
      }
      .search .button{
        width : 7%;
        background : #4caf50;
        color : #fff;
        font-weight : bold;
        padding : 2px 3px;
        border : none;
        cursor : pointer;
      }
    </style>
    
	<script>
		//document가 로딩이 완료되면
		window.onload = function(){
			init();
		}
		//초기 이벤트 등록
		function init(){
			var btn2 = document.getElementById('btn2');
			btn2.onclick = searchBtn;
		}
		//검색 클릭시 수행
		function searchBtn(){
			var keyword = document.forms[0].keyword.value;
				if(keyword.trim().length == 0){
					alert('검색어를 입력하세요.');
					return;
				}
			
			document.forms[0].submit();
			}
	</script>
	
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
	
/* 	window.onload = function() {
		var btn1 = document.getElementById("pageLink");
		btn1.onclick = ajaxCall;
	
	}
	 */
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

<body>
<%@include file="/top.jsp"%>
  <header id="header">

  </header>

  <section>

    <div class="boardTb1">
      <table summary="게시글 목록">
        <caption> <b>게시글목록</b></caption>
        

          <colgroup>
            <col width="10%">
            <col width="40%">
            <col width="25%">
            <col width="15%">
            <col width="10%">
          </colgroup>
          <thead>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">작성일</th>
            <th scope="col">조회수</th>
          </thead>
        <tbody>
     
		  <c:forEach items="${list }" var="dto">
            <tr>
              <td>${dto.bnum }</td>
              <td class="btitle">
              <c:forEach begin="1" end="${dto.bindent }">&nbsp;&nbsp;</c:forEach>
              <c:if test="${dto.bindent>0 }">
              <img src = "${pageContext.request.contextPath}/images/icon_reply.gif"/>
              </c:if>
              <a href="view.do?${pc.makeSearchURL(pc.recordCriteria.reqPage) }&bnum=${dto.bnum }">${dto.btitle }</a>
              </td>
              <td>${dto.bnickname }(${dto.bid })</td>
              <td>${dto.bcdate }</td>
              <td class="textR">${dto.bhit }</td>
            </tr>
            </c:forEach>
        
        </tbody>
        <tfoot>
          <tr>

            <!-- <td colspan="5">
            </td> -->
          </tr>
          <tr>


          </tr>
        </tfoot>
      </table>
      <div class="btngrp">
        <input class="button" type="button" value="글쓰기" name="btn1" id="btn1" onclick="javascrip:location='<%=request.getContextPath()%>/board/boardWrite_form.do'">
<!--         <input class="button" type="button" value="글쓰기" name="btn1" id="btn1" onclick="location.href='write_form.jsp'">
 -->      </div>
    </div>


    <div class="paging">
      <span class="list">
       <c:if test="${pc.prev }">
      		<a href="boardList_view.do?${pc.makeSearchURL(1) }">처음</a>
        	<a href="boardList_view.do?${pc.makeSearchURL(pc.startPage - 1) }">이전</a>
<!--         	<input type="button" id="pageLink" value="..." />
 -->        	
        </c:if>
       
       <c:forEach begin="${pc.startPage }" end="${pc.endPage }" var="pageNum">
        <!-- 현재페이지와 요청페이지가 다르면 -->
        <c:if test="${pc.recordCriteria.reqPage != pageNum }">
       		 <a href="boardList_view.do?${pc.makeSearchURL(pageNum) }">${pageNum }</a>
     	</c:if>
        
        <!-- 현재페이지와 요청'페이지가 같으면 강조표시 -->
        <c:if test="${pc.recordCriteria.reqPage == pageNum }">
      	 	 <a href="boardList_view.do?${pc.makeSearchURL(pageNum) }" class="on">${pageNum }</a>
     	</c:if>
        <!-- 요청페이지와 현재페이지가 같으면 강조표시 -->
        
        </c:forEach>
        <c:if test="${pc.next }">
		<%-- <a href="boardList_view.do?${pc.makeSearchURL( }">...</a> --%>
        <a href="boardList_view.do?${pc.makeSearchURL(pc.endPage + 1) }">다음</a>
        <a href="boardList_view.do?${pc.makeSearchURL(pc.finalEndPage) }">마지막</a>
        </c:if>
      </span>
    </div>
    
    <div class="search">
      <div class="search_box">
        <form  action="boardList_view.do" method="post">
          <label for="key1"></label>
          <select name="searchType" id="key1">
            <option value="TC">제목+내용</option>
            <option value="T">제목</option>
            <option value="C">내용</option>
            <option value="N">작성자</option>
            <option value="I">아이디</option>
          </select>
          <input type="text" name="keyword" id="keyword" placeholder="검색어를 입력하세요.">
          <input class="button" type="button" value="검색" id="btn2">
        </form>
      </div>
    </div>

  </section>

<%@include file="/footer.jsp"%>
</body>

</html>
