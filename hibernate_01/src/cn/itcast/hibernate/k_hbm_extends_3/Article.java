package cn.itcast.hibernate.k_hbm_extends_3;

public class Article {
     private int id;
     private String title;
     private String content;
     
     public Article(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
     
     
}