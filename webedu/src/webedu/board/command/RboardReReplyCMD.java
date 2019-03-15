package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.dao.RboardDAO;
import webedu.board.dao.RboardDAOImpl;
import webedu.board.dto.RboardDTO;

public class RboardReReplyCMD implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RboardDAO rdao = RboardDAOImpl.getInstance();
		RboardDTO rdto = new RboardDTO();
		System.out.println("대댓글 호출");
		rdto.setRnum(Integer.parseInt(request.getParameter("rnum")));
//		rdto.setRid((String)request.getSession().getAttribute("id"));
//		rdto.setRnickname((String)request.getSession().getAttribute("nickname"));
		
		rdto.setRid("1234@1234.1234");
		rdto.setRnickname("1234");
		
		rdto.setRcontent(request.getParameter("rcontent"));
//		rdto.setRgroup(Integer.parseInt(request.getParameter("rgroup")));
//		rdto.setRstep(Integer.parseInt(request.getParameter("rstep")));
//		rdto.setRindent(Integer.parseInt(request.getParameter("rindent")));
	

		int cnt = rdao.reply(rdto);
		if(cnt>0) {
			response.getWriter().write("ok");

		}

	}

}
