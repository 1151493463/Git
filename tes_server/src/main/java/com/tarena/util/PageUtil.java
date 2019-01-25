package com.tarena.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pageUtil")
public class PageUtil {
	@Value("#{props.pageSize}")//#{props[pageSize]}
	private int pageSize;
	@Value("#{props.showNum_a}")
	private int showNum_a;
	public int getPageSize() {
		return pageSize;
	}
	public int getShowNum_a() {
		return showNum_a;
	}
	/**
	 * 获取页面中的bootstrap分页条中的超链接的个数
	 * @param currentPage  当前页
	 * @param pageSize   每页条数
	 * @param totalCount  总记录数
	 * @param totalPage   总页数
	 * @return  集合
	 */
	public List<Integer> getFenYe_a_Num(
			int currentPage,
			int pageSize,
			int totalCount,
			int totalPage){
		List<Integer> aNum=new ArrayList<Integer>();
		//建议奇数,容易定义中间
		
		if(totalCount/pageSize>=showNum_a){
			//总页数大于等于showNum_a    5
			if(currentPage<showNum_a/2+1){
				//中间位置的左边
				for(int i=1;i<=showNum_a;i++){
					aNum.add(new Integer(i));
				}
			}else if((totalPage-currentPage)<(showNum_a/2+1)){
				//判断如果是到最后,最后的那几个数字是不能移动的
				for(int i=1;i<=showNum_a;i++){
					aNum.add(new Integer(totalPage-showNum_a+i));
				}
			}else{
				//不是头,也不是尾
				for(int i=(currentPage-(showNum_a-(showNum_a/2+1)));i<=(currentPage+(showNum_a-(showNum_a/2+1)));i++){
					aNum.add(new Integer(i));
				}
			}			
		}else{
			//总页数不够showNum_a  5
			for(int i=1;i<=totalPage;i++){
				aNum.add(new Integer(i));
			}
		}
		return aNum;
		
	}
}
