package cn.itcast.hibernate.k_hbm_extends_2;






import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	 private static SessionFactory sessionfactory=new Configuration()
			 .configure()//
			 .addClass(Article.class)//
			 .buildSessionFactory();
	 //保存
	 @Test
    public void TestSave()throws Exception{
		 Session session = sessionfactory.openSession();
		 session.beginTransaction();
		 //----------------------------------------------------------------
		 
		  //创建一个文章对象
		 Article atricle = new Article();
           atricle.setTitle("aaaa");
           atricle.setContent("bbbbbbb");
           //创建一个回复对象
           Reply reply = new Reply();
             reply.setTitle("cc");
             reply.setContent("ccccccc");
             reply.setReply(1);
             //创建一个标题对象
            Topic topic = new Topic();
             topic.setTitle("dd");
             topic.setContent("dddddddd");
             topic.setType(1);
             
             //保存对象
               session.save(atricle);
               session.save(reply);
               session.save(topic);
		      
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
		       //获得一篇文章
			   Article atricle = session.get(Article.class,1);
			   Reply reply = session.get(Reply.class, 2);
			   Topic topic = session.get(Topic.class,3);
			   System.out.println(atricle);
			   System.out.println(reply);
			   System.out.println(topic);
			   
			   System.out.println("-------------------------------------------------");
			   
			   
			   Article atricle1 = session.get(Article.class, 2);
			   Article atricle2 = session.get(Article.class, 3);
			   
			   System.out.println(atricle1);
			   System.out.println(atricle2);
			   
			//--------------------------------------------------------------------------
		    session.getTransaction().commit();
		    session.close();
	 }
	 
	  
	  //删除
	  @Test
	  public void TestDelete()throws Exception{
		  Session session = sessionfactory.openSession();
		  session.beginTransaction();
		  //--------------------------------------------------------------------------------------------------------------------------
		     Article atricle = session.get(Article.class,1);
		      session.delete(atricle);
		  //---------------------------------------------------------------------------------------------------------------------------
		  session.getTransaction().commit();
		  session.close();
		  
	  }
	 
}
