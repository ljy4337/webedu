package webedu.member.dao;

import java.sql.Timestamp;

import webedu.member.dto.MemberDTO;

public interface MemberDAO {
	//회원가입
	boolean memberJoin(MemberDTO memberDTO);
	
	// 회원정보가져오기
	MemberDTO getMember(String id);


	// 회원로그인
	boolean isLogin(String id, String pw);
	
	boolean memberModify(MemberDTO memberDTO);
	// 회원 수정 양식 : memberModify.jsp
	// 회원 수정 Command 객체 : MemberModifyCmd
	// DAO : boolean memberModify(MemberDTO memberDTO);
	// FromtController 에서 client UI 입출력 컨트롤 반
	
	
	//탈퇴
	boolean memberOut(String id, String pw);
	
	//아이디 찾기
	String findId(String tel, String birth);
	
	//비밀번호 찾기
	String findPw(String id, String tel, String birth);
	
	//자동로그이상태 유지 적용
	boolean keepLogin(String id, String khssid, Timestamp khssidperiod);
	
	//유효한 khssid가 있는지 체크하여 있다면 회원 id를 반환
	String findKhssid(String khssid);
	
	boolean keepLoginNot(String id, String khssid, Timestamp khssidperiod);

}
