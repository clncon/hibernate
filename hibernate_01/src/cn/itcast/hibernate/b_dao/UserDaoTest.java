package cn.itcast.hibernate.b_dao;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import cn.itcast.hibernate.a_helloworld.User;

public class UserDaoTest {
     UserDao userdao =  new UserDao();
	@Test
	public void testSave_1() {
		  //设置保存到数据库中的对象
	    User user = new User();
	     user.setName("李四");
	     userdao.save(user);
	}

	@Test
	public void testSave_25() {
		  //设置保存到数据库中的对象
		for(int i=1;i<=25;i++){
	    User user = new User();
	     user.setName("李四"+i);
	     userdao.save(user);
		}
	}
	@Test
	public void testUpdate() {
		 User user = userdao.getUserById(1);
	     user.setName("张三");
	     userdao.update(user);
	}

	@Test
	public void testDelete() {
		userdao.delete(1);
	}

	@Test
	public void testGetUserById() {
		User user = userdao.getUserById(1);
		System.out.println(user);
	}

	@Test
	public void testFindAll() {
		//查询
		List<User> list = userdao.findAll();
		//循环
		for(User user:list){
			System.out.println(user);
		}
	}

	@Test
	public void testFindAllIntInt() {
		//分页查询
		/*//第一页
		QueryResult<User> qr = userdao.findAll(0, 10);*/
	
/*		//第二页
		QueryResult<User> qr = userdao.findAll(10, 10);
*/		//第三页
		QueryResult<User> qr = userdao.findAll(20, 10);
		//显示记录的数量
		 System.out.println("总记录数量:"+qr.getCount());
		//显示用户信息
		for(User user:qr.getList()){
			System.out.println(user);
		}
	}

}
