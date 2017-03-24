package cn.itcast.hibernate.i_hbm_manytToMany;

import java.util.HashSet;
import java.util.Set;

public class Teacher {
           private Integer id;
           private String name;
           private Set<Student> students = new HashSet<Student>();
           
           public Teacher(){}

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

		public Set<Student> getStudents() {
			return students;
		}

		public void setStudents(Set<Student> students) {
			this.students = students;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "[Teacher:id="+id+",name="+name+"]";
		}
           
           
}
