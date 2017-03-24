package cn.itcast.hibernate.h_hbm_oneTomany;

public class Employee {
	 private Integer id;
	 private String name;
	 private Department department;
	 
	 public Employee(){}

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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Employee:id="+id+",name="+name+"]";
	}

	 

}
