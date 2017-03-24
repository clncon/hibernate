package cn.itcast.hibernate.b_dao;

import java.util.List;

import cn.itcast.hibernate.a_helloworld.User;

public class QueryResult<T>{
    private List<T> list;//所查询到的一段数据
    private int count;//总记录数
    
	public QueryResult(List<T> list, int count) {
		this.list = list;
		this.count = count;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
    
}
