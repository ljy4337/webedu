package webedu.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webedu.Command;
import webedu.member.dao.MemberDAO;
import webedu.member.dao.MemberDAOImpl;
import webedu.member.dto.MemberDTO;

public class memberOutCmd implements Command {

	boolean status = false;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String id = request.getParameter("id");
		String id = (String)request.getSession().getAttribute("id");
		String pw = request.getParameter("pw");
		HttpSession session = request.getSession();
		MemberDAO mdao = MemberDAOImpl.getInstance();
		boolean result = mdao.memberOut(id, pw);
		if (result) {
			MemberDTO mdto = mdao.getMember(id);

			
			Cookie[] cookies = request.getCookies();
			String khssid = null;
			//쿠키정보가 있다면 khsession 가져온다
			if(cookies != null) {
				for(Cookie c : cookies) {
					if(c.getName().equals("khssid")) {
						khssid = c.getValue();
						
						//유효기간을 0으로 설정하여 쿠키정보 삭제
						Cookie cookie = new Cookie("khssid", null);
						cookie.setPath("/");
						cookie.setMaxAge(0);
						response.addCookie(cookie);
						System.out.println("쿠키제거함");
					}
				}
			}

			
			status = true;

		} else {
			request.setAttribute("memberChk","비밀번호가 일치하지 않습니다.");

		}
		session.invalidate();


	}
	public boolean getStatus() {
		return status;
	}
}
