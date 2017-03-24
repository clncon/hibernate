package cn.itcast.hibernate.l_session_objmanage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;



public class app {
	
		 private static  SessionFactory sessionfactory=new Configuration()
				 .configure()//
				 .addClass(User.class)//
				 .buildSessionFactory();
		 //保存
		  //把临时状态转化成持久化状态
		 //生成并生成一条insert语句
		 //只有主键由数据库生成时，save(obj)时sql语句才会马上执行，
		  //以获取主键值
		 @Test
	    public void TestSave()throws Exception{
			 Session session = sessionfactory.openSession();
			 session.beginTransaction();
			 //----------------------------------------------------------------
			      User user = new User();//临时对象
			       user.setName("张三");
			       //测试OID
			       
			       System.out.println(user.getId());//null
			       session.save(user);//持久化对象
			       System.out.println(user.getId());//1
			       
			       //测试对持久化对象的修改，
			       //修改对象后，最终会同步到数据库中
			       
			        user.setName("李四");
			        System.out.println("-----------------------------------------------------");
			
	    	//--------------------------------------------------------------------------
	    	session.getTransaction().commit();
	    	session.close();
	    	
	    	
	    }
		//更新
		   //把游离状态改成持久化状态
		   //会生成update语句
		  @Test
		 public void TestUpdate()throws Exception{
			  Session session = sessionfactory.openSession();
				 session.beginTransaction();
				 //----------------------------------------------------------------
			        //获取主键为1的对象
				    User user  = session.get(User.class, 1);
				     //user.setName("哈哈");
				    //从session中清除这个对象，那么这个对象就是游离状态
				     session.evict(user);
				     user.setName("bb");
				     
				   /*//再次获得这个对象，因为之前的已经从session中去除了，所以这次没有从缓存中取，而是直接从数据库中区
				      User user2 = session.get(User.class, 1);
				      System.out.println(user==user2);//此时两个对象并不相同
*/				      //把游离状态对象放到session中管理
				        session.update(user);
				          //让update语句可以立刻执行而非等到缓存填满
				        session.flush();
				        System.out.println("-------------------------------------------------");
				       
				   
				//--------------------------------------------------------------------------
			    session.getTransaction().commit();//在提交时，会自动的先执行flush()
			    session.close();
		 }
		 
		//更新2
		   //更新时一定要要求数据库有对应的id值，否则抛异常
		  @Test
		 public void TestUpdate2()throws Exception{
			  Session session = sessionfactory.openSession();
				 session.beginTransaction();
				 //----------------------------------------------------------------
			        //模拟一个游离的对象
				       User user = new User();
				        user.setId(100);
				        user.setName("aa");
				        
				        session.update(user);
				   
				//--------------------------------------------------------------------------
			    session.getTransaction().commit();//在提交时，会自动的先执行flush()
			    session.close();
		 }
		  
		//更新或保存
		   //把游离或临时的状态转化成持久化状态
		   //如果是临时状态就生成insert语句
		   //如果是游离化的状态生成update语句
		   //检测对象是什么状态，主要是看主键值，为null或0时表示临时状态，否则是游离状态
		  @Test
		 public void TestSaveOrUpdate()throws Exception{
			  Session session = sessionfactory.openSession();
				 session.beginTransaction();
				 //----------------------------------------------------------------
			         User user = new User();
			          user.setId(1);
			          user.setName("李四2");
			          session.saveOrUpdate(user);
				   
				//--------------------------------------------------------------------------
			    session.getTransaction().commit();//在提交时，会自动的先执行flush()
			    session.close();
		 }
		  //删除
		   //把持久化或游离状态转化成删除状态
		   //会生成一条delete语句
		   //要求数据库中一定要有对应的记录，否则会报异常的,删除不成功
		  @Test
			 public void TestDelete()throws Exception{
				  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
				         //加载一个对像，就是持久化对象
					     User user  = session.get(User.class, 1);
					     //模拟一个游离得对象
					      user.setId(100);
					      session.delete(user);
					   
					//--------------------------------------------------------------------------
				    session.getTransaction().commit();//在提交时，会自动的先执行flush()
				    session.close();
			 }
		   //获取
		   //查询出来就是持久化对象，因为在session中
		  //马上执行查询
		  //如果没有对应的记录，就返回null
		  //本方法会使用缓存，只有缓存中没有才会查询数据库
		  @Test
			 public void TestGet()throws Exception{
				  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
				        User user = session.get(User.class,2);
				        User user2 = session.get(User.class,2);
				        User user3 = session.get(User.class,2);	
				        
				        System.out.println(user);
				        System.out.println(user2);
				        System.out.println(user3);
				        System.out.println("-------------------------------------------");
					//--------------------------------------------------------------------------
				    session.getTransaction().commit();//在提交时，会自动的先执行flush()
				    session.close();
			 }
		    //load()
		    //查询指定id的数据
		    //不是马上执行查询(懒加载，延迟加载)，而是返回一个代理对象（子类方法）
		    //，所以要求此类不是final的，否则懒加载失效
		    //会在第一次调用数据信息时执行，
		    //加载时如果数据不存在就会异常
		    //org.hibernate.ObjectNotFoundException:
		    //No row with the given identifier exists:
			//[cn.itcast.l_session_objmanage.User#300]
		    //本方法会使用缓存，如果缓存中没有才会去查询数据库
		    //懒加载是否有效取决与类是不是final的，以及映射文件的lazy是否为true
		  @Test
			 public void TestLoad()throws Exception{
				  Session session = sessionfactory.openSession();
					 session.beginTransaction();
					 //----------------------------------------------------------------
				          User user = session.load(User.class, 3);
				          System.out.println("---------------------------------------");
				          //System.out.println(user);
				          System.out.println(user.getName());
				         // System.out.println(user.getId());
					//--------------------------------------------------------------------------
				    session.getTransaction().commit();//在提交时，会自动的先执行flush()
				    session.close();
			 }
}