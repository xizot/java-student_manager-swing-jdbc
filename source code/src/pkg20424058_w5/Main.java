/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424058_w5;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DBManager db = new DBManager();
        ConnectDatabase jf1 = new ConnectDatabase(db);
        jf1.show();

    }
    
}
