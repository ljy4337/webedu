package webedu.member.command;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webedu.Command;
import webedu.member.dao.MemberDAO;
import webedu.member.dao.MemberDAOImpl;
import webedu.member.dto.MemberDTO;



//회원등록
public class MemberJoinCmd implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			MemberDTO mdto = new MemberDTO();
			MemberDAO mdao = MemberDAOImpl.getInstance();
			
	
//			String birth = null;
//			birth = request.getParameter("year")+"-"+request.getParameter("month")+"-"+request.getParameter("day");
			mdto.setId(request.getParameter("id"));
			mdto.setPw(request.getParameter("pw"));
			mdto.setNickname(request.getParameter("nickname"));
			mdto.setTel(request.getParameter("tel"));
			mdto.setGender(request.getParameter("gender"));
			mdto.setRegion(request.getParameter("region"));
			mdto.setBirth(request.getParameter("birth"));
//			mdto.setBirth(birth);
			
			
			
			mdao.memberJoin(mdto);
			
			System.out.println(mdto);
			
			

		
	}


	
}
