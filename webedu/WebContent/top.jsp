<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="header">
	<h1>웹표준</h1>
	<script>
		function logout(){
			if(confirm("로그아웃 하시겠습니까?")){
				location.href="<%=request.getContextPath()%>/member/logout.do";
				return true;
			}else{
				return false;
			}
		}
	
	</script>
	
	<style>
	a{
		text-decoration : none;
	}

   nav>div> .guest{ 
    	text-align:right;
    }
    
    
    nav>div>ul>li{
    	position : relative;
    	text-decoration : none;
    	/* width : 12%; */
    	display : inline-block;
    	margin-right :50px;
    }
    
/*     nav>div>ul>li:hover>a{
    	background-color: rgb(196, 196, 196);
    } */
    nav>div>ul>li .sub{
    	display : none;
    	position: absolute;
    	width:100%;
    }
    nav>div>ul>li:hover .sub{
    	display : block;
    }
    nav>div>ul>li .sub a{
    	display : block;
    	line-height : 40px;
    	width : 100px;
    }
/*     nav>div>ul>li .sub a:hover{
    	background-color : red;
    } */
	</style>

</header>
<body>
	<nav id="navTop">
	<div id='top_menu'>
		<!-- 로그인 후  -->
		<%!String nickname = null;%>
		<%
			String memberok = (String) session.getAttribute("memberOK");
			if (memberok=="ok") {
				nickname = (String) session.getAttribute("nickname");
		%>
		<ul>
			<li><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/home.png" width="50" height="50" alt="로고"></a></li>
			<li><a href="<%=request.getContextPath()%>/board/boardList_view.do">게시판</a></li>
			
			<li><a href="">내정보</a>
				<ul class="sub">
					<li><a href="<%=request.getContextPath()%>/member/memberModify_view.do">회원정보수정</a></li>
					<li><a href="<%=request.getContextPath()%>/member/memberDel_view.do">탈퇴</a></li>
				</ul>
			</li>
					
			<li><a href="#" onclick="logout()" >로그아웃</a></li>
		</ul>
				<ul class="guest">
			<%
				String nickname = (String) session.getAttribute("nickname");
			%>
				<b><%=nickname%>님 환영합니다!</b>
			</ul>
		<%
			} else {
		%>
		<!-- 로그인   -->
		<ul>
		
			<li><a href="<%=request.getContextPath()%>/index.jsp"><img src="<%=request.getContextPath()%>/images/home.png" width="50" height="50" alt="로고"></a></li>
			<li><a href="<%=request.getContextPath()%>/member/login_view.do">로그인</a></li>
			<li><a href="<%=request.getContextPath()%>/member/memberJoin_view.do">회원가입</a></li>
		</ul>
			<ul class="guest">
				<b>Guest</b>
			</ul>
		<%
			}
		%>
	</div>
</nav>
</body>

