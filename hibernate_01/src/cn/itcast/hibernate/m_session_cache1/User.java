package cn.itcast.hibernate.m_session_cache1;



/**
 * �û�����
 * @author Administrator
 *
 */


public  class User {
      private int id;
      private String name;
      private byte[] buff = new byte[1024 * 1000 * 5];
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
  
	
	  
      
}
