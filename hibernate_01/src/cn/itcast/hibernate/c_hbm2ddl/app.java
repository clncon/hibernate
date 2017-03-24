package cn.itcast.hibernate.c_hbm2ddl;

import java.util.EnumSet;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.schema.TargetType;
import org.junit.Test;

import cn.itcast.hibernate.a_helloworld.User;

public class app {
	 @Test
   public void testHbmtoDDl(){
		 //使用工具类ShemaExport自动创建或删除表
	 //Configuration config = new Configuration().configure();
	   //创建服务注册对象
	   ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
	      
	   //创建sessionFactory
	   //SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
	        //创建session对象
	   // Session session = sessionFactory.getCurrentSession();
	   //创建Metadata对象
	   //MetadataImplementor metadataImplementor = (MetadataImplementor) new MetadataSources(serviceRegistry).buildMetadata();  	  
	   Metadata metadata =new MetadataSources(serviceRegistry).buildMetadata();
	   
	   //创建SchemaExport对象
	   SchemaExport export = new SchemaExport();  
	   export.create(EnumSet.of(TargetType.DATABASE),metadata);
   }
}
