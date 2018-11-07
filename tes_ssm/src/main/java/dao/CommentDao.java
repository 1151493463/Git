package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import entity.Comment;

public interface CommentDao {
	/**
	 * 获取评论总记录数
	 * @param keyWords
	 * @return
	 */
	int getTotalCount(@Param("keyWords")Map<String, Object> keyWords);
	/**
	 * 根据条件分页查询评论
	 * @param offset
	 * @param pageSize
	 * @param keyWords
	 * @return
	 */
	List<Comment> queryCommentList(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("keyWords")Map<String,Object> keyWords);
	/**
	 * 审核评论
	 * @param idArray
	 * @param advice
	 * @return
	 */
	int verifyComment(@Param("idArray")String[] idArray, @Param("advice")String advice);

}
