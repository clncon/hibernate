package cn.itcast.hibernate.m_session_cache1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;



public class app {
	
		 private static  SessionFactory sessionfactory=new Configuration()
				 .configure()//
				 .addClass(User.class)//
				 .buildSessionFactory();
		 
		 @Test
	    public void TestRefresh()throws Exception{
			 Session session = sessionfactory.openSession();
			 session.beginTransaction();
			 //----------------------------------------------------------------
			     //获取对象
			     User user =  session.get(User.class, 2);
			      System.out.println(user.getName());
			     //刷新对象的状态（从数据库中再次获取对象）
			      session.refresh(user);
			      System.out.println(user.getName());
			
	    	//--------------------------------------------------------------------------
	    	session.getTransaction().commit();
	    	session.close();
	    	
	    	
	    }
		 //大量存入数据且没有内存溢出的错误
		   
		 @Test
	    public void TestBatchSave()throws Exception{
			 Session session = sessionfactory.openSession();
			 session.beginTransaction();
			 //----------------------------------------------------------------
			    for(int i=0;i<50;i++){
			    	
			    	 User user = new User();
			    	  user.setName("test"+i);
			    	  session.save(user);//保存
			    	   //适时地刷新缓存
			    	 if(i%10==0){
			    		 System.out.println("刷出");
			    		  session.flush();
			    		  session.clear();
			    		 
			    	 }
			    }
			
	    	//--------------------------------------------------------------------------
	    	session.getTransaction().commit();
	    	session.close();
	    	
	    	
	    }
}