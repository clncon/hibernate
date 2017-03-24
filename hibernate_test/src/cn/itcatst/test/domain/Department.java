package cn.itcatst.test.domain;

import java.util.HashSet;
import java.util.Set;

public class Department {
     private Long id;
     private String name;
     private Set<Employee> employees = new HashSet<Employee>();
     private Department parent;
     private Set<Department> childrens = new HashSet<Department>();
     
     
     public Department(){}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}


	public Department getParent() {
		return parent;
	}


	public void setParent(Department parent) {
		this.parent = parent;
	}


	public Set<Department> getChildrens() {
		return childrens;
	}


	public void setChildrens(Set<Department> childrens) {
		this.childrens = childrens;
	}
 
     
}
