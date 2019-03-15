package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.board.dao.RboardDAO;
import webedu.board.dao.RboardDAOImpl;
import webedu.board.dto.RboardDTO;


public class RboardWriteCMD implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RboardDTO rboardDTO = new RboardDTO();
		rboardDTO.setBnum(Integer.parseInt(request.getParameter("bnum")));
		rboardDTO.setRid((String)request.getSession().getAttribute("id"));
		rboardDTO.setRnickname((String)request.getSession().getAttribute("nickname"));
//		rboardDTO.setRid("1@1.1");
//		rboardDTO.setRnickname("뭐임마");
		rboardDTO.setRcontent(request.getParameter("rcontent"));
		
		RboardDAO rboardDAO = RboardDAOImpl.getInstance();
		
		int cnt = rboardDAO.write(rboardDTO);
		if(cnt>0) {
			response.getWriter().append("ok");
			System.out.println(request.getParameter("bnum"));
			System.out.println("댓글 등록 성공 : "+cnt);
			
		}
	}

}
