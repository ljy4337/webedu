package webedu;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import webedu.member.dao.MemberDAO;
import webedu.member.dao.MemberDAOImpl;
import webedu.member.dto.MemberDTO;

/**
 * Servlet Filter implementation class preHandler
 */
@WebFilter("/*")
public class preHandler implements Filter {
	/**
	 * Default constructor.
	 */
	public preHandler() {
		System.out.println("preHandler() 호출됨");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("destroy() 호출됨");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		// pass the request along the filter chain
		System.out.println("호출전 : " + chain.toString());

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		// session 객체를 가져옮
		HttpSession session = req.getSession(false);

		// 쿠키 정보가 있다면 khssid값을 읽어온다.
		String khssid = null;
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("khssid")) {
					if (c.getValue() != null) {
						khssid = c.getValue();
						System.out.println("쿠키찾음:" + c.getValue());
						break;
					}
				}
			}
		}

		// 로그인 세션이 없는경우
		if (session == null && khssid != null) {
			MemberDAO mdao = MemberDAOImpl.getInstance();

			// 회원 테이블에서 khssid가 존재하고 유효기간이 유효한지를 체크하여 유효하면 true를 리턴
			String id = mdao.findKhssid(khssid);

			if (id != null) {
				// 회원정보를 읽어와 세션에 저장
				MemberDTO mdto = mdao.getMember(id);
				System.out.println(mdto);

				String viewPage = "/member/login.do?" + "id=" + mdto.getId() + "&pw=" + mdto.getPw() + "&khssid=''";
				System.out.println("viewPage=" + viewPage);
				RequestDispatcher dispat = request.getRequestDispatcher(viewPage);
				dispat.forward(req, res);

			} else {
				// 로그인 메뉴로 이동
				res.sendRedirect(req.getContextPath() + "/member/login_view.do");
			}
		} else {
			chain.doFilter(req, res);
		}

		System.out.println("호출후 : " + chain.toString());
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init() 호출됨");
	}
}