package webedu.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import webedu.FindCriteria;
import webedu.PageCriteria;
import webedu.RecordCriteria;
import webedu.board.dao.BoardDAO;
import webedu.board.dao.BoardDAOImpl;
import webedu.board.dto.BoardDTO;

class BoardTest {

	BoardDAO bdao;
//	@Test
	void test() {
		bdao = BoardDAOImpl.getInstance();

		for(int i=0; i<50; i++) {
			BoardDTO boardDTO = new BoardDTO();
			boardDTO.setBtitle("제목"+i);
			boardDTO.setBid("아이디"+i);
			boardDTO.setBnickname("닉네임"+i);
			boardDTO.setBhit(i);
			boardDTO.setBcontent("내용"+i);

			bdao.write(boardDTO);
			try {
				Thread.sleep(100);
				
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		

	}
//	@Test
	void list() {
		System.out.println("list");
		bdao = BoardDAOImpl.getInstance();
		
		BoardDTO boardDTO = null;
		
		ArrayList<BoardDTO> alist = bdao.list();
		Iterator<BoardDTO> it = alist.iterator();
		
		while(it.hasNext()) {
			boardDTO = it.next();
			System.out.println("boardDTO : " + boardDTO);
			
		}
	
	}
	
//	@Test
	void view() {
		BoardDTO boardDTO = new BoardDTO();
		System.out.println("view");
		bdao = BoardDAOImpl.getInstance();
		String a = "2";
		boardDTO = bdao.view(a);
		System.out.println(boardDTO);
	}
	
//	@Test
	void modify(){
		BoardDTO boardDTO = new BoardDTO();
		System.out.println("modify");
		bdao = BoardDAOImpl.getInstance();
		String title = "제제제제목";
		String con = "내내내내내용";
		int a = 1;
		boardDTO.setBtitle(title);
		boardDTO.setBcontent(con);
		boardDTO.setBnum(a);
		bdao.modify(boardDTO);
		System.out.println("수정완료");
				
		
	}
	
//	@Test
	void del(){
		System.out.println("del");
		bdao = BoardDAOImpl.getInstance();
		String num = "1";
		bdao.delete(num);
		System.out.println("삭제완료");
	}
	@Test
	void search() {
		System.out.println("search");
		bdao = BoardDAOImpl.getInstance();

		int NUM_PER_PAGE = 10;	//한 페이지에 보여줄 레코드 수
		int PAGE_NUM_PAGE = 10;	//한 페이지에 보여줄 페이지 수
		
		int reqPage = 1;		// 요청 페이지
		int totalRec = 0;		// 전체 레코드 수
		
		String searchType = "T";	// 검색어 타입
		String keyword = "첫글이다";		// 검색어

		PageCriteria pc = null;
		RecordCriteria rc = null;	// 검색조건이 없는 경우의 레코드 시작, 종료페이지 연산
		FindCriteria fc = null;		// 검색조건이 있는 경우의 레코드 시작, 종료페이지 연산
		
		// 검색조건이 있는 경우
			BoardDAO boardDAO = BoardDAOImpl.getInstance();
					
			rc = new FindCriteria(reqPage, NUM_PER_PAGE, searchType, keyword);
					
			// 검색목록 총 레코드 수 변환
			totalRec = boardDAO.SearchTotalRec(((FindCriteria)rc).getSearchType(), ((FindCriteria)rc).getKeyword());
				
			pc = new PageCriteria(rc, totalRec, PAGE_NUM_PAGE);
			System.out.println("pc = "+pc);
					
			// 검색목록
			ArrayList<BoardDTO> alist = 
					boardDAO.list(
					rc.getStartRecord(), rc.getEndRecord(),
					((FindCriteria)rc).getSearchType(), ((FindCriteria)rc).getKeyword()
					);
			System.out.println("검색레코드 수 : " +totalRec);
			System.out.println("검색목록 : ");
			System.out.println(alist+"\n");

	}

}
