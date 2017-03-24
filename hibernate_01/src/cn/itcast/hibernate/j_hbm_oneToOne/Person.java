package cn.itcast.hibernate.j_hbm_oneToOne;

public class Person {
     private Integer id;
     private String name;
     private IdCard idcard;
     
     public Person(){}

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

	public IdCard getIdcard() {
		return idcard;
	}

	public void setIdcard(IdCard idcard) {
		this.idcard = idcard;
	}

	@Override
	public String toString() {
		
		return "[Person: id="+id+",name="+name+"]";
	}
     
   
}
