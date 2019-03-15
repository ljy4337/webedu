package webedu.board.dao;

import java.util.ArrayList;

import webedu.board.dto.BoardDTO;
import webedu.board.dto.RboardDTO;

public interface RboardDAO {
	// 댓글 등록
	int write(RboardDTO rboardDTO);
	
	// 댓글 목록
	ArrayList<RboardDTO> list(String bnum);
	ArrayList<RboardDTO> list(String bnum, int startRec, int endRec);
	
	// 댓글 수정
	int modify(RboardDTO RboardDTO);

	
	// 댓글 삭제
	int delete(String rnum);
	
	// 댓글 좋아요 싫어요
	int goodOrbad(String rnum, String goodOrbad);
	
	// 대댓글 등록
	int reply(RboardDTO rboardDTO);
	
	// 댓글 총계
	int totalRec(String bnum);
	
	// 대댓글 총계
	int replyTotalRec(String bnum);
	
	int updateStep(int rgroup, int rstep);
	
}
