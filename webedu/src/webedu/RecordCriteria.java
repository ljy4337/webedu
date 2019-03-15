package webedu;

/*
 * input : 요청페이지 번호
 * output : 시작레코드, 종료 레코드
 */
public class RecordCriteria {
	private int reqPage;	//요청페이지
	private int numPerPage;	//한페이지에 보여줄 페이지 수
	
	public RecordCriteria(){}	//디폴트 생성자
	
	public RecordCriteria(int reqPage) {
		this.reqPage = reqPage;
	}
	
	public RecordCriteria(int reqPage, int numPerPage) {
		super();
		this.reqPage = reqPage;
		this.numPerPage = numPerPage;
	}
	public int getReqPage() {
		return reqPage;
	}
	public void setReqPage(int reqPage) {
		this.reqPage = reqPage;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	
	//시작 레코드 계산
	public int getStartRecord() {
		// 1, 11, 21 ...
		return ((this.reqPage - 1) * this.numPerPage) + 1;
	}
	//마지막 레코드 계산
	public int getEndRecord() {
		// 10, 20, 30...
		return this.reqPage * this.numPerPage;
	}
	
	
	@Override
	public String toString() {
		return "RecordCriteria [reqPage=" + reqPage + ", numPerPage=" + numPerPage + "]";
	}


}
