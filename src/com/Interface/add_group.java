package com.Interface;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by LMZ on 2018/4/11.
 */

class frame3_background  {
    public static void setBg(JFrame frame,String path){
        ((JPanel)frame.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon(path);
        JLabel background = new JLabel(img);
        frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
}

public class add_group extends JFrame implements ActionListener,MouseListener {

    JPanel jp;//卡片布局
    CardLayout cl;

    //the first card
    JPanel jp1;
    JButton jp1_jb1,jp1_jb2;

    //the second card
    JPanel jp2;
    JScrollPane jsp;
    JPanel jp_jsp;//用来放jsp
    JButton jp2_jb1,jp2_jb2,jp2_jb3;

    //search_id(int)
    private int si;
    private String search_id;
    private int admin_id;

    public void view(int admin_id) {
        this.admin_id = admin_id;
        //background
        cl = new CardLayout();
        jp = new JPanel();
        jp.setOpaque(false);
        //set background size
        jp.setBounds(0, 0, 600, 300);

        //first card
        firstcard();
        //second card
        //secondcard();

        jp.setLayout(cl);
        //jp.add(jp1);
        jp.add(jp1,"1");
        //jp.add(jp2,"2");
        this.add(jp);
        this.getLayeredPane().setLayout(null);
        this.setLayout(null);
        this.setSize(600, 335);
        this.setLocation(700, 250);
        this.setVisible(true);
        this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("search");
        frame3_background.setBg(this,"Images/15.jpg");
    }

    //other variables

    private JTextField
            jp1_f1 = new JTextField(40),
            jp2_f1 = new JTextField(40);

    public void firstcard(){
        jp1 = new JPanel();

        jp1_f1.setBounds(120,128,305,30);

        jp1.add(jp1_f1);
        jp1.setLayout(null);
        jp1.setOpaque(false);

        jp1_jb1 = new JButton(new ImageIcon("Images/search.jpg"));
        jp1_jb1.addActionListener(this);
        jp1_jb1.setBounds(435,128,30,30);
        jp1.add(jp1_jb1);

        jp1_jb2 = new JButton("创建群聊");
        jp1_jb2.addActionListener(this);
        jp1_jb2.setBounds(485,128,100,30);
        jp1_jb2.setContentAreaFilled(false);
        jp1.add(jp1_jb2);
    }

    public void secondcard(){
        jp2 = new JPanel();
        jp2_f1.setBounds(170,50,230,30);
        jp2.add(jp2_f1);
        jp2.setLayout(null);
        jp2.setOpaque(false);

        jp2_jb1 = new JButton(new ImageIcon("Images/search.jpg"));
        jp2_jb1.addActionListener(this);
        jp2_jb1.setBounds(420,50,30,30);
        jp2.add(jp2_jb1);

        jp2_jb3 = new JButton("创建群聊");
        jp2_jb3.addActionListener(this);
        jp2_jb3.setBounds(475,50,100,30);
        jp2_jb3.setContentAreaFilled(false);
        jp2.add(jp2_jb3);
        if(new verify().verify_group(si)){
            jp_jsp = new JPanel(new GridLayout(1,1));
            jsp = new JScrollPane(jp_jsp);
            jsp.setBounds(150, 100, 300, 60);
            JLabel jbl = new JLabel(Integer.toString(si),new ImageIcon("Images/"+new friend().get_group_head(si)),JLabel.LEFT);
            jbl.addMouseListener(this);
            jp_jsp.add(jbl);
            jp2.add(jsp);
            jp2_jb2 = new JButton("add");
            jp2_jb2.setBounds(380,160,70,30);
            jp2_jb2.addActionListener(this);
            jp2.add(jp2_jb2);
        }
        else{
            JLabel error = new JLabel("*The searching group id does not exist.");
            error.setBounds(200,180,300,50);
            jp2.add(error);
        }
    }

    public void error(){
        JLabel error = new JLabel("*Please input the searching id.");
        error.setBounds(200,200,300,50);
        jp1.add(error);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jp1_jb2||e.getSource()==jp2_jb3){
            new creategroup(admin_id);
            return;
        }
        if(e.getSource()==jp2_jb2){

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
                pstmt.setInt(2,si);
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
            return;
        }
        if(e.getSource()==jp1_jb1){
            this.search_id = jp1_f1.getText();
            if(search_id.equals("")){
                error();
                repaint();
                return;
            }
        }
        if(e.getSource()==jp2_jb1){
            this.search_id = jp2_f1.getText();
            if(search_id.equals("")){
                error();
                repaint();
                return;
            }
        }
        this.si = Integer.parseInt(search_id);
        secondcard();
        jp.add(jp2,"2");
        cl.show(jp, "2");
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        JLabel jl =(JLabel)e.getSource();
        jl.setForeground(Color.red);


    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        JLabel jl =(JLabel)e.getSource();
        jl.setForeground(Color.black);
    }
}
