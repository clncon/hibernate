package cn.itcast.hibernate.j_hbm_oneToOne;






import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	 private static SessionFactory sessionfactory=new Configuration()
			 .configure()//
			 .addClass(IdCard.class)//
			 .addClass(Person.class)//
			 .buildSessionFactory();
	 //保存
	 @Test
    public void TestSave()throws Exception{
		 Session session = sessionfactory.openSession();
		 session.beginTransaction();
		 //----------------------------------------------------------------
            //创建对象
		         Person person = new Person();
		          person.setName("张三");
		         IdCard idcard = new IdCard();
		           idcard.setNumber("340154566XXXXXX");
		     
		           
		        // 关联起来
		   		// 使用基于外键的一对一时：Person是无外键方，不可以维护关联关系
		   		// 使用基于外键的一对一时：IdCard是有外键方，可以维护关联关系
		   		// 使用基于主键的一对一时：也是只有有外键方可以保存关联关系
		    //关联关系
		         //person.setIdcard(idcard);
		         idcard.setPerson(person);
		          
    	    //保存对象
		         session.save(idcard);
		         session.save(person);
		      
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
		        //根据person获得并显示idCard
			     Person person = session.get(Person.class, 1);
			     System.out.println("姓名:"+person.getName());
			     System.out.println("身份证:"+person.getIdcard());
			//--------------------------------------------------------------------------
		    session.getTransaction().commit();
		    session.close();
	 }
	 
	   //移除关联关系
	    @Test
	    public void TestremoveRelation()throws Exception{
	    	Session session = sessionfactory.openSession();
	    	session.beginTransaction();
	    	//-------------------------------------------------------------------------------------------------------
	    	  
	    	// // 获取一个Person，并移除与IdCard的关联
			// 使用基于外键的一对一时：Person是无外键方，不可以维护关联关系
	    	    /* Person person = session.get(Person.class,1);
	    	     person.setIdcard(null);*/
	    	   //取得一个Idcard,并去除他的关联关系
	    	// 获取一个IdCard，并移除与的Person关联
			// 使用基于外键的一对一时：IdCard是有外键方，可以维护关联关系
	    	     IdCard idcard = session.get(IdCard.class, 1);
	    	     idcard.setPerson(null);
	    	
	    	//--------------------------------------------------------------------------------------------------------
	    	
	    	session.getTransaction().commit();
	    	session.close();
	    }
	  //删除
	  @Test
	  public void TestDelete()throws Exception{
		  Session session = sessionfactory.openSession();
		  session.beginTransaction();
		  //--------------------------------------------------------------------------------------------------------------------------
		     //删除Person

			// 因为Person是无外键言，不可以维护关联关系，所以删除Person时，如果有关联的IdCard，就会抛异常
		       Person person = session.get(Person.class, 3);
		         session.delete(person);
		     //删除IdCard
		      /*  IdCard idcard = session.get(IdCard.class, 2);
		          session.delete(idcard);*/
		  
		  //---------------------------------------------------------------------------------------------------------------------------
		  session.getTransaction().commit();
		  session.close();
		  
	  }
	 
}
