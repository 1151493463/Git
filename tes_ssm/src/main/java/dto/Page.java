package dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;



public class Page <T> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int currentPage;
	private int pageSize;
	private int totalCount;
	@SuppressWarnings("unused")
	private int totalPage;
	@SuppressWarnings("unused")
	private int offset;
	private List<T> data;
	private Map<String,Object> keyWords;
	
	public Page() {
	}
	
	public Page(int currentPage, Map<String, Object> keyWords, int pageSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.keyWords = keyWords;
	}
	
	
	public int getOffset() {
		return (currentPage-1)*pageSize;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		System.out.println(pageSize);
		return totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public Map<String, Object> getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(Map<String, Object> keyWords) {
		this.keyWords = keyWords;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
