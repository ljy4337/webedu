package webedu.board.dto;

import java.io.Serializable;
import java.sql.Date;
//BNUM	NUMBER(10,0)	No		1	게시글번호
//BTITLE	VARCHAR2(90 BYTE)	No		2	"제목
//"
//BID	VARCHAR2(40 BYTE)	No		3	"작성자ID
//"
//BNAME	VARCHAR2(30 BYTE)	No		4	"작성자이름(별칭)
//"
//BCDATE	DATE	No	sysdate 	5	작성일
//BUDATE	DATE	No	sysdate 	6	"수정일
//"
//BHIT	NUMBER(5,0)	No	0 	7	조회수
//BCONTENT	VARCHAR2(300 BYTE)	No		8	본문내용
//BGROUP	NUMBER(5,0)	Yes		9	답글그룹
//BSTEP	NUMBER(5,0)	Yes		10	답변글의 단계
//BINDENT	NUMBER(3,0)	Yes		11	답변글의 들여쓰기
//BNICKNAME	VARCHAR2(30 BYTE)	No		12	"별명


public class BoardDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2870148719286517579L;
	private int bnum;
	private String btitle;
	private String bid;
	private String bnickname;
	private Date bcdate;
	private Date budate;
	private int bhit;
	private String bcontent;
	private int bgroup;
	private int bstep;
	private int bindent;



	
	public BoardDTO() {	
		
		
	}
	
	


	public BoardDTO(int bnum, String btitle, String bid, String bnickname, Date bcdate, Date budate, int bhit,
			String bcontent, int bgroup, int bstep, int bindent) {

		this.bnum = bnum;
		this.btitle = btitle;
		this.bid = bid;
		this.bnickname = bnickname;
		this.bcdate = bcdate;
		this.budate = budate;
		this.bhit = bhit;
		this.bcontent = bcontent;
		this.bgroup = bgroup;
		this.bstep = bstep;
		this.bindent = bindent;
	}




	public int getBnum() {
		return bnum;
	}




	public void setBnum(int bnum) {
		this.bnum = bnum;
	}




	public String getBtitle() {
		return btitle;
	}




	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}




	public String getBid() {
		return bid;
	}




	public void setBid(String bid) {
		this.bid = bid;
	}




	public String getBnickname() {
		return bnickname;
	}




	public void setBnickname(String bnickname) {
		this.bnickname = bnickname;
	}




	public Date getBcdate() {
		return bcdate;
	}




	public void setBcdate(Date bcdate) {
		this.bcdate = bcdate;
	}




	public Date getBudate() {
		return budate;
	}




	public void setBudate(Date budate) {
		this.budate = budate;
	}




	public int getBhit() {
		return bhit;
	}




	public void setBhit(int bhit) {
		this.bhit = bhit;
	}




	public String getBcontent() {
		return bcontent;
	}




	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}




	public int getBgroup() {
		return bgroup;
	}




	public void setBgroup(int bgroup) {
		this.bgroup = bgroup;
	}




	public int getBstep() {
		return bstep;
	}




	public void setBstep(int bstep) {
		this.bstep = bstep;
	}




	public int getBindent() {
		return bindent;
	}




	public void setBindent(int bindent) {
		this.bindent = bindent;
	}


	@Override
	public String toString() {
		return "BoardDTO [bnum=" + bnum + ", btitle=" + btitle + ", bid=" + bid + ", bnickname="
				+ bnickname + ", bcdate=" + bcdate + ", budate=" + budate + ", bhit=" + bhit + ", bcontent=" + bcontent
				+ ", bgroup=" + bgroup + ", bstep=" + bstep + ", bindent=" + bindent + "]";
	}


	
	

	
	
	
}