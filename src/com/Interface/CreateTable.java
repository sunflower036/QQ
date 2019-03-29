package com.Interface;
import java.sql.*;
import java.util.*;

/**
 * Created by lmz on 2018/4/7 0007.
 */
public class CreateTable {
    static final String DB_URL = "jdbc:mysql://LMZ/login";
    //My localhost is LMZ, which can be changed.
    static final String USER = "root";
    static final String PASS = "xflmz36";
    public CreateTable(int id, String name, String pwd) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<String> strArray = new ArrayList<String>();
        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            String sql ="insert into admin(id,name,pwd) value(?,?,?);  ";
            pstmt = conn.prepareStatement(sql);
            //test admin_id
            pstmt.setInt(1,id);
            pstmt.setString(2,name);
            pstmt.setString(3,pwd);
            pstmt.executeUpdate();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(pstmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
