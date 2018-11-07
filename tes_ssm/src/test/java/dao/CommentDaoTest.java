package dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import entity.Comment;

public class CommentDaoTest extends BaseTest{
	@Autowired
	private CommentDao commentDao;
	
	@Test
	public void test() {
		Map<String, Object> map = new HashMap<>();
		String[] courseArray=new String[] {"java","php"};
		//map.put("courseArray", courseArray);
		//map.put("isVerify", "未审核");
		map.put("keyWord", "java");
		int totalCount = commentDao.getTotalCount(map);
		System.out.println(totalCount);
	}
	
	@Test
	public void test1() {
		Map<String, Object> map = new HashMap<>();
		String[] courseArray=new String[] {"java","php"};
		//map.put("courseArray", courseArray);
		//map.put("isVerify", "未审核");
		//map.put("keyWord", "java");
		map.put("order", "DESC");
		List<Comment> queryCommentList = commentDao.queryCommentList(0, 12, map);
		System.out.println(queryCommentList);
	}
	
	@Test
	public void test2() {
		String[] ids = new String[] {"eb0fb881-655f-4527-968f-e270c8ea3557","e17e6b63-85a3-44df-99cd-8ebe5b9c61b9"};
		int verifyComment = commentDao.verifyComment(ids, "未审核");
		System.out.println(verifyComment);
	}
		

}
