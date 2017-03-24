package cn.itcast.hibernate.h_hbm_oneTomany;






import java.util.Set;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class App {
	 private static SessionFactory sessionfactory=new Configuration()
			 .configure()//
			 .addClass(Department.class)//
			 .addClass(Employee.class)//
			 .buildSessionFactory();
	 //保存
	 @Test
    public void TestSave()throws Exception{
		 Session session = sessionfactory.openSession();
		 session.beginTransaction();
		 //----------------------------------------------------------------
            //创建对象
		     Department department = new Department();
		     department.setName("开发部");
		     Employee employee1 = new Employee();
		     employee1.setName("张三");
		     Employee employee2 = new Employee();
		     employee2.setName("李四");
		     
		    //关联关系
		        //以部门为主体关员工
		        Set<Employee> employees = department.getEmployees();
		        employees.add(employee1);
		        employees.add(employee2);
		       //以员工为主体关联部门
		        employee1.setDepartment(department);
		        employee2.setDepartment(department);
		        
		     
    	    //保存对象
		       session.save(department);
		     /*  session.save(employee1);
		       session.save(employee2);*/
    	
		
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
		        //获取部门，显示其关联的员工信息
			     Department department = session.get(Department.class, 1);
			     System.out.println("部门名:"+department.getName());
			     System.out.println("员工信息:"+department.getEmployees());
			   //获取员工,显示其关联的部门信息
			    Employee employee = session.get(Employee.class, 1);
			    System.out.println("员工:"+employee.getName());
			    System.out.println("部门名:"+employee.getDepartment());
			    
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
	    	   //移除部门的关联关系
	    	    /*Department department = session.get(Department.class, 1);
	    	    department.getEmployees().clear();*/
	    	   //移除员工的关联关系
	    	    Employee employee = session.get(Employee.class, 5);
	    	    employee.setDepartment(null);
	    	
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
		      //删除员工
		      /*  Employee employee = session.get(Employee.class, 3);
		        session.delete(employee);*/
		      //删除部门
		       Department department = session.get(Department.class, 1);
		         session.delete(department);
		  
		  //---------------------------------------------------------------------------------------------------------------------------
		  session.getTransaction().commit();
		  session.close();
		  
	  }
	 
}
