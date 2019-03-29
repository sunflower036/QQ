package com.Interface;
import java.sql.*;

/**
 * Created by lmz on 2018/4/11 0011.
 */
public class verify {
    static final String DB_URL = "jdbc:mysql://LMZ/login";
    static final String USER = "root";
    static final String PASS = "xflmz36";
    public boolean verify_login(int id, String pwd){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select * from admin where id=? && pwd=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.setString(2,pwd);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
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
        //System.out.println("Goodbye!");
        return false;
    }
    public boolean verify_register(int id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select * from admin where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
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
        return false;
    }
    public boolean verify_group(int id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select * from group_head where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
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
        return false;
    }
}
