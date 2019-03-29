package com.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by LMZ on 2018/4/11.
 */
class frame1_background  {
    public static void setBg(JFrame frame,String path) {
        ((JPanel) frame.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon(path);
        JLabel background = new JLabel(img);
        frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
}

public class register extends JFrame {
    public void view(){
        setTitle("register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocation(700,250);
        setResizable(false);
        setVisible(true);
        Button1();
        frame_background.setBg(this,"Images/2.jpg");
    }

    private JButton
    b1 = new JButton("确定"),
    b2 = new JButton("取消");

    public int id;
    public String name,pwd;
    private int flag;
    private JLabel error = new JLabel();

    private void error(int flag){
        if(flag==1)
            error.setText("*用户名、id、密码均不能为空");
        else if(flag==2)
            error.setText("*id已存在");
        error.setBounds(300,330,300,50);
        add(error);
    }

    public void Button1() {
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
                String f1_name = f1.getText();
                String f2_id = f2.getText();
                String f3_pwd = f3.getText();
                if(f1_name.equals("")||f2_id.equals("")||f3_pwd.equals("")){
                    error(1);
                    repaint();
                    return;
                }
                name = f1_name;
                id = Integer.parseInt(f2_id);
                pwd = f3_pwd;
                boolean flag = new verify().verify_register(id);
                if(flag){
                    error(2);
                    repaint();
                }
                else{
                    new CreateTable(id,name,pwd);
                    setVisible(false);
                    new login().kuang();
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
            t1 = new JLabel("用户名:"),
            t2 = new JLabel("账号:"),
            t3 = new JLabel("密码:");

    public void Text(){
        t1.setBounds(155, 180, 100, 40);
        t2.setBounds(170, 230, 100, 40);
        t3.setBounds(170,280,100,40);
        t1.setFont(new Font("宋体", Font.BOLD, 17));
        t2.setFont(new Font("宋体", Font.BOLD, 17));
        t3.setFont(new Font("宋体", Font.BOLD, 17));
        t1.setForeground(Color.white);
        t2.setForeground(Color.white);
        t3.setForeground(Color.white);
        add(t1);
        add(t2);
        add(t3);
        setLayout(null);
    }

    private JTextField
            f1 = new JTextField(40),
            f2 = new JTextField(40),
            f3 = new JPasswordField(40);

    public void Filed(){
        //setLayout(new FlowLayout());
        f1.setBounds(220, 180, 300, 40);
        f2.setBounds(220, 230, 300, 40);
        f3.setBounds(220, 280, 300, 40);
        f1.setOpaque(false);
        f2.setOpaque(false);
        f3.setOpaque(false);
        f1.setForeground(Color.white);
        f2.setForeground(Color.white);
        f3.setForeground(Color.white);
        f1.setFont(new Font("宋体", Font.BOLD, 17));
        f2.setFont(new Font("宋体", Font.BOLD, 17));
        f3.setFont(new Font("宋体", Font.BOLD, 17));

        add(f1);
        add(f2);
        add(f3);
        setLayout(null);
    }

}
