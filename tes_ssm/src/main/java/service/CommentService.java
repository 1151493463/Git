package service;

import java.util.Map;

import dto.Execution;
import dto.Page;
import entity.Comment;

public interface CommentService {
	/**
	 * 根据条件查询评论信息
	 * @param currentPage
	 * @param keyWords
	 * @return
	 */
	Execution<Page<Comment>> getCommentByPage(int currentPage, Map<String, Object> keyWords);
	/**
	 * 审核评论
	 * @param idArray
	 * @param advice
	 * @return
	 */
	Execution<Comment> verifyComment(String[] idArray, String advice);
	/**
	 * 保存铭感词
	 * @param sensitiveWord
	 * @return
	 */
	Execution<Comment> saveSensitiveWord(String sensitiveWord);

}
