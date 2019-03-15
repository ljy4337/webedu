package webedu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.command.RboardDeleteCMD;
import webedu.board.command.RboardGoodOrBadCMD;
import webedu.board.command.RboardListCMD;
import webedu.board.command.RboardModifyCMD;
import webedu.board.command.RboardReReplyCMD;
import webedu.board.command.RboardTestCMD;
import webedu.board.command.RboardWriteCMD;

/**
 * Servlet implementation class RboardController
 */
@WebServlet("/rboard/*")
public class RboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RboardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("RboardController doget호출");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RboardController actionDo호출!!");
		
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String cmd = uri.substring(conPath.length());

		System.out.println("uri : " + uri);
		System.out.println("conPath : " + conPath);
		System.out.println("cmd : " + cmd);

		Command command = null;
		String viewPage = null; // view 이동경로
		boolean status = false;
		
		switch(cmd) {
		//댓글 등록
			case "/rboard/write":
			command = new RboardWriteCMD();
			command.execute(request, response);
			break;
		//댓글 목록
			case "/rboard/list":
			command = new RboardListCMD();
			command.execute(request, response);
			break;
		//댓글 수정
			case "/rboard/modify":
			command = new RboardModifyCMD();
			command.execute(request, response);
			break;
		//댓글 삭제
			case "/rboard/delete":
			command = new RboardDeleteCMD();
			command.execute(request, response);
			break;
		//댓글 좋아요 or 싫어요
			case "/rboard/good":
			case "/rboard/bad":
			command = new RboardGoodOrBadCMD();
			command.execute(request, response);
			break;
		//대댓글 등록
			case "/rboard/reReply":
			command = new RboardReReplyCMD();
			command.execute(request, response);
			break;
			
		//테스트
			case "/rboard/test" :
			command = new RboardTestCMD();
			command.execute(request, response);
			break;
				
			default:
				break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doDelete 호출");
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPut 호출");
	}

}
