package com.Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Hesijia~ on 2018/5/7.
 */
public class change_background extends JFrame{
        private int admin_id;
        public boolean flag;
        private String back;
        private FriendView owner;
        public change_background(FriendView owner, boolean flag){
            this.owner = owner;
            this.flag = flag;
        }

        public void change_user_back(int id){
            this.admin_id = id;
            setTitle("change background.");
            setSize(896, 500);
            setLocation(700, 250);
            setLayout(null);
            setResizable(false);
            setVisible(true);
            JLabel jl = new JLabel("change background.");
            jl.setBounds(300,10,500,30);
            jl.setFont(new Font("黑体", Font.BOLD, 30));
            JButton b1,b2,b3;
            b1 = new JButton(new ImageIcon("Images/23.jpg"));
            b2 = new JButton(new ImageIcon("Images/22.jpg"));
            b3 = new JButton(new ImageIcon("Images/21.jpg"));
            b1.setBounds(10, 70, 276, 300);
            b2.setBounds(310, 70, 276, 300);
            b3.setBounds(610, 70, 276, 300);
            b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
            b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
            b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b1.setBorder(BorderFactory.createLineBorder(Color.black,5));
                    b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
                    b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
                    new friend().set_back("3.jpg",admin_id);
                    back = "3.jpg";
                }
            });
            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b2.setBorder(BorderFactory.createLineBorder(Color.black,5));
                    b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
                    b3.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
                    new friend().set_back("20.jpg",admin_id);
                    back = "20.jpg";
                }
            });
            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    b3.setBorder(BorderFactory.createLineBorder(Color.black,5));
                    b1.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
                    b2.setBorder(BorderFactory.createLineBorder(Color.lightGray,2));
                    new friend().set_back("20.jpg",admin_id);
                    new friend().set_back("19.jpg",admin_id);
                    back = "19.jpg";
                }
            });

            this.add(jl);
            this.add(b1);
            this.add(b2);
            this.add(b3);

            JButton submit = new JButton("submit");
            submit.setBounds(410,400,100,50);
            submit.setFont(new Font("黑体", Font.BOLD, 20));
            submit.setContentAreaFilled(false);
            this.add(submit);
//            submit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                owner.flag=false;
//                setVisible(false);
//            }
//        });
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    owner.imgLabel.setIcon(new ImageIcon("Images/"+back));
                    setVisible(false);
                }
            });
        }
}
