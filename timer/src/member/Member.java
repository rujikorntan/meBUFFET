/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package member;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Member implements Serializable{
    
    String name,tel,id,enroll,birth;
    
    public Member(){}
    
    public Member(String name,String id,String tel,String enroll,String birth){
        this.name = name;
        this.id = id;
        this.tel = tel;
        this.enroll = enroll;
        this.birth = birth;
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

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public void setBirth(String birth) {
        this.birth = birth;
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

    public String getEnroll() {
        return enroll;
    }

    public String getBirth() {
        return birth;
    }
}
