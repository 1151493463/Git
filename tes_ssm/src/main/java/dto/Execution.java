package dto;

import java.io.Serializable;

import enums.StateEnum;

public class Execution <T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int state;
	private String stateInfo;
	private T data;
	
	/**
	 * 执行成功
	 * @param stateEnum
	 * @param data
	 */
	public Execution(StateEnum stateEnum,T data) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.data = data;
	}
	/**
	 * 执行失败或成功 已定义stateInfo
	 * @param stateEnum
	 */
	public Execution(StateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	/**
	 * 执行失败 未定义stateInfo
	 * @param stateEnum
	 * @param errMsg
	 */
	public Execution(StateEnum stateEnum,String errMsg) {
		this.state = stateEnum.getState();
		this.stateInfo = errMsg;
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
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
