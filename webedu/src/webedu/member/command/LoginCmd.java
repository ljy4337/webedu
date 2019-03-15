package webedu.member.command;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import webedu.Command;
import webedu.member.dao.MemberDAO;
import webedu.member.dao.MemberDAOImpl;
import webedu.member.dto.MemberDTO;

public class LoginCmd implements Command {

	private boolean status = false;
	String id = null;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = request.getParameter("id");
		String pw = request.getParameter("pw");

		// 로그인 상태 유지
		String khssidlimit = request.getParameter("khssidlimit");

		System.out.println("id(LoginCmd) : " + id);
		System.out.println("pw(LoginCmd) : " + pw);
		System.out.println("khssidlimit(LoginCmd) : " + khssidlimit);

		MemberDAO mdao = MemberDAOImpl.getInstance();
		boolean result = mdao.isLogin(id, pw);

		if (result) {
			MemberDTO mdto = mdao.getMember(id);

			HttpSession session = request.getSession();
			session.setAttribute("id", mdto.getId());
			session.setAttribute("nickname", mdto.getNickname());
			session.setAttribute("memberOK", "ok");

			// 로그인 성공하고 사용자가 로그인 유지상태 체크를 하였을 시
			if (khssidlimit != null && khssidlimit.equals("y")) {
				// 쿠키생성
				Cookie cookie = new Cookie("khssid", session.getId());

				// 컨텍스트경로 이하 요청시 쿠키정보를 함께 보내도록 지정
				cookie.setPath("/");
				// 쿠키 유효기간을 7일로 설정
				int amount = 60 * 60 * 24 * 7;
				cookie.setMaxAge(amount);
				// 생성된 쿠키를 클라이언트에 전송
				response.addCookie(cookie);

				// 현재시간 + 7일 계산하여 Timestamp 타입으로 변환
				LocalDateTime localdateTime = LocalDateTime.now().plusSeconds(amount);
				Timestamp datetime = Timestamp.valueOf(localdateTime);

				// 현재 세션id와 유효시간을 사용자 테이블에 저장
				mdao.keepLogin(mdto.getId(), session.getId(), datetime);

			}

			status = true;

		} else {
			request.setAttribute("memberChk", "등록 정보가 없거나 아이디 또는 비밀번호를 확인해주세요.");
		}

//		request.setAttribute("memberChk", result);
	}

	public boolean getStatus() {
		return status;
	}

}
