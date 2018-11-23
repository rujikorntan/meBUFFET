/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

/**
 *
 * @author User
 */
public class Employee implements java.io.Serializable{
    
    String name,tel,id,wage,firstDate,birth;
    
    public Employee(){}
    
    public Employee(String name,String birth,String id,String firstDate,String wage,String tel){
        this.name = name;
        this.birth = birth;
        this.id = id;
        this.firstDate = firstDate;
        this.wage = wage;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getId() {
        return id;
    }

    public String getWage() {
        return wage;
    }

    public String getFirstDate() {
        return firstDate;
    }

    public String getBirth() {
        return birth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    public void setFirstDate(String firstDate) {
        this.firstDate = firstDate;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
    
}