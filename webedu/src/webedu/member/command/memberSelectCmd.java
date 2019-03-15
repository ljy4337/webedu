package webedu.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.member.dao.MemberDAO;
import webedu.member.dao.MemberDAOImpl;
import webedu.member.dto.MemberDTO;


public class memberSelectCmd implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberDAO mdao = MemberDAOImpl.getInstance();
		MemberDTO mdto = mdao.getMember((String)request.getSession().getAttribute("id"));
		
		request.setAttribute("mdto", mdto);
		System.out.println("mdto(selectCmd) : " + mdto);
		
	}

}
