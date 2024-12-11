package bean;

import java.io.Serializable;

public class dbBean implements Serializable {
	private int id;
	private String name;
	private int age;
	
	public void setID(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}

}
