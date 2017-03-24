package cn.itcatst.test.domain;

import java.util.Set;

public class UserAccount {
      private Long id;
      private String loginName;
      private String password;
      private Employee employee;
      private Set<Privilege> privileges;
      
      
      public UserAccount(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public Set<Privilege> getPrivileges() {
		return privileges;
	}


	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
      
      
      
      
}
