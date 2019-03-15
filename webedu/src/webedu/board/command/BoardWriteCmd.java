package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webedu.Command;
import webedu.board.dao.BoardDAO;
import webedu.board.dao.BoardDAOImpl;
import webedu.board.dto.BoardDTO;

public class BoardWriteCmd implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession httpSession = request.getSession();
//		httpSession.get
		
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBtitle(request.getParameter("btitle"));
		boardDTO.setBid((String)request.getSession().getAttribute("id"));
		boardDTO.setBnickname((String)request.getSession().getAttribute("nickname"));
		boardDTO.setBcontent(request.getParameter("bcontent"));
		
		BoardDAO boardDAO = BoardDAOImpl.getInstance();
		boardDAO.write(boardDTO);
		
	}

}
