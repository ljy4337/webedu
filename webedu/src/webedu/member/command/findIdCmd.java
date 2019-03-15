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

public class findIdCmd implements Command{
	boolean status = false;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tel = request.getParameter("tel");
		String birth = request.getParameter("birth");
		
		System.out.println("findId Tel : "+tel);
		System.out.println("findId Birth : "+birth);
		
		MemberDAO mdao = MemberDAOImpl.getInstance();
		String id = mdao.findId(tel, birth);

		if(id!=null) {
			request.setAttribute("id", id);
			System.out.println(id);
			status = true;

		}else {
			
			request.setAttribute("memberChk", "등록 정보가 없습니다. 다시 한번 확인해주세요.");
			System.out.println("오류실행! 아이디가 없습니다");
		}
		
		
	}
	public boolean getStatus() {
		return status;
	}

}
