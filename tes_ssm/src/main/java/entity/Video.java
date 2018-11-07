package entity;


import java.io.Serializable;
import java.sql.Timestamp;

public class Video implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private Integer special;
	private String forSale;
	private Integer clickCount;
	private String introduction;
	private String picture;
	private String ccPicture;
	private String fileName;
	private String urlCc;
	private Integer state;
	private Timestamp onTime;
	private Integer difficulty;
	private String md5;
	private String tag;
	private String CategoryId;
	private String fileSize;
	private String metaUrl;
	private String chunkUrl;
	private String ccvid;
	private String serviceType;
	private String isPass;
	
	private User user;
	private Course course;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public String getIsPass() {
		return isPass;
	}
	public void setIsPass(String isPass) {
		this.isPass = isPass;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSpecial() {
		return special;
	}
	public void setSpecial(Integer special) {
		this.special = special;
	}
	public String getForSale() {
		return forSale;
	}
	public void setForSale(String forSale) {
		this.forSale = forSale;
	}
	public Integer getClickCount() {
		return clickCount;
	}
	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getCcPicture() {
		return ccPicture;
	}
	public void setCcPicture(String ccPicture) {
		this.ccPicture = ccPicture;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUrlCc() {
		return urlCc;
	}
	public void setUrlCc(String urlCc) {
		this.urlCc = urlCc;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Timestamp getOnTime() {
		return onTime;
	}
	public void setOnTime(Timestamp onTime) {
		this.onTime = onTime;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCategoryId() {
		return CategoryId;
	}
	public void setCategoryId(String categoryId) {
		CategoryId = categoryId;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getMetaUrl() {
		return metaUrl;
	}
	public void setMetaUrl(String metaUrl) {
		this.metaUrl = metaUrl;
	}
	public String getChunkUrl() {
		return chunkUrl;
	}
	public void setChunkUrl(String chunkUrl) {
		this.chunkUrl = chunkUrl;
	}
	public String getCcvid() {
		return ccvid;
	}
	public void setCcvid(String ccvid) {
		this.ccvid = ccvid;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	

}
