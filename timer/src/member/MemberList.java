/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package member;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MemberList implements Serializable{
    
    ArrayList<Member> memberList = new ArrayList<>();
    
    public MemberList(){}
    
    public void append(Member employee){
        memberList.add(employee);
    }
    
    public int size(){
        return memberList.size();
    }
    
    public void remove(String name){
        for(int i=0;i<size();i++){
            if(memberList.get(i).getName().equals(name)){
                memberList.remove(i);
            }
        }     
    }

    public Member getMember(int index){
        return memberList.get(index);
    }
    
    public ArrayList<Member> getList(){
        return memberList;
    }
}
