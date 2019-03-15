package webedu.board.dao;

import java.util.ArrayList;

import webedu.board.dto.BoardDTO;

public interface BoardDAO {
	
	// 글쓰기
	void write(BoardDTO boardDTO);
	
	// 글목록 가져오기
	ArrayList<BoardDTO> list();
	ArrayList<BoardDTO> list(int startRec, int endRec);
	
	// 글읽기
	BoardDTO view(String bnum);
	
	// 글수정
	int modify(BoardDTO boardDTO);
	
	// 글삭제
	int delete(String bnum);

	// 원글가져오기
	BoardDTO replyView(String bnum);
	
	// 답글쓰기
	void reply(BoardDTO boardDTO);
	
	// 게시글 총계
	int totalRec();

	// 검색 목록
	ArrayList<BoardDTO> list(int startRecord, int endRecord, String searchType, String keyword);


	// 검색 총계
	int SearchTotalRec(String searchType, String keyword);

	


}
