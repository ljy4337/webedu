package webedu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;

public class AjaxTextCmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		PrintWriter out = response.getWriter();
//		out.append("hello?");
		
		response.getWriter().write("hello");
		
	}

}
