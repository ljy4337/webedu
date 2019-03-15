package webedu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.command.BoardDeleteCmd;
import webedu.board.command.BoardListCmd;
import webedu.board.command.BoardModifyCmd;
import webedu.board.command.BoardReplyCmd;
import webedu.board.command.BoardReplyViewCmd;
import webedu.board.command.BoardViewCmd;
import webedu.board.command.BoardWriteCmd;
import webedu.member.command.LoginCmd;
import webedu.member.command.LogoutCmd;
import webedu.member.command.MemberJoinCmd;
import webedu.member.command.findIdCmd;
import webedu.member.command.findPwCmd;
import webedu.member.command.memberModifyCmd;
import webedu.member.command.memberOutCmd;
import webedu.member.command.memberSelectCmd;
import webedu.test.AjaxTextCmd;

/**
 * Servlet implementation class FrontController
 */
@WebServlet(value = "*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("doGet호출!!");
		actionDo(request, response);
	}// get방식 url경로상에 값을 입력하여 보여주는 방

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		System.out.println("doPost호출!!");
		actionDo(request, response);

	}// post방식 http상에서 값을 입력받아 보여주는 방

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("actionDo호출!!");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String cmd = uri.substring(conPath.length());

		System.out.println("uri : " + uri);
		System.out.println("conPath : " + conPath);
		System.out.println("cmd : " + cmd);

		Command command = null;
		String viewPage = null; // view 이동경로
		

		switch (cmd) {
		// 회원등록 이동
		case "/member/memberJoin_view.do":
			viewPage = "/member/memberJoin_form.jsp";
			break;

		// 회원등록
		case "/member/memberJoin.do":
			command = new MemberJoinCmd();
			command.execute(request, response);
			viewPage = "/member/login_form.jsp"; // 로그인페이지로 이동
			break;

		// 로그인
		case "/member/login_view.do":
			viewPage = "/member/login_form.jsp"; // 초기화면으로 이동
			break;

		// 로그인 처리
		case "/member/login.do":
			command = new LoginCmd();
			command.execute(request, response);
			boolean status = ((LoginCmd) command).getStatus();
			System.out.println("로그인 성공여부 : " + status);
			if (status) {
				viewPage = "/index.jsp";
			} else {
				viewPage = "/member/login_form.jsp";
			}
			break;
		// 로그아웃
		case "/member/logout.do":
			command = new LogoutCmd();
			command.execute(request, response);
			viewPage = "/member/login_form.jsp";
			break;

		// 회원수정이동
		case "/member/memberModify_view.do":
			command = new memberSelectCmd();
			command.execute(request, response);
			viewPage = "/member/memberModify_form.jsp";
			break;

		// 회원수정
		case "/member/memberModify.do":
			command = new memberModifyCmd();
			command.execute(request, response);
			boolean status1 = ((memberModifyCmd) command).getStatus();

			System.out.println("정보수정여부 :" + status1);
			if (status1) {
				viewPage = "/index.jsp";
			} else {
				System.out.println("수정실패 ");
				viewPage = "/member/memberModify_view.do";
			}
			break;

		// 회원탈퇴 이동
		case "/member/memberDel_view.do":
			viewPage = "/member/memberOut_form.jsp";
			break;

		// 회원탈퇴
		case "/member/memberDel.do":
			command = new memberOutCmd();
			command.execute(request, response);
			boolean status2 = ((memberOutCmd) command).getStatus();
			if (status2) {
				viewPage = "/index.jsp";
			} else {
				viewPage = "/member/memberOut_form.jsp";
			}
			break;

		// 아이디 찾기 이동
		case "/member/FindId_view.do":
			viewPage = "/member/findId_form.jsp";
			break;

		// 아이디 찾기
		case "/member/FindId.do":
			command = new findIdCmd();
			command.execute(request, response);
			boolean status3 = ((findIdCmd) command).getStatus();

			if (status3) {
				viewPage = "/member/newpage.jsp";
			} else {
				viewPage = "/member/findId_form.jsp";
			}
			break;

		// 비밀번호 찾기 이동
		case "/member/FindPw_view.do":
			viewPage = "/member/findPw_form.jsp";
			break;

		// 비밀번호 찾기
		case "/member/FindPw.do":
			command = new findPwCmd();
			command.execute(request, response);
			boolean status4 = ((findPwCmd) command).getStatus();

			if (status4) {
				viewPage = "/member/newpage2.jsp";
			} else {
				viewPage = "/member/findPw_form.jsp";
			}
			break;

		// 게시판 글 작성양식
		case "/board/boardWrite_form.do":
			viewPage = "/board/write_form.jsp"; //글쓰기로 이동
			break;

			// 게시판 글등록 이동
		case "/board/boardWrite.do":
			viewPage = "/board/boardList_view.do"; //게시판목록으로 이동
			break;
			
			// 게시팡 글등록
		case "/board/Write.do":
			command = new BoardWriteCmd();
			command.execute(request, response);
			viewPage = "/board/boardList_view.do"; //게시판목록으로 이동
			break;

			// 게시판 목록 조회
		case "/board/boardList_view.do":
			command = new BoardListCmd();
			command.execute(request, response);
			viewPage = "/board/list.jsp";	//게시판 목록으로 이동
			break;

			// 게시판 글 조회
		case "/board/view.do":
			command = new BoardViewCmd();
			command.execute(request, response);
			viewPage = "/board/read.jsp";	//
			break;
			
			// 게시글 수정
		case "/board/modify.do":
			System.out.println("수정");
			command = new BoardModifyCmd();
			command.execute(request, response);
			viewPage = "/board/view.do";	//수정 후 글 목록으로 이동
			break;
		
			// 게시글 삭제
		case "/board/delete.do" :
			command = new BoardDeleteCmd();
			command.execute(request, response);
			viewPage = "/board/boardList_view.do";
			break;
		
			// 답글작성양식
		case "/board/reply_form.do" :
			System.out.println("답글화면불러오기");
			command = new BoardReplyViewCmd();
			command.execute(request, response);
			viewPage = "/board/reply_form.jsp";

			break;
			
			// 답글 등록
		case "/board/reply.do" :
			System.out.println("답글등록");
			command = new BoardReplyCmd();
			command.execute(request, response);
			System.out.println("커맨드호출완료");
//			viewPage = "/board/boardList_view.do";
//			break;
			return;
			
			// AJAX 테스트
		case "ajaxText.do" :
			command = new AjaxTextCmd();
			command.execute(request, response);
			return;

		default:
			break;
		}
//		요청 디스패치 방식(RequestDispatcer)
//		Server 또는 JSP에서 요청을 받은 후 다른 콤포턴트로 요청을 위임할 수 있다.
//		위임할 때 요청 객체(request)를 동일하게 전달 할 수 있다.

		RequestDispatcher dispat = request.getRequestDispatcher(viewPage);
		dispat.forward(request, response); //forward 방식 

	}


}
