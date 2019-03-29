package com.Interface;

import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class frame_background  {
    public static void setBg(JFrame frame,String path){
        ((JPanel)frame.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon(path);
        JLabel background = new JLabel(img);
        frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
}

public class login extends JFrame {
    public void kuang() {
        setTitle("QQ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 395);
        setLocation(700,250);
        setResizable(false);
        setVisible(true);
        Button1();
        Text();
        Filed();
        frame_background.setBg(this,"Images/1.jpg");
    }

    private JButton
            b1 = new JButton("登录"),
            b2 = new JButton("注册"),
            b3 = new JButton("取消");
    private String pwd;
    private int id = -1;
    private void error(){
        JLabel error = new JLabel("Failed to login. Please check your id or password.");
        error.setBounds(100,300,300,50);
        add(error);
    }
    public void Button1() {
        b1.setBounds(140, 250, 90, 30);
        b2.setBounds(250, 250, 90, 30);
        b3.setBounds(360,250,90,30);
        b1.setFont(new Font("黑体", Font.BOLD, 15));
        b2.setFont(new Font("黑体", Font.BOLD, 15));
        b3.setFont(new Font("黑体", Font.BOLD, 15));
        b1.setContentAreaFilled(false);
        b2.setContentAreaFilled(false);
        b3.setContentAreaFilled(false);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String f1_id = f1.getText();
                if(f1_id.equals("")){
                    error();
                    repaint();
                    return;
                }
                id = Integer.parseInt(f1_id);
                pwd = f2.getText();
                boolean verify = new verify().verify_login(id, pwd);
                if (verify == false || id <= 0) {
                    error();
                    repaint();
                } else {
                    setVisible(false);
                    new FriendView(id);
                }
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new register().view();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(b1);
        add(b3);
        add(b2);
        setLayout(null);
    }


    private JLabel
            t1 = new JLabel("ID"),
            t2 = new JLabel("PASSWORD");

    public void Text(){
        t1.setBounds(105, 140, 100, 30);
        t2.setBounds(45, 190, 100, 30);
        t1.setFont(new Font("SimHei", Font.BOLD, 17));
        t2.setFont(new Font("SimHei", Font.BOLD, 17));
        add(t1);
        add(t2);
        setLayout(null);
    }

    private JTextField f1 = new JTextField(40);
    private JPasswordField f2 = new JPasswordField(40);
    private JLabel pic = new JLabel(new ImageIcon("Images/12.jpg"));//触发前的

    public void Filed(){
        //setLayout(new FlowLayout());
        f1.setBounds(130, 140, 330, 30);
        f2.setBounds(130, 190, 330, 30);

        pic.setBounds(60,150,50,50);
        add(pic);

        f1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pic.setIcon(new ImageIcon("Images/12.jpg"));
            }
            @Override
            public void mousePressed(MouseEvent e) {            }

            @Override
            public void mouseReleased(MouseEvent e) {            }

            @Override
            public void mouseEntered(MouseEvent e) {            }

            @Override
            public void mouseExited(MouseEvent e) {            }
        });

        f2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                pic.setIcon(new ImageIcon("Images/13.jpg"));
            }

            @Override
            public void mousePressed(MouseEvent e) {            }

            @Override
            public void mouseReleased(MouseEvent e) {            }

            @Override
            public void mouseEntered(MouseEvent e) {            }

            @Override
            public void mouseExited(MouseEvent e) {            }
        });

        f1.setOpaque(false);
        f2.setOpaque(false);
        f1.setForeground(Color.BLACK);
        f2.setForeground(Color.BLACK);
        f1.setFont(new Font("黑体", Font.BOLD, 17));
        f2.setFont(new Font("", Font.BOLD, 17));

        add(f1);
        add(f2);
        setLayout(null);
    }

}







