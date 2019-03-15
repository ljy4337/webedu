package webedu.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import webedu.DataBaseUtil;
import webedu.member.command.LoginCmd;
import webedu.member.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {

	DataBaseUtil db;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	private static MemberDAO mdao = new MemberDAOImpl();

	private MemberDAOImpl() {
		db = DataBaseUtil.getInstance();
	}

	public static MemberDAO getInstance() {
		return mdao;
	}

	@Override
	public boolean memberJoin(MemberDTO memberDTO) {
		boolean status = false;
		StringBuffer sb = new StringBuffer();

		sb.append("insert into member (id, pw, tel, nickname, gender, region, birth ) ");
		sb.append("values (?, ?, ?, ?, ?, ?, ?) ");

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			pstmt.setString(3, memberDTO.getTel());
			pstmt.setString(4, memberDTO.getNickname());
			pstmt.setString(5, memberDTO.getGender());
			pstmt.setString(6, memberDTO.getRegion());
			pstmt.setString(7, memberDTO.getBirth());
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				status = true;
			}
		} catch (SQLException e) {
			String errLoc = "boolean memberJoin(MemberDTO memberDTO)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}

		return status;
	}

	@Override
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO = new MemberDTO();
		StringBuffer sql = new StringBuffer();
		sql.append("select id, pw, nickname, gender, region, birth, tel ");
		sql.append("from member where id=? ");

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberDTO = new MemberDTO();
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setNickname(rs.getString("nickname"));
				memberDTO.setGender(rs.getString("gender"));
				memberDTO.setRegion(rs.getString("region"));
				memberDTO.setBirth(rs.getString("birth"));
				memberDTO.setTel(rs.getString("tel"));
			}
			;
		} catch (SQLException e) {
			String errLoc = "MemberDTO getMember(String id)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}

		return memberDTO;
	}

	@Override
	public boolean isLogin(String id, String pw) {
		boolean isLogin = false;

		StringBuffer sql = new StringBuffer();
		sql.append("select nickname from member ");
		sql.append("where id=? and pw=? ");

		try {
			System.out.println("isLigin" + id + " " + pw);
			conn = db.getConnection();
			System.out.println("isLigin" + id + " " + pw);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			isLogin = rs.next();
		} catch (SQLException e) {
			String errLoc = "boolean isLogin(String id, String pw)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}
		return isLogin;
	}

	@Override
	public boolean memberModify(MemberDTO memberDTO) {
		boolean status = false;

		StringBuffer sql = new StringBuffer();
		sql.append("update member set pw=?, tel=?, nickname=?, gender=?, region=?, birth=? ");
		sql.append("where id=? and pw=?");

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, memberDTO.getPw());
			pstmt.setString(2, memberDTO.getTel());
			pstmt.setString(3, memberDTO.getNickname());
			pstmt.setString(4, memberDTO.getGender());
			pstmt.setString(5, memberDTO.getRegion());
			pstmt.setString(6, memberDTO.getBirth());
			pstmt.setString(7, memberDTO.getId());
			pstmt.setString(8, memberDTO.getPw());
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				status = true;
			}
		} catch (SQLException e) {
			String errLoc = "MemberDTO memberModify(MemberDTO memberDTO)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}
		return status;
	}

	@Override
	public boolean memberOut(String id, String pw) {
		boolean status = false;

		StringBuffer sql = new StringBuffer();
		sql.append("delete from member  ");
		sql.append("where id=? and pw=? ");

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				status = true;
			}
		} catch (SQLException e) {
			String errLoc = "boolean memberOut(String id, String pw)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}
		return status;
	}

	// 아이디 찾기
	@Override
	public String findId(String tel, String birth) {
		String id = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select id from member ");
		sql.append("where tel=? and birth=? ");

		try {
			System.out.println("findid : " + tel + " " + birth);
			conn = db.getConnection();
			System.out.println("findid2 : " + tel + " " + birth);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, tel);
			pstmt.setString(2, birth);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getString("id");
				System.out.println(id);
			}

		} catch (SQLException e) {
			String errLoc = "findId(String tel, String birth)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}
		return id;

	}
	
	// 비밀번호 찾기
	@Override
	public String findPw(String id, String tel, String birth) {
		String pw = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select pw from member ");
		sql.append("where id=? and tel=? and birth=? ");

		try {
			System.out.println("findpw : " + id + " " + tel + " " + birth);
			conn = db.getConnection();
			System.out.println("findpw2 : " + id + " " + tel + " " + birth);
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, tel);
			pstmt.setString(3, birth);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pw = rs.getString("pw");
			}
		} catch (SQLException e) {
			String errLoc = "boolean findPw(String id, String tel, String birth)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}
		return pw;

	}

	
	// 사용자 로그인 상태 유지 적용
	@Override
	public boolean keepLogin(String id, String khssid, java.sql.Timestamp khssidperiod) {
		boolean status = false;
		StringBuffer sb = new StringBuffer();

		sb.append("update member set khssid=?, khssidperiod=? ");
		sb.append("where id=? ");

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, khssid);
			pstmt.setTimestamp(2, khssidperiod);
			pstmt.setString(3, id);

			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				status = true;
			}
		} catch (SQLException e) {
			String errLoc = "boolean keepLogin(String id, String khssid, java.sql.Timestamp khssidperiod)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}

		return status;
	}

	// 유효한 khssid 가 있는지 체크하여 있다면 회원 id를 반
	@Override
	public String findKhssid(String khssid) {
		String id = null;

		StringBuffer sql = new StringBuffer();
		sql.append("select id from member ");
		sql.append("where khssid=? and khssidperiod > systimestamp ");

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, khssid);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("id");
			}
		} catch (SQLException e) {
			String errLoc = "String findKhssid(String khssid)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}
		return id;
	}

	@Override
	public boolean keepLoginNot(String id, String khssid, Timestamp khssidperiod) {
		boolean status = false;
		StringBuffer sb = new StringBuffer();

		sb.append("update member set khssid=?, khssidperiod=? ");
		sb.append("where id=? ");

		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, khssid);
			pstmt.setTimestamp(2, khssidperiod);
			pstmt.setString(3, id);

			int rows = pstmt.executeUpdate();
			if (rows == 1) {
				status = true;
			}
		} catch (SQLException e) {
			String errLoc = "boolean keepLoginNot(String id, String khssid, Timestamp khssidperiod)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}

		return status;
	}



	

}
