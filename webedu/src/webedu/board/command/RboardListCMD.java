package webedu.board.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.PageCriteria;
import webedu.RecordCriteria;
import webedu.board.dao.RboardDAO;
import webedu.board.dao.RboardDAOImpl;
import webedu.board.dto.RboardDTO;

public class RboardListCMD implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final int NUM_PER_PAGE = 10;
		final int PAGENUM_PER_PAGE = 10;
		int rereqPage = 0;
		
		// 요청페이지가 없으면 1페이지로 이동
		if(request.getParameter("rereqPage")==null || request.getParameter("rereqPage").trim().isEmpty()) {
			rereqPage = 1;
		}else {
			rereqPage = Integer.parseInt(request.getParameter("rereqPage"));
		}
		
		// 원글 번호
		String bnum = request.getParameter("bnum");
		
		// 요청 페이지와 한페이지에 보여줄 댓글수를 입력받아 시작레코드와 종료레코드를 구한다.
		RecordCriteria rc = new RecordCriteria(rereqPage, NUM_PER_PAGE);
		
		RboardDAO rboardDAO = RboardDAOImpl.getInstance();
		ArrayList<RboardDTO> alist = rboardDAO.list(bnum, rc.getStartRecord(), rc.getEndRecord());
	
		// 페이징
		int totalRec = rboardDAO.replyTotalRec(bnum);
		PageCriteria pc = new PageCriteria(rc, totalRec, PAGENUM_PER_PAGE);
		/*
		 * 	json
		 * 	{
		 * 		result : [{
		 * 					"rnum":rnum,
		 * 					"bnum":bnum,
		 * 					"rcdate":rcdate,
		 * 					"rcontent":rcontent,
		 * 					"rgood":rgood
		 * 					"rbad":rbad
		 * 					"rgroup":rgroup
		 * 					"rstep":rstep
		 * 					"rindent":rindent
		 * 		},,
		 * 		],
		 *		pagecriteria : {
		 *						"startpage" : startpage,
		 *						"endpage" : endpage,
		 *						"finalpage" : finalpage,
		 *						"prev" : prev,
		 *						"next" : next,
		 *		}
		 *}
		 */
		// json 포맷으로 요청결과값 응답
		StringBuffer str = new StringBuffer();
		str.append("{ \"result\" : [ ");
		int i = 0;
		for(RboardDTO rdto : alist) {
			i++;
			str.append("{\"rnum\" :\"" 	+rdto.getRnum()				+"\"," );
			str.append("\"bnum\" :\"" 		+rdto.getBnum()			+"\"," );
			str.append("\"rid\" :\"" 		+rdto.getRid()			+"\"," );
			str.append("\"rnickname\" :\""	+rdto.getRnickname()	+"\"," );
			str.append("\"rcdate\" :\"" 	+rdto.getRcdate()		+"\"," );
			str.append("\"rcontent\" :\"" 	+rdto.getRcontent()		+"\"," );
			str.append("\"rgood\" :\""	 	+rdto.getRgood()		+"\"," );
			str.append("\"rbad\" :\"" 		+rdto.getRbad()			+"\"," );
			str.append("\"rgroup\" :\"" 	+rdto.getRgroup()			+"\"," );
			str.append("\"rstep\" :\"" 	+rdto.getRstep()			+"\"," );
			
			// 마지막 레코드가 아니면 "," 추가
			if(alist.size() == i) {
				str.append("\"rindent\" :\"" +rdto.getRindent()	+ "\"}");

			}else {
				str.append("\"rindent\" :\""+rdto.getRindent()	+"\"},");
			}
		}
		str.append("],");
		
		str.append("\"pagecriteria\":{");
		str.append("\"startPage\":"		+pc.getStartPage()	+",");
		str.append("\"endPage\":"		+pc.getEndPage()		+",");
		str.append("\"finalEndPage\":"	+pc.getFinalEndPage()	+",");
		str.append("\"prev\":"			+pc.isPrev()			+",");
		str.append("\"next\":"			+pc.isNext()			);
		str.append("}}");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(str.toString());;
	}

}
