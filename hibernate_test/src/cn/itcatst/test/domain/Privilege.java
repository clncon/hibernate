package cn.itcatst.test.domain;

import java.util.Set;

public class Privilege {
    private Long id;
    private String action;
    private Set<UserAccount> useraccounts;
    
    public Privilege(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Set<UserAccount> getUseraccounts() {
		return useraccounts;
	}

	public void setUseraccounts(Set<UserAccount> useraccounts) {
		this.useraccounts = useraccounts;
	}

	
    
}
