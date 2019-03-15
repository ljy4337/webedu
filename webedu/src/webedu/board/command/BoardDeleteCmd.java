package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.dao.BoardDAO;
import webedu.board.dao.BoardDAOImpl;

public class BoardDeleteCmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO bdao = BoardDAOImpl.getInstance();
		int cnt = bdao.delete(request.getParameter("bnum"));
		System.out.println("삭제된 게시글 : " + cnt+ "개");
		
	}

}
