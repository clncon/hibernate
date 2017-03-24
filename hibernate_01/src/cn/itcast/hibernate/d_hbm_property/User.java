package cn.itcast.hibernate.d_hbm_property;

import java.util.Date;
/**
 * 创建一个user对象
 * @author Administrator
 *
 */
public class User {
       private Integer id;
       private String name;
       private boolean gender;
       private Date birthday;
       private byte[] photo;
       private String desc; //描述
       public User(){}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
       
}
