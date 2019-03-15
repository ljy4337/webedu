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

public class BoardReplyCmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO bdao = BoardDAOImpl.getInstance();

		
		System.out.println("커맨드 호출");
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBtitle(request.getParameter("btitle"));
		boardDTO.setBid((String)request.getSession().getAttribute("id"));
		boardDTO.setBnickname((String)request.getSession().getAttribute("nickname"));
		boardDTO.setBcontent(request.getParameter("bcontent"));
		boardDTO.setBgroup(Integer.parseInt(request.getParameter("bgroup")));
		boardDTO.setBstep(Integer.parseInt(request.getParameter("bstep")));
		boardDTO.setBindent(Integer.parseInt(request.getParameter("bindent")));
		System.out.println("DTOset완료");
		BoardDAO boardDAO = BoardDAOImpl.getInstance();
		System.out.println(boardDTO);
		boardDAO.reply(boardDTO);
		
		response.sendRedirect(request.getContextPath()+"/board/boardList_view.do"); //redirect 방식
		
//		boardDTO = bdao.view(request.getParameter("bnum"));
//		
//		RecordCriteria rc = new RecordCriteria(Integer.parseInt(request.getParameter("reqPage")));
//		
//		request.setAttribute("view", boardDTO);
//		request.setAttribute("rc", rc);
	}

}
