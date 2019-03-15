package webedu.board.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webedu.Command;
import webedu.FindCriteria;
import webedu.PageCriteria;
import webedu.RecordCriteria;
import webedu.board.dao.BoardDAO;
import webedu.board.dao.BoardDAOImpl;
import webedu.board.dto.BoardDTO;

public class BoardListCmd implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int NUM_PER_PAGE = 10;	//한 페이지에 보여줄 레코드 수
		int PAGE_NUM_PAGE = 10;	//한 페이지에 보여줄 페이지 수
		
		int reqPage = 0;		// 요청 페이지
		int totalRec = 0;		// 전체 레코드 수
		
		String searchType = null;	// 검색어 타입
		String keyword = null;		// 검색어

		PageCriteria pc = null;
		RecordCriteria rc = null;	// 검색조건이 없는 경우의 레코드 시작, 종료페이지 연산
		FindCriteria fc = null;		// 검색조건이 있는 경우의 레코드 시작, 종료페이지 연산
		

		
		// 요청 페이지가 없는 경우 1페이지로 이동
		if(request.getParameter("reqPage") == null || request.getParameter("reqPage") == "") {
			reqPage = 1;
		}else {
			reqPage = Integer.parseInt(request.getParameter("reqPage"));
		}
		
		
		System.out.println(reqPage);
		
		// 검색 매개값 체크(searchType, keyword)
		searchType = request.getParameter("searchType");
		keyword = request.getParameter("keyword");
		System.out.println(searchType);
		System.out.println(keyword);
		
		if(keyword == null || keyword.trim().isEmpty()) {
			BoardDAO boardDAO = BoardDAOImpl.getInstance();
			
			rc = new RecordCriteria(reqPage, NUM_PER_PAGE);
			totalRec = boardDAO.totalRec();
		
			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
			System.out.println("pc = "+pc);
			
			ArrayList<BoardDTO> alist = boardDAO.list(rc.getStartRecord(), rc.getEndRecord());
			
			request.setAttribute("list", alist);
			request.setAttribute("pc", pc);
		}
		
		else {
			System.out.println("검색조건이 있는 경우");
			// 검색조건이 있는 경우
			BoardDAO boardDAO = BoardDAOImpl.getInstance();
			
			rc = new FindCriteria(reqPage, NUM_PER_PAGE, searchType, keyword);
			
			// 검색목록 총 레코드 수 변환
			totalRec = boardDAO.SearchTotalRec(((FindCriteria)rc).getSearchType(), ((FindCriteria)rc).getKeyword());
		
			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
			System.out.println("pc = "+pc);
			
			ArrayList<BoardDTO> alist = 
					boardDAO.list(
					rc.getStartRecord(), rc.getEndRecord(),
					((FindCriteria)rc).getSearchType(), ((FindCriteria)rc).getKeyword()
					);
			System.out.println("검색레코드 수 : " +totalRec);
			System.out.println("검색목록 : ");
			System.out.println(alist+"\n");
			request.setAttribute("list", alist);
			request.setAttribute("pc", pc);
		}

//		BoardDAO boardDAO = BoardDAOImpl.getInstance();
//		
//		rc = new RecordCriteria(reqPage, NUM_PER_PAGE);
//		totalRec = boardDAO.totalRec();
//	
//		pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
//		System.out.println("pc = "+pc);
//		
//		ArrayList<BoardDTO> alist = boardDAO.list(rc.getStartRecord(), rc.getEndRecord());
//		
//		request.setAttribute("list", alist);
//		request.setAttribute("pc", pc);
	}

}
