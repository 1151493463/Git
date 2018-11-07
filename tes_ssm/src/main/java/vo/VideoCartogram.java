package vo;

import java.io.Serializable;

public class VideoCartogram implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String courseName;
	private Integer videoNum;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getVideoNum() {
		return videoNum;
	}
	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}
	
	

}
