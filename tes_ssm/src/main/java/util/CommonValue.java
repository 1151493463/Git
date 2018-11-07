package util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class CommonValue {
	
	@Value("${pageSize}")
	private int pageSize;
	
	public static List<String> CONTENTTYPE;
	
	static {
		CONTENTTYPE = new ArrayList<String>();
		CONTENTTYPE.add("image/bmp");
		CONTENTTYPE.add("image/gif");
		CONTENTTYPE.add("image/x-icon");
		CONTENTTYPE.add("image/jpeg");
		CONTENTTYPE.add("image/jpg");
		CONTENTTYPE.add("image/png");
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
