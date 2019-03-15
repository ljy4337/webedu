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


public class memberModifyCmd implements Command {
	
	private boolean status = false;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String)request.getSession().getAttribute("id");
		String pw = request.getParameter("pw");
		String tel = request.getParameter("tel");
		String nickname = request.getParameter("nickname");
		String gender = request.getParameter("gender");
		String region = request.getParameter("region");
		String birth = request.getParameter("birth");
		
		System.out.println("id(Modify) : "+id);
		System.out.println("pw(Modify) : "+pw);
		System.out.println("tel(Modify) : "+tel);
		System.out.println("nickname(Modify) : "+nickname);
		System.out.println("gender(Modify) : "+gender);
		System.out.println("region(Modify) : "+region);
		System.out.println("birth(Modify) : "+birth);
		
		MemberDAO mdao = MemberDAOImpl.getInstance();
		MemberDTO mdto = new MemberDTO();


			mdto.setId(id);
			mdto.setPw(pw);
			mdto.setNickname(nickname);
			mdto.setTel(tel);
			mdto.setGender(gender);
			mdto.setRegion(region);
			mdto.setBirth(birth);
//			mdto.setId(id);

			System.out.println(mdto);
			boolean result = mdao.memberModify(mdto);
			System.out.println(result);
			if(result) {
				mdto = mdao.getMember(id);
				
				HttpSession session = request.getSession();
				session.setAttribute("id", mdto.getId());
				session.setAttribute("nickname", mdto.getNickname());
				session.setAttribute("memberOK", "ok");
				
				
				System.out.println("수정완료 ");
				System.out.println(mdto);
				status = true;
			}
			else {
				request.setAttribute("errmsg", "비밀번호가 일치하지 않습니다.");
			}
			
		
	}
	public boolean getStatus() {
		return status;
	}

}
