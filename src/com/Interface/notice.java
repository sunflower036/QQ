package com.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class notice extends JFrame{
    JPanel friendPanel2;
    JScrollPane friendPanel_jScrollPane;
    ArrayList<Integer> request = new ArrayList<>();
    int i = 0;
    public notice(int admin_id){
        setBounds(400, 200, 500, 300);
        setResizable(false);
        setLayout(null);
        setTitle("AddFriendNotice");

        friendPanel2=new JPanel(new GridLayout(10, 1));
        JLabel[] friendsList;
        JButton[] jb1;
        JButton[] jb2;
        request = get_request(admin_id);

        friendsList = new JLabel[request.size()];
        jb1 = new JButton[request.size()];
        jb2 = new JButton[request.size()];

        for (i = 0; i < request.size(); i++) {
            int friend_id = request.get(i);
            String head = new friend().get_head(friend_id);
            friendsList[i]=new JLabel(new friend().get_id(friend_id)+"请求添加你为好友",new ImageIcon("Images/"+head),JLabel.LEFT);
            jb1[i] = new JButton("同意");
            jb2[i] = new JButton("拒绝");

            final JPopupMenu jp =new JPopupMenu();
            friendsList[i].add(jp);
            jp.add(jb1[i]);
            jp.add(jb2[i]);
            jb1[i].setContentAreaFilled(false);
            jb2[i].setContentAreaFilled(false);
            friendsList[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (e.getButton() == MouseEvent.BUTTON1){
                        //GG弹出菜单
                        jp.show(e.getComponent(),e.getX(),e.getY());
                    }
                }
            });

            friendPanel2.add(friendsList[i]);

            jb1[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame message = new JFrame("消息");
                    message.setBounds(480,250,300,100);
                    message.setLayout(new FlowLayout());
                    message.setResizable(false);
                    JLabel label = new JLabel("您已成功添加"+new friend().get_id(friend_id)+"为好友");
                    JButton button = new JButton("确定");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            add_success(admin_id,friend_id);
                            message.setVisible(false);
                        }
                    });
                    message.add(label);
                    message.add(button);
                    message.setVisible(true);

                }
            });
            jb2[i].addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JFrame message = new JFrame("消息");
                    message.setBounds(480,250,300,100);
                    message.setLayout(new FlowLayout());
                    message.setResizable(false);
                    JLabel label = new JLabel("您已拒绝"+friend_id+"的好友请求");
                    JButton button = new JButton("确定");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            add_fail(admin_id,friend_id);
                            message.setVisible(false);
                        }
                    });
                    message.add(label);
                    message.add(button);
                    message.setVisible(true);

                }
            });

        }

        friendPanel_jScrollPane=new JScrollPane(friendPanel2);
        friendPanel_jScrollPane.setBounds(0,0,495,272);
        friendPanel_jScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(friendPanel_jScrollPane);
        setVisible(true);
    }

    private ArrayList<Integer> get_request(int admin_id){
        String DB_URL = "jdbc:mysql://LMZ/login";
        String USER = "root";
        String PASS = "xflmz36";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ArrayList<Integer> strArray = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="select admin_id from notice where friend_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
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

    private void add_success(int admin_id, int friend_id){
        String DB_URL = "jdbc:mysql://LMZ/login";
        String USER = "root";
        String PASS = "xflmz36";
        Connection conn = null;
        PreparedStatement pstmt = null;
        String name = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql ="insert into friend(id, friend)value(?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,admin_id);
            pstmt.setInt(2,friend_id);
            pstmt.executeUpdate();
            sql = "insert into friend(id, friend)value(?,?);";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,friend_id);
            pstmt.setInt(2,admin_id);
            pstmt.executeUpdate();
            sql = "delete from notice where admin_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,friend_id);
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
    }

    private void add_fail(int admin_id, int friend_id){
        String DB_URL = "jdbc:mysql://LMZ/login";
        String USER = "root";
        String PASS = "xflmz36";
        Connection conn = null;
        PreparedStatement pstmt = null;
        String name = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "delete from notice where admin_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,friend_id);
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
    }


}
