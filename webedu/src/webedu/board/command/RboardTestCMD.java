package webedu.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;

public class RboardTestCMD implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 브라우저로 보내어 지는 한글 처리
		response.setContentType("Application/json;charset=utf-8");
		
		// PrintWriter 객체를 통해 결과 처리
		response.getWriter().append("{'name' : '홍길동', 'age' :'27'}");
	}

}
