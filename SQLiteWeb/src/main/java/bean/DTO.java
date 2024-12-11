package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class DTO implements Serializable {
	private ArrayList<dbBean> list;
	
	public DTO(){
		list = new ArrayList<dbBean>();
	}
	
	public void add(dbBean db) {
		list.add(db);
	}
	
	public dbBean get(int i) {
		return list.get(i);
	}
	
	public int size() {
		return list.size();
	}
}
