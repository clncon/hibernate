package cn.itcast.hibernate.m_session_cache2;

import java.util.HashSet;
import java.util.Set;

public class Department {
      private Integer id;
      private String name;
      private Set<Employee> Employees = new HashSet<Employee>();
      
      public Department(){}

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

	public Set<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(Set<Employee> employees) {
		Employees = employees;
	}

	@Override
	public String toString() {
		return "[Deparment:id="+id+",name="+name+"]";
	}
      
      
   
}
