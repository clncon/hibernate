package cn.itcast.hibernate.m_session_cache2;






import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.Test;

public class App {
	 private static SessionFactory sessionfactory=new Configuration()
			 .configure()//
			 .addClass(Department.class)//
			 .addClass(Employee.class)//
			 .buildSessionFactory();
	    
	   //测试一级缓存(session缓存)
	      @Test
	     public void testSessionCache(){
	    	  Session session = sessionfactory.openSession();
				 session.beginTransaction();
				 //----------------------------------------------------------------
				 //====================第一个session
				      Employee employee = session.get(Employee.class, 1);
				       System.out.println(employee.getName());
		    	//--------------------------------------------------------------------------
		    	session.getTransaction().commit();
		    	session.close();
		    	
		    	//====================================第二个session
		    	 Session session2 = sessionfactory.openSession();
				 session2.beginTransaction();
				 //----------------------------------------------------------------
				 
				      Employee employee2 = session2.get(Employee.class, 1);
				       System.out.println(employee2.getName());
		    	//--------------------------------------------------------------------------
		    	session2.getTransaction().commit();
		    	session2.close();
	    	  
	      }
	     
	      //测试二级缓存
	      @Test
		     public void testSecondCache2(){
	    	     //========================第一个Session
		    	  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
					     Department department = session.get(Department.class, 1);
					       System.out.println(department.getName());
			    	//--------------------------------------------------------------------------
			    	session.getTransaction().commit();
			    	session.close();
			    	
			    	//========================第二个Session
			    	  Session session2 = sessionfactory.openSession();
						 session2.beginTransaction();
						 //----------------------------------------------------------------
						     Department department2 = session2.get(Department.class, 1);
						       System.out.println(department2.getName());
				    	//--------------------------------------------------------------------------
				    	session2.getTransaction().commit();
				    	session2.close();
		    	  
		      }
	      
	      //测试二级缓存
	      //当使用Query.list()时 默认是不会使用二级缓存
	      @Test
		     public void testQueryCache(){
	    	     //========================第一个Session
		    	  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
					     List<Employee> list = session.createQuery("FROM Employee e WHERE e.id<8").list();
					        System.out.println(list);
				    	//--------------------------------------------------------------------------
				    	session.getTransaction().commit();
				    	session.close();
				    	
				    	 //========================第二个Session
				    	  Session session2 = sessionfactory.openSession();
							 session2.beginTransaction();
							 //----------------------------------------------------------------
							     List<Employee> list2 = session2.createQuery("FROM Employee e WHERE e.id<8").list();
							        System.out.println(list2);
						    	//--------------------------------------------------------------------------
						    	session2.getTransaction().commit();
						    	session2.close();
		    	  
		      }
	      //测试查询缓存
	      //在使用HQL方式查询时，如果使用Iterator()方法，就会使用缓存
	      //因为这个方法是先查询所有符合条件的id集合，再按一个一个的按id查询数据，就能使用缓存了
	      //但这个方法使用N+1次查询，提升性能有限,通常不会使用此方法
	      @Test
		     public void testQueryCache2(){
	    	     //========================第一个Session
		    	  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
					 Iterator<Employee> iterator = session.createQuery("FROM Employee e WHERE e.id<8").iterate();
				        while(iterator.hasNext()){
				        	Employee e = iterator.next();
				        	System.out.println(e);
				        	
				        }
			    	//--------------------------------------------------------------------------
			    	session.getTransaction().commit();
			    	session.close();
			    	
			    	  System.out.println("\n============================\n");
			    	
			    	 //========================第二个Session
			    	  Session session2 = sessionfactory.openSession();
						 session2.beginTransaction();
						 //----------------------------------------------------------------
						 Iterator<Employee> iterator2 = session2.createQuery("FROM Employee e WHERE e.id<8").iterate();
					        while(iterator2.hasNext()){
					        	Employee e = iterator2.next();
					        	System.out.println(e);
					        	
					        }
						    	//--------------------------------------------------------------------------
						    	session2.getTransaction().commit();
						    	session2.close();
		    	  
		      }
	      
	      
	      @Test
		     public void testQueryCache3(){
	    	     //========================第一个Session
		    	  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
					 List list = session.createQuery("FROM Employee e WHERE e.id<8")//
							 .setCacheable(true)// 是否使用查询缓存，需要在hibernate.cfg.xml中开启查询缓存才行
							 .list();
				        System.out.println(list);
			    	//--------------------------------------------------------------------------
			    	session.getTransaction().commit();
			    	session.close();
			    	
			    	 //========================第二个Session
			    	  Session session2 = sessionfactory.openSession();
						 session2.beginTransaction();
						 //----------------------------------------------------------------
						     List list2 = session2.createQuery("FROM Employee e WHERE e.id<8")//
						    		 .setCacheable(true)//是否使用查询缓存，需要在hibernate.cfg.xml中开启查询缓存才行
						    		 .list();
						        System.out.println(list2);
						    	//--------------------------------------------------------------------------
						    	session2.getTransaction().commit();
						    	session2.close();
		    	  
		      }
	      
	      @Test
		     public void testUpdateTimestampCache(){
	    	     //========================第一个Session
		    	  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
					    //先获取对象
					    Employee employee = session.get(Employee.class, 1);
					     System.out.println(employee.getName());
					     
					   //直接更新数据库DB
					     int result = session.createQuery("UPDATE Employee e SET e.name=? WHERE e.id=?")//
					    		          .setParameter(0, "新名称")//
					    		          .setParameter(1, 1)//
					    		          .executeUpdate();//执行
					     
					     //再显示这个员工的姓名
					       System.out.println(employee.getName());
			    	//--------------------------------------------------------------------------
			    	session.getTransaction().commit();
			    	session.close();
			    	
			    	 //========================第二个Session
			    	  Session session2 = sessionfactory.openSession();
						 session2.beginTransaction();
						 //----------------------------------------------------------------
						       Employee employee2 = session2.get(Employee.class, 1);
						       System.out.println(employee2.getName());
						    	//--------------------------------------------------------------------------
						    	session2.getTransaction().commit();
						    	session2.close();
		    	  
		      }
}
