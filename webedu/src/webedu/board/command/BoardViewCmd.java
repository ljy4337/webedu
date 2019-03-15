package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.RecordCriteria;
import webedu.board.dao.BoardDAO;
import webedu.board.dao.BoardDAOImpl;
import webedu.board.dto.BoardDTO;

public class BoardViewCmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDAO bdao = BoardDAOImpl.getInstance();
		System.out.println("getParameter :"+request.getParameter("bnum"));
		
		BoardDTO boardDTO = bdao.view(request.getParameter("bnum"));
		
		System.out.println(request.getParameter("reqPage"));
		
		RecordCriteria rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
		request.setAttribute("view", boardDTO);
		request.setAttribute("rc", rc);
		
	}

}
