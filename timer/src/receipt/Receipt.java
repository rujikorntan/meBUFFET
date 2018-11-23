/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package receipt;

import dish.Dish;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import info.Info;
import info.InfoDB;
import member.Member;
import member.MemberDB;
import member.MemberList;
import orderDish.OrderDish;
import orderDish.OrderDishDB;
import orderDish.TempDish;

/**
 *
 * @author User
 */
public class Receipt implements Serializable{
    
    OrderDishDB orderDB = new OrderDishDB();
    Info info = new Info();
    InfoDB infoDB = new InfoDB();
    
    public Receipt(){}

    public void saveDb(String index){
//        orderList.append(orderDish);
        boolean isMember = false;
        MemberList memberList = new MemberList();
        MemberDB memberDB = new MemberDB();
        memberList = memberDB.loadDB();
        OrderDish orderDish = new OrderDish();
        orderDish = orderDB.loadDB(index);
        info = infoDB.loadDB();
        String path = "D:\\timer\\src\\receipt\\receipt.txt";
        File file = new File(path);
        FileWriter writer;
        
        int sum=0;
        for(Dish d : orderDish.getOrderDishList()){
            sum += (d.getAmount())*(Integer.parseInt(d.getPrice()));
        }
        float vat = (float) ((float) sum*((Float.parseFloat(info.getInfo_vat()))/100));
        float discount = (float) ((float)sum*((Float.parseFloat(info.getInfo_discount()))/100));
        try{
            writer = new FileWriter(file, false);  //True = Append to file, false = Overwrite
            writer.write("<--- Thank You --->\r\n");
            for(int i=0;i<orderDish.size();i++){
                writer.write(orderDish.getDish(i).getName()+
                "    "+orderDish.getDish(i).getPrice()+"   "+orderDish.getDish(i).getAmount()+"\r\n"+"\r\n");
            }
            for(Member m : memberList.getList()){
                if(m.getName().equals(orderDish.getMember())){
                    isMember = true;
                    break;
                }
            }
            if(isMember){
                System.out.println("member total"+((float)sum+vat-discount));
                writer.write("Total : "+((float)sum+vat-discount)+"\r\n");
            }else{
                System.out.println("not member total"+((float)vat));
                writer.write("Total : "+((float)sum+vat)+"\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
