package cn.itcast.hibernate.b_dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cn.itcast.hibernate.a_helloworld.User;

public class UserDao {
	
	  private static SessionFactory sessionFactory=null;
	  static{
		  //读取配置文件，生成对应的sessionFactory
		  Configuration cofg = new Configuration()//
				  .
				  configure()//
				  .addResource("cn/itcast/hibernate/a_helloworld/User.hbm.xml");
	
		  sessionFactory=cofg.buildSessionFactory();
		  
	  }
	   /**
	    * 保存用户信息
	    * @param user
	    */
      public void save(User user){
    	   //生成session对象
    	  Session session = sessionFactory.openSession();
    	  Transaction tx =null;
    	  try {
			//取得事务对象,并开始事务
			 tx = session.beginTransaction();
			  //执行保存操作
			   session.save(user);
			  //提交事务
			   tx.commit();
		} catch (RuntimeException e) {
			 tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			
		}
      };
       /**
        * 更新用户信息
        * @param user
        */
      public void update(User user){
    	  Session session = sessionFactory.openSession();
    	  Transaction tx =null;
    	  try {
			//取得事务对象,并开始事务
			 tx = session.beginTransaction();
			  //执行更新操作
			   session.update(user);
			  //提交事务
			   tx.commit();
		} catch (RuntimeException e) {
			 tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			
		}
    	  
      };
       /**
        * 删除用户信息
        * @param id
        */
      public void delete(int id){
    	  Session session = sessionFactory.openSession();
    	  Transaction tx =null;
    	  try {
			//取得事务对象,并开始事务
			 tx = session.beginTransaction();
			 //取得需要删除的对象
			  User user = getUserById(id);
			  //执行保存操作
			   session.delete(user);
			  //提交事务
			   tx.commit();
		} catch (RuntimeException e) {
			 tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			
		}
    	  
    	  
      };
      /**
       * 
       * 根据指定的id查询用户信息
       * @param id
       * @return
       */
      public User getUserById(int id){
    	  Session session = sessionFactory.openSession();
    	  Transaction tx =null;
    	  User user=null;
    	  try {
			//取得事务对象,并开始事务
			 tx = session.beginTransaction();
			  //根据id取得对象
			  user= (User) session.get(User.class, id);
			  //提交事务
			   tx.commit();
		} catch (RuntimeException e) {
			 tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			
		}
    	  return user ;
      };
      /**
       * 查找数据中所有的信息
       * @return
       */
      public List<User> findAll(){
    	  Session session = sessionFactory.openSession();
    	  Transaction tx =null;
    	  List<User> list=null;
    	  try {
			//取得事务对象,并开始事务
			 tx = session.beginTransaction();
			  //取得数据库中的所有的数据
			  list =  session.createQuery("FROM User").getResultList();
			  //提交事务
			   tx.commit();
		} catch (RuntimeException e) {
			 tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			
		}
    	  return list;
      };
      /**
       * 分页查询
       * @param firstResult
       * @param maxResults
       * @return
       */
      public QueryResult<User> findAll(int firstResult,int maxResults){
    	   
    	  Session session = sessionFactory.openSession();
    	  Transaction tx =null;
    	  List<User> list = null;
    	  int count=0;
    	  try {
			//取得事务对象,并开始事务
			 tx = session.beginTransaction();
			  //取得数据库中的记录的个数
			  Long temp = (Long) session.createQuery("SELECT COUNT(*)FROM User").uniqueResult();
			       count = temp.intValue();
			  //查询一段数据
			     Query query = session.createQuery("From User");
			     query.setFirstResult(firstResult);
			     query.setMaxResults(maxResults);
			    list = query.list();
			     
			  //提交事务
			   tx.commit();
		} catch (RuntimeException e) {
			 tx.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			
		}
    	  return new QueryResult<User>(list, count);
      };
}   
