package vo;

import java.io.Serializable;

public class UserCartogram implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String roleName;
	private Integer userNum;
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Integer getUserNum() {
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	
	
}
