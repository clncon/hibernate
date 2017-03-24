package cn.itcast.hibernate.n_query_hql;






import java.util.Arrays;
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
	    
	   //插入测试数据
	      @Test
	     public void testSave(){
	    	  Session session = sessionfactory.openSession();
				 session.beginTransaction();
				 //----------------------------------------------------------------
				       for(int i=1;i<=10;i++){
				    	   Department department = new Department();
				    	    department.setName("开发部_"+i);
				    	   session.save(department);   
				       }
				       for(int i=1;i<=20;i++){
				    	   
				    	   Employee employee = new Employee();
				    	    employee.setName("员工_"+i);
				    	    session.save(employee);
				       }
		    	//--------------------------------------------------------------------------
		    	session.getTransaction().commit();
		    	session.close();
	    	  
	      }
	      
	      
	      @Test
	      public void testHQL(){
	    	  
	    	  Session session = sessionfactory.openSession();
				 session.beginTransaction();
				 //----------------------------------------------------------------
				  String hql = null;
				 //1，简单的查询(FROM与AS)
				   //  hql = "FROM Employee";
				   //hql = "FROM Employee AS e";
				    //hql = "FROM Employee e";
				 //2，带上过滤条件的（可以使用别名）：Where
				   // hql = "FROM Employee e WHERE e.id=50";
				    //  hql = "FROM Employee e WHERE e.id>5 AND e.id<15";
				 //3，带上排序条件的：Order By
				     //hql = "FROM Employee e ORDER BY e.id DESC,e.name ASC";
				      // hql = "FROM Employee e WHERE e.id>5 AND e.id<17 ORDER BY e.id DESC";
				// 4，指定select子句（不可以使用select *）
				 	//可以使用new语法，指定把查询出的部分属性封装到对象中
				    //如果只查询一个属性的话,则查询到的结果集就是这个属性的类型
				    //如果查询到的是多个属性，则查询的结果集中是Object数组
				    
				     hql = "SELECT e FROM Employee e";
				     //hql = "SELECT e.id,e.name FROM Employee e";
				     //hql  = "SELECT new cn.itcast.hibernate.n_query_hql.Employee(e.id,e.name) FROM Employee e";
				    
				 //5，执行查询，获得结果（list、uniqueResult、分页 ）
				     //>>a.查询唯一的一个结果，如果没有结果返回null,如果结果不唯一就会抛出异常
				     // Query query = session.createQuery(hql);
				       //Object obj =   query.uniqueResult();
				       //System.out.println(obj);*/
				     //>>b.查询一个集合
				       //List list = query.list();
				  
				    //>>c.分页查询
				       /*query.setFirstResult(10);
				        query.setMaxResults(10);
				        List list = query.list();*/
				 //6，方法链
				     List list = session//
				    		 .
				    		 createQuery(hql).//
				    		 setFirstResult(10).//
				    		 setMaxResults(10).list();
				 
				 
				 //执行查询
				/* Query query = session.createQuery(hql);
				    session.flush();
				   List list = query.list();*/
				   //显示结果
				   for(Object obj:list){
					  
					   if(obj.getClass().isArray()){
						   System.out.println(Arrays.toString((Object[])obj));
					   }else{
						   
						   System.out.println(obj);
					   }
				   }
		    	//--------------------------------------------------------------------------
		    	session.getTransaction().commit();
		    	session.close();
	      }
	      @Test
	      public void TestHQL_2(){
	    	  Session session = sessionfactory.openSession();
				 session.beginTransaction();
				 //----------------------------------------------------------------
				   String hql=null;
				   
				   //1，聚集函数
				     //hql = "SELECT COUNT(*) FROM Employee e";
				     //hql = "SELECT MAX(e.id) FROM Employee e";
				     //  hql ="SELECT COUNT(e.name) FROM Employee e";
				       //hql ="SELECT COUNT(DISTINCT e.name) FROM Employee e";
				  // 2，分组
				     // hql = "SELECT e.name FROM Employee e WHERE e.id<13 GROUP BY e.name";
				      //hql = "SELECT e.name,count(e.name) FROM Employee e WHERE e.id<13 GROUP BY e.name";
				  // hql = "SELECT e.name,count(e.name) FROM Employee e WHERE e.id<13 GROUP BY e.name HAVING COUNT(e.name)>1";
				   // hql = "SELECT e.name,count(e.name) FROM Employee e WHERE e.id<13 GROUP BY e.name HAVING COUNT(e.name)>1 ORDER BY COUNT(e.name) DESC";
				    
				   //3，连接查询 / HQL是面向对象的查询
				        //查询指定部门中员工的信息
				        // hql = "SELECT e.name FROM Employee e WHERE e.department.name='caiwu'";
				          //hql = "SELECT e.name FROM Employee e JOIN e.department d WHERE d.name='caiwu'";
				            // HQL是面向对象的查询	
				          //hql = "SELECT e FROM Employee e WHERE e.department IS NULL";
				  // 4，查询时使用参数
				  // 	方式一：使用'?'占位
				     /* hql = "FROM Employee e WHERE e.id>? AND e.id<?";
				         Query query = session.createQuery(hql);
				         query.setParameter(0, 5);// 第1参数的位置是0
				         query.setParameter(1, 15);
				         List list = query.list();*/
				   
				      //>> 方法链
				         /*List list = session.createQuery("FROM Employee e WHERE e.id>? AND e.id<?")//
				        		 .setParameter(0, 10)//
				        		 .setParameter(1,15).//
				        		 list();*/
				  // 	方式二：使用变量名
				        /* hql = "FROM Employee e WHERE e.id>:idMin AND e.id<:idMax";
				         Query query = session.createQuery(hql);
				         query.setParameter("idMin", 5);// 第1参数的位置是0
				         query.setParameter("idMax", 15);
				         List list = query.list();*/
				         
				         //>>当参数是集合或者数组时，只能通过使用变量名的方式，并通过setParameter()方法来设置参数
				         /*  List list = session.createQuery("FROM Employee e WHERE e.id IN(:ids)")//
				        		   .setParameterList("ids", new Integer[]{1,2,3})//
				        		   .list();*/
				  // 5，使用命名查询
				        /* Query query = session.getNamedQuery("QueryByIdMax");
				         query.setParameter("idMax",10);
				         List list = query.list();*/
				  
				// 6，update与delete，不会通知Session缓存
					  // 	在update或delete后，需要refresh(obj)一下以获取最新的状态
				     //不会通知Session缓存
			          /* Employee employee = session.get(Employee.class, 5);
				       System.out.println(employee.getName());*/
				   
				    //>>UPDATE
				     /* int result = session//
				      .createQuery("UPDATE Employee e Set e.name=? WHERE e.id>=?")//
				      .setParameter(0, "新名称").//
				      setParameter(1, 10).//
				      executeUpdate();*/
				 /*  System.out.println("影响的行数:"+result);*/
				   
				    //>>DELETE
				      int result = session//
				    		  .createQuery("DELETE FROM Employee e WHERE e.id>?")//
				    		  .setParameter(0, 16)//
				    		  .executeUpdate();
				      System.out.println("影响的行数:"+result);
				    		  
				 //执行查询
				 /*Query query = session.createQuery(hql);
				    session.flush();
				   List list = query.list();*/
				   //显示结果
				 /*  for(Object obj:list){
					  
					   if(obj.getClass().isArray()){
						  System.out.println(Arrays.toString((Object[])obj));
					   }else{
						   
						   System.out.println(obj);
					   }
					   
					
				      
				   }*/
		    	//--------------------------------------------------------------------------
		    	session.getTransaction().commit();
		    	session.close();
	      }
}
