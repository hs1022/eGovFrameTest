package egovframework.example.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsrList {
	
	
	private int page=1;

    private int records=2;

    private int total=3;
	
	private List<UsrVO> rows;
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

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
