package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.dao.RboardDAO;
import webedu.board.dao.RboardDAOImpl;
import webedu.board.dto.RboardDTO;

public class RboardModifyCMD implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RboardDAO rdao = RboardDAOImpl.getInstance();
		RboardDTO rdto = new RboardDTO();
		
		rdto.setRcontent(request.getParameter("rcontent"));
		rdto.setRnum(Integer.parseInt(request.getParameter("rnum")));
		
		int cnt = rdao.modify(rdto);
		if(cnt >0) {
			response.getWriter().append("ok");
			System.out.println("수정 완료 : "+cnt);

		}
		

	}

}
