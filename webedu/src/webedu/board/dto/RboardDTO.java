package webedu.board.dto;

import java.sql.Date;
import java.sql.Timestamp;

//RNUM	NUMBER(10,0)
//BNUM	NUMBER(10,0)
//RID	VARCHAR2(40 BYTE)
//RNICKNAME	VARCHAR2(30 BYTE)
//RCDATE	DATE
//RUDATE	DATE
//RCONTENT	VARCHAR2(300 BYTE)
//RGOOD	NUMBER(5,0)
//RBAD	NUMBER(5,0)
//RGROUP	NUMBER(10,0)
//RSTEP	NUMBER(5,0)
//RINDENT	NUMBER(3,0)

public class RboardDTO {
	private int rnum;
	private int bnum;
	private String rid;
	private String rnickname;
	private Timestamp rcdate;
	private Date rudate;
	private String rcontent;
	private int rgood;
	private int rbad;
	private int rgroup;
	private int rstep;
	private int rindent;
	
	public RboardDTO() {
		
	}
	
	public RboardDTO(int rnum, int bnum, String rid, String rnickname, Timestamp rcdate, Date rudate, String rcontent,
			int rgood, int rbad, int rgroup, int rstep, int rindent) {
		super();
		this.rnum = rnum;
		this.bnum = bnum;
		this.rid = rid;
		this.rnickname = rnickname;
		this.rcdate = rcdate;
		this.rudate = rudate;
		this.rcontent = rcontent;
		this.rgood = rgood;
		this.rbad = rbad;
		this.rgroup = rgroup;
		this.rstep = rstep;
		this.rindent = rindent;
	}


	public int getRnum() {
		return rnum;
	}


	public void setRnum(int rnum) {
		this.rnum = rnum;
	}


	public int getBnum() {
		return bnum;
	}


	public void setBnum(int bnum) {
		this.bnum = bnum;
	}


	public String getRid() {
		return rid;
	}


	public void setRid(String rid) {
		this.rid = rid;
	}


	public String getRnickname() {
		return rnickname;
	}


	public void setRnickname(String rnickname) {
		this.rnickname = rnickname;
	}


	public Timestamp getRcdate() {
		return rcdate;
	}


	public void setRcdate(Timestamp rcdate) {
		this.rcdate = rcdate;
	}


	public Date getRudate() {
		return rudate;
	}


	public void setRudate(Date rudate) {
		this.rudate = rudate;
	}


	public String getRcontent() {
		return rcontent;
	}


	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}


	public int getRgood() {
		return rgood;
	}


	public void setRgood(int rgood) {
		this.rgood = rgood;
	}


	public int getRbad() {
		return rbad;
	}


	public void setRbad(int rbad) {
		this.rbad = rbad;
	}


	public int getRgroup() {
		return rgroup;
	}


	public void setRgroup(int rgroup) {
		this.rgroup = rgroup;
	}


	public int getRstep() {
		return rstep;
	}


	public void setRstep(int rstep) {
		this.rstep = rstep;
	}


	public int getRindent() {
		return rindent;
	}


	public void setRindent(int rindent) {
		this.rindent = rindent;
	}


	@Override
	public String toString() {
		return "RboardDTO [rnum=" + rnum + ", bnum=" + bnum + ", rid=" + rid + ", rnickname=" + rnickname + ", rcdate="
				+ rcdate + ", rudate=" + rudate + ", rcontent=" + rcontent + ", rgood=" + rgood + ", rbad=" + rbad
				+ ", rgroup=" + rgroup + ", rstep=" + rstep + ", rindent=" + rindent + "]";
	}
	
	
	
	
	
}
