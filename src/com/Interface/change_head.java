package com.Interface;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

/**
 * Created by lmz on 2018/4/13 0013.
 */
public class change_head extends JFrame{
    private int admin_id;
    public boolean flag;
    private String head;
    private FriendView owner;
    public change_head(FriendView owner, boolean flag){
        this.owner = owner;
        this.flag = flag;
    }
    public void change_user_head(int id){
        this.admin_id = id;
        setTitle("change head.");
        setSize(350, 270);
        setLocation(700, 250);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        JLabel jl = new JLabel("change head.");
        jl.setBounds(80,10,200,30);
        jl.setFont(new Font("黑体", Font.BOLD, 25));
        JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b10;
        b1 = new JButton(new ImageIcon("Images/2.gif"));
        b2 = new JButton(new ImageIcon("Images/3.gif"));
        b3 = new JButton(new ImageIcon("Images/4.gif"));
        b4 = new JButton(new ImageIcon("Images/5.gif"));
        b5 = new JButton(new ImageIcon("Images/6.gif"));
        b6 = new JButton(new ImageIcon("Images/7.gif"));
        b7 = new JButton(new ImageIcon("Images/8.gif"));
        b8 = new JButton(new ImageIcon("Images/9.gif"));
        b9 = new JButton(new ImageIcon("Images/10.gif"));
        b10 = new JButton(new ImageIcon("Images/11.gif"));
        b1.setBounds(15, 50, 50, 50);
        b2.setBounds(75, 50, 50, 50);
        b3.setBounds(135, 50, 50, 50);
        b4.setBounds(195, 50, 50, 50);
        b5.setBounds(255, 50, 50, 50);
        b6.setBounds(15, 110, 50, 50);
        b7.setBounds(75, 110, 50, 50);
        b8.setBounds(135, 110, 50, 50);
        b9.setBounds(195, 110, 50, 50);
        b10.setBounds(255, 110, 50, 50);
        b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("2.gif",admin_id);
                head = "2.gif";
                b1.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("3.gif",admin_id);
                head = "3.gif";
                b2.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("4.gif",admin_id);
                head = "4.gif";
                b3.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("5.gif",admin_id);
                head = "5.gif";
                b4.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("6.gif",admin_id);
                head = "6.gif";
                b5.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("7.gif",admin_id);
                head = "7.gif";
                b6.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("8.gif",admin_id);
                head = "8.gif";
                b7.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("9.gif",admin_id);
                head = "9.gif";
                b8.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("10.gif",admin_id);
                head = "10.gif";
                b9.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b10.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });
        b10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new friend().set_head("11.gif",admin_id);
                head = "11.gif";
                b10.setBorder(BorderFactory.createLineBorder(Color.black,3));
                b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b4.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b5.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b6.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b7.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b8.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b9.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
                b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
            }
        });

        this.add(jl);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.add(b7);
        this.add(b8);
        this.add(b9);
        this.add(b10);

        JButton submit = new JButton("submit");
        submit.setBounds(110,180,100,30);
        submit.setFont(new Font("黑体", Font.BOLD, 15));
        submit.setContentAreaFilled(false);
        this.add(submit);
//        submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                owner.flag=false;
//                setVisible(false);
//            }
//        });
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                owner.head.setIcon(new ImageIcon("Images/"+head));
                setVisible(false);
            }
        });
    }
}
