package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.dao.RboardDAO;
import webedu.board.dao.RboardDAOImpl;

public class RboardGoodOrBadCMD implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RboardDAO rdao = RboardDAOImpl.getInstance();
		System.out.println("good");

		int cnt = rdao.goodOrbad(request.getParameter("rnum"),request.getParameter("goodOrbad"));
		
		if(cnt > 0) {
			response.getWriter().write("ok");
		}

	}

}
