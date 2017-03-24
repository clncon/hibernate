package cn.itcatst.test.hibernate_test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class hibernate_test {
	@Test
	 public void test(){
		 SessionFactory sessionfactory = new Configuration()//
				 .configure()//
				 .buildSessionFactory();
				 
	 }

}
