package com.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by lmz on 2018/5/8 0008.
 */
public class creategroup extends JFrame{
    private JButton
            b1 = new JButton("确定"),
            b2 = new JButton("取消");
    public int id;
    private int flag;
    private JLabel error = new JLabel();

    private void error(int flag){
        if(flag==1)
            error.setText("*id已存在");
        else if(flag==2)
            error.setText("*id 不可为空");
        error.setBounds(300,250,300,50);
        add(error);
    }

    public creategroup(int admin_id){
        setTitle("create group");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocation(700,250);
        setResizable(false);
        setVisible(true);
        frame_background.setBg(this,"Images/2.jpg");

        b1.setBounds(250, 350, 90, 30);
        b2.setBounds(400, 350, 90, 30);
        b1.setFont(new Font("黑体", Font.BOLD, 15));
        b2.setFont(new Font("黑体", Font.BOLD, 15));
        b1.setForeground(Color.white);
        b2.setForeground(Color.white);
        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String group_id = f.getText();
                if(group_id.equals("")){
                    error(2);
                    repaint();
                    return;
                }
                id = Integer.parseInt(group_id);
                boolean flag = new verify().verify_group(id);
                if(flag){
                    error(1);
                    repaint();
                }
                else{
                    String DB_URL = "jdbc:mysql://LMZ/login";
                    String USER = "root";
                    String PASS = "xflmz36";
                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    String name = null;
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        String sql ="insert into user_group(id, group_id)value(?,?);";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1,admin_id);
                        pstmt.setInt(2,id);
                        pstmt.executeUpdate();
                        sql = "insert into group_head(id, head)value(?,'2.gif');";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1,id);
                        pstmt.executeUpdate();
                    }catch(SQLException se){
                        se.printStackTrace();
                    }catch(Exception s){
                        s.printStackTrace();
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

                    setVisible(false);
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(b1);
        add(b2);
        setLayout(null);
        Text();
        Filed();
    }

    private JLabel
            t1 = new JLabel("群号:");

    public void Text(){
        t1.setBounds(155, 180, 100, 40);
        t1.setFont(new Font("宋体", Font.BOLD, 17));
        t1.setForeground(Color.white);
        add(t1);
        setLayout(null);
    }

    private JTextField
            f = new JTextField(40);

    public void Filed(){
        //setLayout(new FlowLayout());
        f.setBounds(220, 180, 300, 40);
        f.setOpaque(false);
        f.setForeground(Color.white);
        f.setFont(new Font("宋体", Font.BOLD, 17));

        add(f);
        setLayout(null);
    }
}
