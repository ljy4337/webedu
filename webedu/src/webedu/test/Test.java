package webedu.test;


import webedu.DataBaseUtil;
import webedu.member.dao.MemberDAO;
import webedu.member.dao.MemberDAOImpl;
import webedu.member.dto.MemberDTO;


class Test {

	@org.junit.jupiter.api.Test
	//DB연동체크 
	void test() {
	DataBaseUtil db = DataBaseUtil.getInstance();
	System.out.println(db);
		
	}
	
	@org.junit.jupiter.api.Test
	void test2() {
		MemberDAO dao = MemberDAOImpl.getInstance();
		MemberDTO mdto = new MemberDTO();
		mdto.setId("1234@1234.1234");
		mdto.setPw("123");
		mdto.setTel("1111");
		mdto.setNickname("4321");
		mdto.setGender("여");
		mdto.setRegion("부산");
		mdto.setBirth("1990-10-10");
		
		dao.memberModify(mdto);
		
		mdto = dao.getMember(mdto.getId());
		System.out.println(mdto);
		System.out.println("수정완료");
		
	}

}
