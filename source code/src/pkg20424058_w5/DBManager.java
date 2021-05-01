/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg20424058_w5;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class DBManager {
    Connection conn ;
    public DBManager(){
       
        //Oracle

//	Connection connection = null;
//    try {
//        // Load the JDBC driver
//        String driverName = "oracle.jdbc.driver.OracleDriver";
//        Class.forName(driverName);
//    
//        // Create a connection to the database
//        String serverName = "127.0.0.1";
//        String portNumber = "1521";
//        String sid = "mydatabase";
//        String url = null;
//		url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber + ":" + sid;
//        String username = "username";
//        String password = "password";
//        
//        connection = DriverManager.getConnection(url, username, password);
//        
//	...
//    } catch (ClassNotFoundException e) {
//        // Could not find the database driver
//    } catch (SQLException e) {
//        // Could not connect to the database
//    }
//
//
////MySQL
//Connection connection = null;
//    try {
//        // Load the JDBC driver
//        String driverName = "org.gjt.mm.mysql.Driver"; // MySQL MM JDBC driver
//        Class.forName(driverName);
//    
//        // Create a connection to the database
//        String serverName = "localhost";
//        String mydatabase = "mydatabase";
//        String url = "jdbc:mysql://" + serverName +  "/" + mydatabase; // a JDBC url
//        String username = "username";
//        String password = "password";
//        connection = DriverManager.getConnection(url, username, password);
//	//...
//    } catch (ClassNotFoundException e) {
//        // Could not find the database driver
//    } catch (SQLException e) {
//        // Could not connect to the database
//    }
//
//
////access -driver, type 1
//
//try
//{
//		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		con = DriverManager.getConnection("jdbc:odbc:sqldsn");
//}
//catch(Exception e)
//{
//}
//
//
//
//
////JDBC Driver for MS SQL Server 2005
//try
//           {
//               	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            	con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=TT;user=username;password=password");
//				if(con!=null) 
//               		System.out.println("Connection Successful!");
//           }
//           catch(Exception e)
//           {
//               JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error Message", JOptionPane.ERROR_MESSAGE);
//               e.printStackTrace();
//               System.out.println("Error Trace in getConnection() : " + e.getMessage());
//           }


   }
    
    public Connection createConnection(int dbServer, String IP, String userName, String password) throws SQLException, ClassNotFoundException{
        conn = null;
        if(dbServer == 0){ //MS SQL 
            // Load the JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String DB_URL = "jdbc:sqlserver://" + IP;
            conn = DriverManager.getConnection(DB_URL, userName, password);
         
        }
        else if(dbServer == 1){ // MY SQL
            
            // Load the JDBC driver
//            Class.forName("org.gjt.mm.mysql.Driver");

            // Create a connection to the database
            String DB_URL = "jdbc:mysql://" + IP;
            conn = DriverManager.getConnection(DB_URL, userName, password);

        }
        else if(dbServer == 2){ // ORACLE
            // Load the JDBC driver
//            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Create a connection to the database
            String DB_URL = "jdbc:oracle:thin:@" + IP;
            this.conn = DriverManager.getConnection(DB_URL, userName, password);
        }
        else{
            throw new Error("Please choose database server");
        }
        return conn;
    }
    public boolean checkConnection(){
        try {
            if(conn != null && !conn.isClosed()){
                return true;
            }
        } catch (SQLException ignored) {}
        return false;
    }
    
    public ArrayList<Student> getStudent(int idAscending, int pointAscending) throws SQLException{
        ArrayList<Student> students = new ArrayList<>();
        String idOrder =  "id ASC";
        String pointOrder = "point ASC";
        if(idAscending == 2) idOrder = "id DESC";
        if(pointAscending == 2) pointOrder = "point DESC";
        
        String sql ="SELECT * FROM STUDENT ORDER BY " + idOrder + ", " + pointOrder;
        if(idAscending == 0){
            sql ="SELECT * FROM STUDENT ORDER BY " + pointOrder;
        }
        if(pointAscending == 0){
            sql ="SELECT * FROM STUDENT ORDER BY " + idOrder;
        }
        
        Statement st = conn.createStatement();
        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(sql);

        // iterate through the java resultset
        while (rs.next())
        {
            String id = rs.getString("ID");
            String name = rs.getString("name");
            double point = rs.getDouble("point");
            String address = rs.getString("address");
            String note= rs.getString("note");
            String image = rs.getString("image");
            Student stu = new Student(id, name, point, address, note, image);
            students.add(stu);
          // print the results
        }
        st.close();
        return students;
    }
    
    public boolean addNew(String ID, String name, double point, String address, String note, String image) throws SQLException{
        String sql = "INSERT INTO STUDENT(id,name,point,address,note,image) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, ID);
        ps.setString(2, name);
        ps.setDouble(3, point);
        ps.setString(4, address);
        ps.setString(5, note);
        ps.setString(6, image);

        return ps.executeUpdate() > 0;
    }
    public boolean update(String ID, String name, double point, String address, String note, String image) throws SQLException{
        String sql = "UPDATE STUDENT SET Name = ? , Point = ?, Address = ?, Note = ?, Image = ? WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
       
        ps.setString(1, name);
        ps.setDouble(2, point);
        ps.setString(3, address);
        ps.setString(4, note);
        ps.setString(5, image);
        ps.setString(6, ID);
        
        return ps.executeUpdate() > 0;
    }
    
    public boolean delete(String ID) throws SQLException{
        String sql = "DELETE FROM STUDENT WHERE ID = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
       
        ps.setString(1, ID);
        return ps.executeUpdate() > 0;
    }
}
