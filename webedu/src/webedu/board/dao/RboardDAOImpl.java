package webedu.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import webedu.DataBaseUtil;
import webedu.board.dto.BoardDTO;
import webedu.board.dto.RboardDTO;

public class RboardDAOImpl implements RboardDAO {
	private DataBaseUtil db;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static volatile RboardDAO rbdao = new RboardDAOImpl();

	private RboardDAOImpl() {
		db = DataBaseUtil.getInstance();
		try {
			conn = db.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static RboardDAO getInstance() {
		return rbdao;
	}

	// 댓글 등록
	@Override
	public int write(RboardDTO rboardDTO) {
		int cnt = 0;

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO replyboard (rnum,bnum, rid, rnickname, rcontent, rgroup) ");
		sql.append("VALUES (rboardnum_seq.nextval, ?, ?, ?, ?, rboardnum_seq.currval)  ");

		try {
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, rboardDTO.getBnum());
			pstmt.setString(2, rboardDTO.getRid());
			pstmt.setString(3, rboardDTO.getRnickname());
			pstmt.setString(4, rboardDTO.getRcontent());

			cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("댓글 등록 성공");
			}

		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "void write(RboardDTO rboardDTO)";
			db.pirintSQLException(e, errLoc);
		}

		return cnt;
	}

	// 댓글 목록
	@Override
	public ArrayList<RboardDTO> list(String bnum) {

		ArrayList<RboardDTO> alist = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT rnum,bnum,rid,rnickname,rcdate,rudate, ");
		sql.append("	   rcontent,rgood,rbad,rgroup,rstep,rindent ");
		sql.append(" FROM (select * from replyboard ");
		sql.append(" 		where bnum = ? ");
		sql.append(" order by rgroup desc, rstep asc) ");
		sql.append(" where rownum >= 1 and rownum < 25 ");

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(bnum));

			rs = pstmt.executeQuery();
			while (rs.next()) {
				RboardDTO rboardDTO = new RboardDTO();
				rboardDTO.setRnum(rs.getInt("rnum"));
				rboardDTO.setBnum(rs.getInt("bnum"));
				rboardDTO.setRid(rs.getString("rid"));
				rboardDTO.setRnickname(rs.getNString("rnickname"));
				rboardDTO.setRcdate(rs.getTimestamp("rcdate"));
				rboardDTO.setRudate(rs.getDate("rudate"));
				rboardDTO.setRcontent(rs.getString("rcontent"));
				rboardDTO.setRgood(rs.getInt("rgood"));
				rboardDTO.setRbad(rs.getInt("rbad"));
				rboardDTO.setRgroup(rs.getInt("rgroup"));
				rboardDTO.setRstep(rs.getInt("rstep"));
				rboardDTO.setRindent(rs.getInt("rindent"));
				alist.add(rboardDTO);
			}
		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "ArrayList<RboardDTO> list(String bnum)";
			db.pirintSQLException(e, errLoc);
		}
		return alist;
	}

	// 댓글 목록
	@Override
	public ArrayList<RboardDTO> list(String bnum, int startRec, int endRec) {
		ArrayList<RboardDTO> alist = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.*  ");
		sql.append("from (select row_number() over (order by rgroup desc, rstep asc) as num, t1.* ");
		sql.append("from replyboard t1 where bnum = ?)t2 ");
		sql.append("where num between ? and ? ");

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(bnum));
			pstmt.setInt(2, startRec);
			pstmt.setInt(3, endRec);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				RboardDTO rboardDTO = new RboardDTO();
				rboardDTO.setRnum(rs.getInt("rnum"));
				rboardDTO.setBnum(rs.getInt("bnum"));
				rboardDTO.setRid(rs.getString("rid"));
				rboardDTO.setRnickname(rs.getNString("rnickname"));
//				rboardDTO.setRcdate(rs.getDate("rcdate"));
				rboardDTO.setRcdate(rs.getTimestamp("rcdate"));
				rboardDTO.setRudate(rs.getDate("rudate"));
				rboardDTO.setRcontent(rs.getString("rcontent"));
				rboardDTO.setRgood(rs.getInt("rgood"));
				rboardDTO.setRbad(rs.getInt("rbad"));
				rboardDTO.setRgroup(rs.getInt("rgroup"));
				rboardDTO.setRstep(rs.getInt("rstep"));
				rboardDTO.setRindent(rs.getInt("rindent"));
				alist.add(rboardDTO);
			}
		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "public ArrayList<BoardDTO> list()";
			db.pirintSQLException(e, errLoc);
		}

		return alist;
	}

	// 댓글 수정
	@Override
	public int modify(RboardDTO RboardDTO) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("update replyboard set  ");
		sql.append("	   rcontent=?, rudate=sysdate ");
		sql.append(" where rnum = ? ");
		try {
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, RboardDTO.getRcontent());
			pstmt.setInt(2, RboardDTO.getRnum());

			cnt = pstmt.executeUpdate();
			System.out.println("update : " + cnt);

		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "int modify(RboardDTO RboardDTO)";
			db.pirintSQLException(e, errLoc);
		}
		return cnt;
	}

	// 댓글 삭제
	@Override
	public int delete(String rnum) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from replyboard ");
		sql.append(" where rnum = ? ");
		try {
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, Integer.parseInt(rnum));

			cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("delete : " + cnt);
			}

		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "int delete(String rnum)";
			db.pirintSQLException(e, errLoc);
		}
		return cnt;
	}

	// 댓글 좋아요 싫어요
	@Override
	public int goodOrbad(String rnum, String goodOrbad) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		switch (goodOrbad) {
		case "good":
			sql.append("update replyboard set rgood = rgood+1 where rnum = ?");
			break;
		case "bad":
			sql.append("update replyboard set rbad = rbad+1 where rnum = ?");
			break;
		default:
			break;
		}

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(rnum));

			pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("좋아요 or 싫어요");
			}
		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "int modify(RboardDTO RboardDTO)";
			db.pirintSQLException(e, errLoc);
		}
		return cnt;
	}

	// 대댓글 등록
	@Override
	public int reply(RboardDTO rboardDTO) {
		int cnt1 = 0, cnt2 = 0;

		// 댓글대상 정보 읽어오기
		RboardDTO originDTO = replyView(rboardDTO.getRnum());

		// 이전 답글 step 업데이트(원글 구룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(originDTO.getRgroup(), originDTO.getRstep());

		StringBuffer sql = new StringBuffer();

		sql.append("INSERT INTO replyboard (rnum,bnum, rid, rnickname, rcontent, rgroup, rstep, rindent) ");
		sql.append("VALUES (rboardnum_seq.nextval, ?, ?, ?, ?, ?, ?, ?)  ");

		try {
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, originDTO.getBnum());
			pstmt.setString(2, rboardDTO.getRid());
			pstmt.setString(3, rboardDTO.getRnickname());
			pstmt.setString(4, rboardDTO.getRcontent());

			pstmt.setInt(5, originDTO.getRgroup()); // 원글 번호 = 원글 그룹
			pstmt.setInt(6, originDTO.getRstep() + 1); // 원글 그룹의 세로정렬(답글단계)
			pstmt.setInt(7, originDTO.getRindent() + 1); // 원글 그룹의 가로정렬(들여쓰기)

			cnt2 = pstmt.executeUpdate();

			if (cnt2 > 0) {
				System.out.println("대댓글 성공");
			}

		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "void reply(RboardDTO rboardDTO)";
			db.pirintSQLException(e, errLoc);
		}

		return cnt2;
	}

	// 댓글대상 읽어오기
	private RboardDTO replyView(int rnum) {
		RboardDTO rdto = new RboardDTO();

		StringBuffer sql = new StringBuffer();
		sql.append("select bnum, rgroup, rstep, rindent from replyBoard where rnum = ? ");
		
		try {

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, rnum);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				rdto.setBnum(rs.getInt("bnum"));
				rdto.setRgroup(rs.getInt("rgroup"));
				rdto.setRstep(rs.getInt("rstep"));
				rdto.setRindent(rs.getInt("rindent"));
			}
			

		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "RboardDTO replyView(int rnum)";
			db.pirintSQLException(e, errLoc);
		} 

		return rdto;
	}

	@Override
	public int updateStep(int rgroup, int rstep) {
		int cnt = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("update board set bstep = bstep + 1 where bgroup=? and bstep > ? ");
		try {

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, rgroup);
			pstmt.setInt(2, rstep);
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "void updateStep(int rgroup, int rstep)";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}
		return cnt;
	}

	// 댓글 총계
	@Override
	public int totalRec(String bnum) {
		int totalRec = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) totalRec from replyboard where bnum = ? ");

		try {
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, Integer.parseInt(bnum));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRec = rs.getInt("totalRec");
				System.out.println("댓글 갯수 : " + totalRec);
			}
		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "int totalRec()";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}

		return totalRec;
	}

	// 대댓글 총계
	@Override
	public int replyTotalRec(String bnum) {
		int totalRec = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) totalRec from replyboard where bnum = ? ");

		try {
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, Integer.parseInt(bnum));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalRec = rs.getInt("totalRec");
				System.out.println("댓글 갯수 : " + totalRec);
			}
		} catch (SQLException e) {
			String errLoc = this.getClass().getName() + "int totalRec()";
			db.pirintSQLException(e, errLoc);
		} finally {
			db.close(conn, pstmt, rs);
		}

		return totalRec;
	}
}
