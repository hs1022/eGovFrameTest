package egovframework.example.vo;

import java.util.List;

public class UsrList {
	private List<UsrVO> rows;

	public List<UsrVO> getRows() {
		return rows;
	}

	public void setRows(List<UsrVO> rows) {
		this.rows = rows;
	}
	
	public UsrList(List<UsrVO> rows) {
		// TODO Auto-generated constructor stub
		this.rows = rows;
	}

}
