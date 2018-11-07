package service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.CommentDao;
import dto.Execution;
import dto.Page;
import entity.Comment;
import enums.StateEnum;
import service.CommentService;
import util.CommonValue;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private CommonValue commonValue;
	
	@Override
	public Execution<Page<Comment>> getCommentByPage(int currentPage, Map<String, Object> keyWords) {
		// TODO Auto-generated method stub
		Execution<Page<Comment>> ce = null;
		try {
			Page<Comment> page = new Page<Comment>(currentPage,keyWords,commonValue.getPageSize());
			int totalCount = commentDao.getTotalCount(keyWords);
			page.setTotalCount(totalCount);
			List<Comment> queryCommentList = commentDao.queryCommentList(page.getOffset(), page.getPageSize(), keyWords);
			page.setData(queryCommentList);
			ce = new Execution<>(StateEnum.SUCCESS,page);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			ce = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}
		
		return ce;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public Execution<Comment> verifyComment(String[] idArray, String advice) {
		// TODO Auto-generated method stub
		Execution<Comment> ce = null;
		try {
			int verifyComment = commentDao.verifyComment(idArray,advice);
			if(verifyComment!=idArray.length) {
				throw new RuntimeException("修改评论状态失败!");
			}else {
				ce = new Execution<>(StateEnum.SUCCESS);
			}
		}catch (Exception e) {
			// TODO: handle exception
			ce = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
		return ce;
	}
	@Override
	public Execution<Comment> saveSensitiveWord(String sensitiveWord) {
		// TODO Auto-generated method stub
		Execution<Comment> ce = null;
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(basePath+"/sensitiveWord.txt");
			fileWriter.write(sensitiveWord);
			ce = new Execution<>(StateEnum.SUCCESS);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ce = new Execution<>(StateEnum.INNER_ERROR,e.getMessage());
		}finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return ce;
	}
	
	

}
