package cn.itcast.hibernate.f_hbm_component;





import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	 private static SessionFactory sessionfactory=new Configuration()
			 .configure()//
			 .addClass(User.class)//
			 
			 .buildSessionFactory();
	 //保存
	 @Test
    public void TestSave()throws Exception{
		 Session session = sessionfactory.openSession();
		 session.beginTransaction();
		 //----------------------------------------------------------------

    	
    		User user = new User();
    		//user.setId(100);
       // user.setId(UUID.randomUUID().toString());
    		//user.setId(1);
    	  user.setName("王五");
    	  user.setUserAddress(new UserAddress("合肥开发区","8800","0551-xxxxxx"));
    	//保存
    	session.save(user);
    	
		
    	//--------------------------------------------------------------------------
    	session.getTransaction().commit();
    	session.close();
    	
    	
    }
	//获取
	  @Test
	 public void TestGet()throws Exception{
		  Session session = sessionfactory.openSession();
			 session.beginTransaction();
			 //----------------------------------------------------------------
			/*
			   User user = session.get(User.class, 1);
			    System.out.println("姓名:"+user.getName());*/
			  
			//--------------------------------------------------------------------------
		    session.getTransaction().commit();
		    session.close();
	 }
}
