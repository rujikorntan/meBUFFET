/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class EmployeeList implements java.io.Serializable{
    
    private ArrayList<Employee> employeeList = new ArrayList<>();
    private ArrayList<Employee> delEmpList = new ArrayList<>();
    
    public EmployeeList(){}
    
    public void append(Employee employee){
        employeeList.add(employee);
    }
    
    public int size(){
        return employeeList.size();
    }
    
    public void remove(String name){
        for(int i=0;i<size();i++){
            if(employeeList.get(i).getName().equals(name)){
                employeeList.remove(i);
            }
        }     
    }

    public void setDelEmpList(ArrayList<Employee> delList){
        delEmpList = delList;
    }

    public ArrayList<Employee> getDelList(){
        return delEmpList;
    }

    public Employee getEmployee(int index){
        return employeeList.get(index);
    }
}
