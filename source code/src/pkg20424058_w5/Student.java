/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424058_w5;

import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Student {
    String id,name,address,note,image;
    double point;
    Student(String id,String name, double point, String address,String note,String image){
        this.id = id;
        this.name = name;
        this.address = address;
        this.note = note;
        this.image = image;
        this.point = point;
    }
    public void print(){
          System.out.format("%s, %s, %s, %s, %s, %s\n", id, name, point, address, note, image);
    }
}
