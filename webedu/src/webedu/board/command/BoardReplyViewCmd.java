package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.dao.BoardDAO;
import webedu.board.dao.BoardDAOImpl;
import webedu.board.dto.BoardDTO;

public class BoardReplyViewCmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("BoardReplyViewCmd 호출");
		BoardDAO bdao = BoardDAOImpl.getInstance();
		
		BoardDTO boardDTO = bdao.replyView(request.getParameter("bnum"));
		
		request.setAttribute("replyView", boardDTO);

	}

}
