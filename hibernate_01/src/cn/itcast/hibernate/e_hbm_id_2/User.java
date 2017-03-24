package cn.itcast.hibernate.e_hbm_id_2;

import java.io.Serializable;

public class User implements Serializable{
   private String firstName;
   private String lastName;
   private boolean gender;
   public User(){}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public boolean isGender() {
	return gender;
}
public void setGender(boolean gender) {
	this.gender = gender;
}
   
}
