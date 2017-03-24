package cn.itcast.hibernate.d_hbm_property;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
public class App {
	 private static SessionFactory sessionfactory=new Configuration()
			 .configure()//
			 .addResource("cn/itcast/hibernate/d_hbm_property/User.hbm.xml")//
			 
			 .buildSessionFactory();
	 //保存
	 @Test
    public void TestSave()throws Exception{
		 Session session = sessionfactory.openSession();
		 session.beginTransaction();
		 //----------------------------------------------------------------
		 FileInputStream fin = new FileInputStream(new File("E:/meinv.jpg"));
		 byte[] photo = new byte[fin.available()];
		fin.read(photo);
		
    	User user = new User();
    	user.setName("张三");
    	user.setGender(true);
    	user.setBirthday(new Date());
    	user.setDesc("关于张三的简介");
    	user.setPhoto(photo);
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
			    User user = session.get(User.class, 1);
			    System.out.println("姓名:"+user.getName());
			    System.out.println("性别:"+user.getGender());
			    System.out.println("生日:"+user.getBirthday());
			    System.out.println("描述:"+user.getDesc());
			  
			//--------------------------------------------------------------------------
		    session.getTransaction().commit();
		    session.close();
	 }
}
