package webedu.member.command;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.membership.MemberImpl;

import webedu.Command;
import webedu.member.dao.MemberDAO;
import webedu.member.dao.MemberDAOImpl;

public class LogoutCmd implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
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
			if(khssid != null) {
				// 회원테ㅐ이블에서 khssid	= 'none' 으로 유효시간을 현재시간으로 업데이트 
				MemberDAO mdao = MemberDAOImpl.getInstance();

				LocalDateTime localDateTime = LocalDateTime.now();
				Timestamp khssidperiod = Timestamp.valueOf(localDateTime);
				
				String id = (String)session.getAttribute("id");
				mdao.keepLoginNot(id, "none", khssidperiod);
			}
		}
		//기존 세션 데이터를 모두 삭제
		session.invalidate();
	
	}

}
