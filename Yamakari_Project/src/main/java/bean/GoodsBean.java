package bean;

public class GoodsBean {
	private int id;
	private String name;
	private int price;
	private String category;
	private String fileName;
	private int userID;
	
	public void setID(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getPrice() {
		return this.price;
	}
	public String getCategory() {
		return this.category;
	}
	public String getFileName() {
		return this.fileName;
	}
	public int getUserID() {
		return this.userID;
	}
}
