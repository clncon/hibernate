package cn.itcast.hibernate.a_helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class app {
	  /*
	   * 保存对象到数据库中
	   */
	 private static SessionFactory  sessionFactory = null;
	static{
		    //读取配置文件并生成session工厂对象
		   Configuration configuration = new Configuration();
		    //加载指定的配置文件
		//  configuration.configure("hibernate.cfg.xml");
		    //加载默认的配置文件
		    configuration.configure();
		    //configuration.addResource("cn/itcast/hibernate/a_helloworld/User.hbm.xml");
		    //configuration.addClass(User.class);
		    //取得sessionFactory
		    sessionFactory = configuration.buildSessionFactory();
	 }
	  @Test
     public void testSave(){
		  
		  
		  Configuration config = new Configuration();
		  config.configure();
		  SessionFactory factory = config.buildSessionFactory();
		  Session session = factory.openSession();
		  
		  
    	    //设置保存到数据库中的对象
    	    User user = new User();
    	     user.setName("李四");
    	    //保存到数据库中
//    	     Session session =sessionFactory.openSession();
    	     Transaction tr = session.beginTransaction();
    	     session.save(user);//保存
    	     tr.commit();
    	     session.close();
    	     
     }
      /**
       * 取得数据库中的数据转换成对象输出
       */
	  @Test
     public void testGet(){
    	 //从数据库中取得对象
    	  Session session =sessionFactory.openSession();
 	     Transaction tr = session.beginTransaction();
 	     User user = (User) session.get(User.class, 1);
 	     tr.commit();
 	     session.close();
    	  //打印该对象
    	  System.out.println(user);
     }
}
