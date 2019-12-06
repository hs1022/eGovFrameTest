package egovframework.example.vo;

public class UsrVO {
	private String usr_id;
	private String usr_nm;
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	
	@Override
	public String toString() {
		return "UsrVO [usr_id=" + usr_id + ", usr_nm=" + usr_nm + "]";
	}
	
}
