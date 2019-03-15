package webedu.test;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import webedu.board.dao.RboardDAO;
import webedu.board.dao.RboardDAOImpl;
import webedu.board.dto.RboardDTO;

class RBoardTest {

	RboardDAO rdao = RboardDAOImpl.getInstance();
//	@Test
	void test1() {
		RboardDTO rboardDTO = new RboardDTO();
		rboardDTO.setBnum(10064);
		rboardDTO.setRid("1@1.1");
		rboardDTO.setRnickname("몰라임마");
		rboardDTO.setRcontent("10064에 대한 댓글이다3");
		
		rdao.write(rboardDTO);
		
	}
//	@Test
	void test2() {
		RboardDTO rboardDTO = new RboardDTO();
		
		
		ArrayList<RboardDTO> alist = rdao.list("10063");

		System.out.println(alist+"\n");
	}
	
//	@Test
	void test3() {

		ArrayList<RboardDTO> alist = rdao.list("10063",1,10);

		for(RboardDTO rdto : alist) {
			System.out.println(rdto);
			
		}
	}
//	@Test
	void test4() {
		System.out.println("댓글 수정");
		RboardDTO rboardDTO = new RboardDTO();
		rboardDTO.setRcontent("10064에 대한 댓글이다 수정하였습니다");
		rboardDTO.setRnum(1);
		
		int cnt = rdao.modify(rboardDTO);
		System.out.println("수정건수 : " + cnt);
	}
	
//	@Test
	void test5() {
		System.out.println("댓글 삭제");
		
		int cnt = rdao.delete("3");
		System.out.println("삭제건수 : " + cnt);
	}
//	@Test
	void test6() {
		int cnt = rdao.totalRec("10063");
		System.out.println("댓글갯수 : "+cnt);
	}
	//대댓글 등록
	@Test
	void test7() {
		RboardDTO rboardDTO = new RboardDTO();
		rboardDTO.setBnum(10063);
		rboardDTO.setRid("11@1.1");
		rboardDTO.setRnickname("몰라임마12");
		rboardDTO.setRcontent("10064에 대한 대댓글이다");
		rboardDTO.setRgroup(2);
		rdao.reply(rboardDTO);
		System.out.println("대댓글 : "+rboardDTO);
	}

}
