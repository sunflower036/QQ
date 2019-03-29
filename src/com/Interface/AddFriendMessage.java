package com.Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
public class AddFriendMessage extends JFrame {
    public AddFriendMessage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(400, 200, 500, 300);
        setResizable(false);
        setLayout(null);
        setTitle("AddFriendMessage");

        JLabel l1 = new JLabel("sjq 请求添加你为好友");
        l1.setBounds(188,70,500,40);
        add(l1);

        JButton b1 = new JButton("同意");
        JButton b2 = new JButton("拒绝");
        b1.setBounds(130,155,90,40);
        b2.setBounds(270,155,90,40);
        add(b1);
        add(b2);
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null,"您已成功添加sjq为好友");
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        this.setVisible(true);
    }
}

