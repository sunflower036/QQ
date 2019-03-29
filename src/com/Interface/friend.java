package com.Interface;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by lmz on 2018/4/11 0011.
 */
public class friend {
    static final String DB_URL = "jdbc:mysql://LMZ/login";
    static final String USER = "root";
    static final String PASS = "xflmz36";
    public ArrayList admin_friend(int admin_id){
        //int admin_id = 1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<String> strArray = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select name from admin,friend where admin.id=friend && friend.id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                strArray.add(rs.getString(1));
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
        return strArray;
    }

    public ArrayList admin_friend_head(int admin_id){
        //int admin_id = 1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<String> strArray = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select head from admin,friend where admin.id=friend && friend.id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                strArray.add(rs.getString(1));
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
        return strArray;
    }

    public int name_id(String name){
        Connection conn = null;
        PreparedStatement pstmt = null;
        int id = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select id from admin where name=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                id = rs.getInt(1);
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
        return id;
    }

    public String get_id(int admin_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String name = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select name from admin where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                name = rs.getString(1);
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
        return name;
    }

    public void set_head(String head,int id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="update admin set head=? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,head);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
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
    }

    public String get_head(int admin_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String head = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select head from admin where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                head = rs.getString(1);
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
        return head;
    }

    public void set_back(String back,int id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="update admin set back=? where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,back);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
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
    }

    public String get_back(int admin_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String back = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select back from admin where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                back = rs.getString(1);
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
        return back;
    }

    public ArrayList admin_group(int admin_id){
        //int admin_id = 1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<String> strArray = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select group_id from user_group where id=?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                strArray.add(rs.getString(1));
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
        return strArray;
    }

    public ArrayList admin_group_head(int admin_id){
        //int admin_id = 1;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<String> strArray = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select head from user_group,group_head where group_head.id=group_id && user_group.id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                strArray.add(rs.getString(1));
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
        return strArray;
    }

    public String get_group_head(int group_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        String head = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select head from group_head where id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,group_id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
                head = rs.getString(1);
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
        return head;
    }

    public ArrayList group_user(int group_id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<Integer> strArray = new ArrayList<Integer>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select id from user_group where group_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,group_id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                strArray.add(rs.getInt(1));
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
        return strArray;
    }
}
