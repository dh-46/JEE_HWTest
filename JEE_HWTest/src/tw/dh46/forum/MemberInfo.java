package tw.dh46.forum;

public class MemberInfo {
	private String account;
	private String email;
	private String realname;
	
	public MemberInfo(String account, String email, String realname) {
		this.account = account;
		this.realname = realname;
		this.email = email;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getRealname() {
		return realname;
	}
}
