package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentDTO implements Serializable{
  private ArrayList<StudentBean> list;

  public StudentDTO(){
    list = new ArrayList<StudentBean>();
  }
  public void add(StudentBean sb){
    list.add(sb);
  }
  public StudentBean get(int i){
    return list.get(i);
  }
  public int size(){
    return list.size();
  }
}

