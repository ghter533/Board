package bitek.board.user;

public class UserInfoVO {
	private String id;
	private String pwd;
	private boolean loginOk;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public boolean isLoginOk() {
		return loginOk;
	}
	public void setLoginOk(boolean loginOk) {
		this.loginOk = loginOk;
	}


}
