package cn.itcast.hibernate.g_hbm_collection;





import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

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
    		 //Set
    	/*  Set<String> addressSet = new HashSet<String>();
    	  addressSet.add("1.合肥");
    	  addressSet.add("2.南京");
    	  user.setAddressSet(addressSet);
*/    	  user.setName("王五");
         //List
            /* user.getAddressList().add("1.杭州");
             user.getAddressList().add("2.北京");*/
         //Map
           /* user.getAddressMap().put("公司","上海");
            user.getAddressMap().put("家", "北京");*/
           //Array
             user.setAddressArray(new String[]{"安庆","合肥","深圳"});
           //bag
              user.getAddressBag().add("天津");
              user.getAddressBag().add("香港");
              user.getAddressBag().add("海南");
    	//保存
    	session.save(user);
    	System.out.println("新对象的ID:"+user.getId());
    	
		
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
		
			   User user = session.get(User.class, 6);
			    System.out.println("姓名:"+user.getName());
			    System.out.println("地址:"+user.getAddressSet());
			    System.out.println("地址："+user.getAddressList());
			    System.out.println("地址:"+user.getAddressMap());
			    System.out.println("地址:"+Arrays.toString(user.getAddressArray()));
			    System.out.println("地址:"+user.getAddressBag());
			  
			//--------------------------------------------------------------------------
		    session.getTransaction().commit();
		    session.close();
	 }
	  
	  public static void main(String[] args) {
		// Set<String> set = new HashSet<String>();//无序
		 //Set<String> set = new TreeSet<String>();//自动排序，排序方式玩家可以指定
		   Set<String> set = new LinkedHashSet<String>();//和插入顺序一致
		   set.add("aa");
		   set.add("bb");
		   set.add("cc");
		   set.add("dd");
		   System.out.println(set);
		 
	}
}
