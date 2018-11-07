package enums;

public enum StateEnum {
	
	SUCCESS(1,"执行成功"),INNER_ERROR(-1001,"内部错误"),NULL_PARAMS(-1002,"参数为空"),INVALID_PARAMS(-1003,"非法参数"), EXECUTE_FAIL(-1004,"执行失败");
	
	private int state;
	private String stateInfo;
	
	private StateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getStateInfo() {
		return stateInfo;
	}
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	
}
