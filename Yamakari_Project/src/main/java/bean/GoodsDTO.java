package bean;

import java.util.ArrayList;

public class GoodsDTO {
	ArrayList<GoodsBean> list;
	
	public GoodsDTO() {
		list = new ArrayList<GoodsBean>();
	}
	
	public void add(GoodsBean gb) {
		list.add(gb);
	}
	
	public ArrayList<GoodsBean> getList() {
		return list;
	}
	public int size() {
		return list.size();
	}
	public GoodsBean get(int i) {
		return list.get(i);
	}
}
