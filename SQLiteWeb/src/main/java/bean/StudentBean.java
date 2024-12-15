package bean;

import java.io.Serializable;

public class StudentBean implements Serializable{
  private int no;
  private String name;
  private String room;
  private int score;
  
  public void setNo(int no){
    this.no = no;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setRoom(String room) {
	  this.room = room;
  }
  public void setScore(int score){
    this.score = score;
  }
  public int getNo(){
    return no;
  }
  public String getName(){
    return name;
  }
  public String getRoom() {
	  return room;
  }
  public int getScore(){
    return score;
  }
}

