/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package member;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import orderDish.OrderDish;

/**
 *
 * @author User
 */
public class MemberDB {
    
    public MemberDB(){}
    
    public void saveDB(MemberList memberList){
        try {
            FileOutputStream fileOut = new FileOutputStream("D:\\timer\\src\\member\\memberList.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memberList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved ");
        } catch (IOException i) {
            i.printStackTrace();
        }

    }
    
    public MemberList loadDB() {
        MemberList loadMemberList = new MemberList();
        try {
            FileInputStream fileIn = new FileInputStream("D:\\timer\\src\\member\\memberList.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            loadMemberList = ( MemberList ) in.readObject();
            in.close();
            fileIn.close();
            return loadMemberList;
        } catch (IOException i) {
            saveDB(loadMemberList);
            loadMemberList = loadDB();
        } catch (ClassNotFoundException c) {
            System.out.println("Member class not found");
            c.printStackTrace();
        }
        return loadMemberList;
    }
    
}
