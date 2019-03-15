package webedu.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.Message;
import webedu.DataBaseUtil;
import webedu.board.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO{
	
	DataBaseUtil db;
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	private static volatile BoardDAO bdao = new BoardDAOImpl();
	
	private BoardDAOImpl() {
		db = DataBaseUtil.getInstance();
		try {
			conn = db.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static BoardDAO getInstance() {
		return bdao;
	}

	//글쓰기
	@Override
	public void write(BoardDTO boardDTO) {
		
		int cnt = 0;
		
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("INSERT INTO board (bnum, btitle, bid, bnickname, ");
		sql.append("bhit, bcontent, bgroup, bstep, bindent) ");
		sql.append("VALUES (boardnum_seq.nextval, ?, ?, ?, 0, ?, boardnum_seq.currval,  0, 0)  ");
		
		try {
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, boardDTO.getBtitle());
			pstmt.setString(2, boardDTO.getBid());
			pstmt.setString(3, boardDTO.getBnickname());
			pstmt.setString(4, boardDTO.getBcontent());

			cnt = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			String errLoc = this.getClass().getName()+"void write(BoardDTO boardDTO)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
	}
	
	//글목록 가져오기
	@Override
	public ArrayList<BoardDTO> list() {
		ArrayList<BoardDTO> alist = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bnum,btitle,bid,bnickname,bcdate,budate, " );
		sql.append("	   bhit,bcontent,bgroup,bstep,bindent " );
		sql.append(" FROM (select * from board order by bgroup desc, bstep asc) ");
		sql.append(" where rownum >= 1 and rownum < 25 ");
//		sql.append(" order by bgroup desc, bstep asc");
		
		try {
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBnum(rs.getInt("bnum"));
				boardDTO.setBtitle(rs.getString("btitle"));
				boardDTO.setBid(rs.getString("bid"));
				boardDTO.setBnickname(rs.getNString("bnickname"));
				boardDTO.setBcdate(rs.getDate("bcdate"));
				boardDTO.setBudate(rs.getDate("budate"));
				boardDTO.setBhit(rs.getInt("bhit"));
				boardDTO.setBcontent(rs.getString("bcontent"));
				boardDTO.setBgroup(rs.getInt("bgroup"));
				boardDTO.setBstep(rs.getInt("bstep"));
				boardDTO.setBindent(rs.getInt("bindent"));
				alist.add(boardDTO);
			}
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"public ArrayList<BoardDTO> list()";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}

		return alist;
	}
	//글목록 (시작레코드, 종료레코드)
	@Override
	public ArrayList<BoardDTO> list(int startRec, int endRec) {
		ArrayList<BoardDTO> alist = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.*  " );
		sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* " );
		sql.append("from board t1)t2 ");
		sql.append("where num between ? and ? ");
		
		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, startRec);
			pstmt.setInt(2, endRec);

			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBnum(rs.getInt("bnum"));
				boardDTO.setBtitle(rs.getString("btitle"));
				boardDTO.setBid(rs.getString("bid"));
				boardDTO.setBnickname(rs.getNString("bnickname"));
				boardDTO.setBcdate(rs.getDate("bcdate"));
				boardDTO.setBudate(rs.getDate("budate"));
				boardDTO.setBhit(rs.getInt("bhit"));
				boardDTO.setBcontent(rs.getString("bcontent"));
				boardDTO.setBgroup(rs.getInt("bgroup"));
				boardDTO.setBstep(rs.getInt("bstep"));
				boardDTO.setBindent(rs.getInt("bindent"));
				alist.add(boardDTO);
			}
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"public ArrayList<BoardDTO> list()";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		
		return alist;
	}
	
	//글읽기
	@Override
	public BoardDTO view(String bnum) {
		BoardDTO boardDTO = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bnum,btitle,bid,bnickname,bcdate,budate, " );
		sql.append("	   bhit,bcontent,bgroup,bstep,bindent " );
		sql.append(" from board ");
		sql.append(" where bnum = ? " );
		try {
			System.out.println(bnum);
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, Integer.parseInt(bnum));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setBnum(rs.getInt("bnum"));
				boardDTO.setBtitle(rs.getString("btitle"));
				boardDTO.setBid(rs.getString("bid"));
				boardDTO.setBnickname(rs.getNString("bnickname"));
				boardDTO.setBcdate(rs.getDate("bcdate"));
				boardDTO.setBudate(rs.getDate("budate"));
				boardDTO.setBhit(rs.getInt("bhit"));
				boardDTO.setBcontent(rs.getString("bcontent"));
				boardDTO.setBgroup(rs.getInt("bgroup"));
				boardDTO.setBstep(rs.getInt("bstep"));
				boardDTO.setBindent(rs.getInt("bindent"));
				System.out.println("1 : " +boardDTO.getBnum());
				//조회수 증가
				updateHit(boardDTO.getBnum());
				System.out.println("2 : "+boardDTO.getBnum());
				System.out.println("조회수 증가 완료");
			}
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"BoardDTO view(String bnum)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		return boardDTO;
	}
	private void updateHit(int bnum) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("update board set bhit = bhit+1 " );
		sql.append(" where bnum = ? " );
		try {
			System.out.println(bnum);
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, bnum);
			
			cnt = pstmt.executeUpdate();
			System.out.println("updateHit : " + cnt);
	
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"void updateHit(int bnum)";
			db.pirintSQLException(e, errLoc);
		}
		
	}

	//글수정
	@Override
	public int modify(BoardDTO boardDTO) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("update board set  " );
		sql.append("	   btitle=? , bcontent=?, budate=sysdate " );
		sql.append(" where bnum = ? " );
		try {
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, boardDTO.getBtitle());
			pstmt.setString(2, boardDTO.getBcontent());
			pstmt.setInt(3, boardDTO.getBnum());
			
			cnt = pstmt.executeUpdate();
			System.out.println("update : " + cnt);
	
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"BoardDTO view(String bnum)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		return cnt;
	}
	
	//글삭제
	@Override
	public int delete(String bnum) {
		int cnt = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("delete from board " );
		sql.append(" where bnum = ? " );
		try {
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, Integer.parseInt(bnum));
			
			cnt = pstmt.executeUpdate();
			System.out.println("delete : " + cnt);
	
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"int delete(String bnum)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		return cnt;
		
	}

	//답글쓰기 양식
	@Override
	public BoardDTO replyView(String bnum) {
		BoardDTO boardDTO = null;

		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bnum,btitle,bid,bnickname,bcdate,budate, " );
		sql.append("	   bhit,bcontent,bgroup,bstep,bindent " );
		sql.append(" from board ");
		sql.append(" where bnum = ? " );
		try {
			System.out.println(bnum);
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, Integer.parseInt(bnum));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				boardDTO = new BoardDTO();
				boardDTO.setBnum(rs.getInt("bnum"));
				boardDTO.setBtitle(rs.getString("btitle"));
				boardDTO.setBid(rs.getString("bid"));
				boardDTO.setBnickname(rs.getNString("bnickname"));
				boardDTO.setBcdate(rs.getDate("bcdate"));
				boardDTO.setBudate(rs.getDate("budate"));
				boardDTO.setBhit(rs.getInt("bhit"));
				boardDTO.setBcontent(rs.getString("bcontent"));
				boardDTO.setBgroup(rs.getInt("bgroup"));
				boardDTO.setBstep(rs.getInt("bstep"));
				boardDTO.setBindent(rs.getInt("bindent"));
				System.out.println("답글 완료");

				
			}
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"BoardDTO replyview(String bnum)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		return boardDTO;
	}

	//답글쓰기
	@Override
	public void reply(BoardDTO boardDTO) {
		
		int cnt1 = 0 ,cnt2 = 0;
		// 이전 답글 step 업데이트(원글 그룹에 대한 세로정렬 재정의)
		cnt1 = updateStep(boardDTO.getBgroup(),boardDTO.getBstep());

		
		StringBuffer sql = new StringBuffer();
		
		
		sql.append("INSERT INTO board (bnum, btitle, bid, bnickname, ");
		sql.append("bhit, bcontent, bgroup, bstep, bindent) ");
		sql.append("VALUES (boardnum_seq.nextval, ?, ?, ?, 0, ?, ?, ?, ?)  ");
		
		try {
//			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, boardDTO.getBtitle());
			pstmt.setString(2, boardDTO.getBid());
			pstmt.setString(3, boardDTO.getBnickname());
			pstmt.setString(4, boardDTO.getBcontent());
			pstmt.setInt(5, boardDTO.getBgroup());		//원글 번호 = 원글그룹
			pstmt.setInt(6, boardDTO.getBstep()+1);		//원글 그룹의 세로정렬(답글단계)
			pstmt.setInt(7, boardDTO.getBindent()+1);		//원글 그룹의 가로정렬(들여쓰기)
			System.out.println(cnt2);
			cnt2 = pstmt.executeUpdate();
			System.out.println(cnt2);
			
//			if(cnt1>0 && cnt2>0) {
//				conn.commit();
//			}
//			else {
//				conn.rollback();
//				String message = "답글오류발생!";
//				throw new Exception(message);
//			}
			
		} catch (SQLException e) {
			String errLoc = this.getClass().getName()+"void	reply(BoardDTO boardDTO)";
			db.pirintSQLException(e, errLoc);
		} catch(Exception e) {	
			e.getMessage();
		}
		finally {
			db.close(conn, pstmt, rs);
		}		
	}

	private int updateStep(int bgroup, int bstep) {
		int cnt = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("update board set bstep = bstep + 1 where bgroup=? and bstep=? ");
		try {
//			conn = db.getConnection();
//			conn.setAutoCommit(false); //commit을 자동으로 못하게 함.
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, bgroup);
			pstmt.setInt(2, bstep);
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			String errLoc = this.getClass().getName()+"void updateStep(int bgroup, int bstep)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}		
		return cnt;
	}

	// 게시글 총계
	@Override
	public int totalRec() {
		int totalRec = 0;
				
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) totalRec from board " );

		try {
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRec = rs.getInt("totalRec");
			}
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"int totalRec()";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		
		
		return totalRec;
	}
	


	// 검색 목록
	@Override
	public ArrayList<BoardDTO> list(int startRecord, int endRecord, String searchType, String keyword) {
		ArrayList<BoardDTO> alist = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append("select t2.*  " );
		sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* " );
		sql.append("		from board t1 ");
		sql.append("		where bnum > 0 ");
		
		switch(searchType){
		case "TC" : // 제목 + 내용
			sql.append("and btitle like '%' || ? || '%' or bcontent like '%' || ? || '%' "); // 오라클에서 || 는 문자열 연결 연산자
			break;
		case "T" : // 제목
			sql.append("and btitle like '%' || ? || '%' ");
			break;
		case "C" : // 내용
			sql.append("and bcontent like '%' || ? || '%' ");
			break;
		case "N" : // 닉네임
			sql.append("and bnickname like '%' || ? || '%'");
			break;
		case "I" : // 아이디
			sql.append("and bid like '%' || ? || '%' ");
			break;
		
		default: // 제목 + 내용 + 작성자
			sql.append("and btitle like '%' || ? || '%' or bcontent like '%' || ? || '%' and bid like '%' || ? || '%' ");
			break;
		}
		sql.append("	)t2 ");
		sql.append("where num between ? and ? ");
		
		try {
			pstmt = conn.prepareStatement(sql.toString());

			switch(searchType){
			case "TC" : // 제목 + 내용
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				pstmt.setInt(3, startRecord);
				pstmt.setInt(4, endRecord);
				break;
			case "T" : // 제목
			case "C" : // 내용
			case "N" : // 닉네임
			case "I" : // 아이디
				pstmt.setString(1, keyword);
				pstmt.setInt(2, startRecord);
				pstmt.setInt(3, endRecord);
				break;
			
			default: // 제목 + 내용 + 작성자
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				pstmt.setString(3, keyword);
				pstmt.setInt(4, startRecord);
				pstmt.setInt(5, endRecord);

				break;
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setBnum(rs.getInt("bnum"));
				boardDTO.setBtitle(rs.getString("btitle"));
				boardDTO.setBid(rs.getString("bid"));
				boardDTO.setBnickname(rs.getNString("bnickname"));
				boardDTO.setBcdate(rs.getDate("bcdate"));
				boardDTO.setBudate(rs.getDate("budate"));
				boardDTO.setBhit(rs.getInt("bhit"));
				boardDTO.setBcontent(rs.getString("bcontent"));
				boardDTO.setBgroup(rs.getInt("bgroup"));
				boardDTO.setBstep(rs.getInt("bstep"));
				boardDTO.setBindent(rs.getInt("bindent"));
				alist.add(boardDTO);
			}
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"ArrayList<BoardDTO> list(int startRecord, int endRecord, String searchType, String keyword)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		
		return alist;

		

	}
	
	// 검색 총계
	@Override
	public int SearchTotalRec(String searchType, String keyword) {
		int totalRec = 0;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) totalRec " );
		sql.append("from (select row_number() over (order by bgroup desc, bstep asc) as num, t1.* " );
		sql.append("		from board t1 ");
		sql.append("		where bnum > 0 ");
		switch(searchType){
		case "TC" : // 제목 + 내용
			sql.append("and btitle like '%' || ? || '%' or bcontent like '%' || ? || '%' "); // 오라클에서 || 는 문자열 연결 연산자
			break;
		case "T" : // 제목
			sql.append("and btitle like '%' || ? || '%' ");
			break;
		case "C" : // 내용
			sql.append("and bcontent like '%' || ? || '%' ");
			break;
		case "N" : // 닉네임
			sql.append("and bnickname like '%' || ? || ");
			break;
		case "I" : // 아이디
			sql.append("and bid like '%' || ? || '%' ");
			break;
		
		default: // 제목 + 내용 + 작성자
			sql.append("and btitle like '%' || ? || '%' or bcontent like '%' || ? || '%' and bid like '%' || ? || '%' ");
			break;
		}
		sql.append("	)t2 ");

		try {
			pstmt = conn.prepareStatement(sql.toString());
			switch(searchType){
			case "TC" : // 제목 + 내용
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				break;
			case "T" : // 제목
			case "C" : // 내용
			case "N" : // 닉네임
			case "I" : // 아이디
				pstmt.setString(1, keyword);
				break;
			
			default: // 제목 + 내용 + 작성자
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				pstmt.setString(3, keyword);
				break;
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRec = rs.getInt("totalRec");
			}
		}catch (SQLException e) {
			String errLoc = this.getClass().getName()+"int SearchTotalRec(String searchType, String keyword)";
			db.pirintSQLException(e, errLoc);
		}finally {
			db.close(conn, pstmt, rs);
		}
		
		
		return totalRec;
		
	}



}
