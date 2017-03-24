package cn.itcast.hibernate.i_hbm_manytToMany;






import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	 private static SessionFactory sessionfactory=new Configuration()
			 .configure()//
			 .addClass(Student.class)//
			 .addClass(Teacher.class)//
			 .buildSessionFactory();
	 //保存
	 @Test
    public void TestSave()throws Exception{
		 Session session = sessionfactory.openSession();
		 session.beginTransaction();
		 //----------------------------------------------------------------
            //创建对象
		        //创建老师对象
		        Teacher teacher1 = new Teacher();
		         teacher1.setName("李老师");
		        Teacher teacher2 = new Teacher();
		            teacher2.setName("王老师");
		        //创建学生对象
		          Student student1 = new Student();
		           student1.setName("张三");
		          Student student2 = new Student();
		          student2.setName("李四");
		        
		     
		    //关联关系
		        //以老师为主体关联学生对象
		          teacher1.getStudents().add(student1);
		          teacher1.getStudents().add(student2);
		          teacher2.getStudents().add(student1);
		          teacher2.getStudents().add(student1);
		        //以学生为主体关联老师对象
		           student1.getTeachers().add(teacher1);
		           student1.getTeachers().add(teacher2);
		           student2.getTeachers().add(teacher1);
		           student2.getTeachers().add(teacher2);
    	    //保存对象
		       session.save(student1);
		       session.save(student2);
		       session.save(teacher1);
		       session.save(teacher2);
		
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
		        //获取老师，显示其关联的学生信息
			    /* Teacher teacher = session.get(Teacher.class, 1);
			     System.out.println("老师姓名："+teacher.getName());
			     System.out.println("关联的学生："+teacher.getStudents());*/
			   //获取学生,显示其关联的老师信息
			      Student student = session.get(Student.class, 1);
			      System.out.println("学生姓名:"+student.getName());
			      System.out.println("关联的老师:"+student.getTeachers());
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
	    	   //移除老师的关联关系
	    	     /*Teacher teacher = session.get(Teacher.class, 1);
	    	     teacher.getStudents().clear();*/
	    	   //移除学生的关联关系
	    	     Student student = session.get(Student.class, 1);
	    	     student.getTeachers().clear();
	    	
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
		    //删除一个学生,如果有关联的老师，则
		      //如果inverse="false",表示可以维护关联关系，这时会先删除时
		      //会先删除关系，再删除自己
		//如果inverse="false",表示可以不维护关联关系，这时会先删除时
	      //会先直接删除自己，会有异常
		     /* Student student = session.get(Student.class, 1);
		      session.delete(student);*/
		      //删除一个老师
	/*
		       Teacher teacher = session.get(Teacher.class, 1);
		       session.delete(teacher);*/
		  
		  //---------------------------------------------------------------------------------------------------------------------------
		  session.getTransaction().commit();
		  session.close();
		  
	  }
	 
}
