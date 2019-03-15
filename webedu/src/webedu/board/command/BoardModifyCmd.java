package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.dao.BoardDAO;
import webedu.board.dao.BoardDAOImpl;
import webedu.board.dto.BoardDTO;

public class BoardModifyCmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("boardCMD");
		BoardDAO bdao = BoardDAOImpl.getInstance();
		BoardDTO boardDTO = new BoardDTO();
		System.out.println(request.getParameter("btitle"));
		System.out.println(request.getParameter("bcontent"));
		System.out.println(Integer.parseInt(request.getParameter("bnum")));
		
		boardDTO.setBnum(Integer.parseInt(request.getParameter("bnum")));
		boardDTO.setBtitle(request.getParameter("btitle"));
		boardDTO.setBcontent(request.getParameter("bcontent"));
		
		System.out.println("boardCMD");

		int cnt = bdao.modify(boardDTO);
		System.out.println("수정건수 : " + cnt);

	}

}
