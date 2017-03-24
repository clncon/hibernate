package cn.itcast.hibernate.o_session_manage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class app {
	 private static SessionFactory sessionfactory = new Configuration()//
			 .configure("cn/itcast/hibernate/o_session_manage/myhibernate.cfg.xml")//
			 .addClass(User.class)//
			 .buildSessionFactory();
	   //想要使用getCurrentSession()方法
	   //需要在hibernate主配置文件中配置
	 //current_session_context_class项。
	  //getCurrentSession()方法
	 //去指定的上下文中查找（如Thread）查找绑定的Session对象
	 //如果有就会返回
	 //如果没有就会创建一个，并绑定好，返回
	 //openSession()方法只是创建一个session，并不会绑定和查询
	  @Test
	  public void testSession(){
		 Session session1 =  sessionfactory.getCurrentSession();
		 Session session2 =  sessionfactory.getCurrentSession();
		 System.out.println(session1!=null);//true
		 System.out.println(session1==session2);//true
		 
	  }
	  
	  //当使用getCurrentSession()时，Hibernate会在提交和回滚后自动关闭Session
	   @Test
	   public void testSessionClose(){
		   
		   Session session = sessionfactory.getCurrentSession();
		    session.beginTransaction();
		    System.out.println("当完成xxx事务");
		    session.getTransaction().commit();
//		    session.close(); //当使用getCurrentSession()时，会自动的关闭
	   }
	   
	   
	   @Test
		  public void testSession2(){
			 Session session1 =  sessionfactory.openSession();
			 Session session2 =  sessionfactory.openSession();
			 System.out.println(session1!=null);//true
			 System.out.println(session1==session2);//false
			 
		  }
	   
	   
	   @Test
		  public void testSession3(){
			 Session session1 =  sessionfactory.getCurrentSession();
			 Session session2 =  sessionfactory.openSession();
			 System.out.println(session1!=null);//true
			 System.out.println(session1==session2);//false
			 
		  }
}
