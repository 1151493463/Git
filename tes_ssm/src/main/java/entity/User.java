package entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class User implements Serializable{

	private static final long serialVersionUID = 7381375681884695300L;
	
	private String id;
	private String loginName;
	private String loginType;
	private String nickName;
	private String password;
	private Integer age;
	private Integer type;
	private String head;
	private Integer score;
	private String isLock;
	private String pwdState;
	private Timestamp regDate;
	private String sex;
	private String introduction;
	private List<Role> roles;
	
	private String activeCode;
	
	
	public String getActiveCode() {
		return activeCode;
	}
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getIsLock() {
		return isLock;
	}
	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}
	public String getPwdState() {
		return pwdState;
	}
	public void setPwdState(String pwdState) {
		this.pwdState = pwdState;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", nickName=" + nickName + ", score=" + score
				+ ", isLock=" + isLock + ", regDate=" + regDate + ", roles=" + roles + ", getRoles()=" + getRoles()
				+ ", getId()=" + getId() + ", getLoginName()=" + getLoginName() + ", getLoginType()=" + getLoginType()
				+ ", getNickName()=" + getNickName() + ", getPassword()=" + getPassword() + ", getType()=" + getType()
				+ ", getHead()=" + getHead() + ", getScore()=" + getScore() + ", getIsLock()=" + getIsLock()
				+ ", getPwdState()=" + getPwdState() + ", getRegDate()=" + getRegDate() + ", getSex()=" + getSex()
				+ ", getIntroduction()=" + getIntroduction() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	

}
