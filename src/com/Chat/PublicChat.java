package com.Chat;
import com.Interface.*;
import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;


public class PublicChat extends JFrame {
    String admin_name;
    MsgSocketClient client = new MsgSocketClient();
    JTextArea dialog, input;
    JLabel[] jbls;
    ArrayList<Integer> str = new ArrayList<Integer>();
    ArrayList<Integer> str1 = new ArrayList<Integer>();
    public PublicChat(int group_id, int admin_id, MsgSocketClient cc){
        this.client = cc;
        setBounds(300,150,700,500);
        setResizable(false);
        setLayout(null);
        setTitle("PublicChat");
        frame_background.setBg(this,"Images/28.jpg");
        setVisible(true);

        dialog = new JTextArea();//对话显示框
        dialog.disable();//设置成只读属性
        dialog.setOpaque(false);
        dialog.setFont(new Font("宋体",Font.BOLD,20));
        dialog.setSelectedTextColor(Color.BLACK);
        JScrollPane JS_dialog = new JScrollPane(dialog);
        JS_dialog.setBounds(0,0,495,300);
        JS_dialog.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JS_dialog.setOpaque(false);
        JS_dialog.getViewport().setOpaque(false);
        add(JS_dialog);


        input = new JTextArea();//聊天输入框
        input.setOpaque(false);
        input.setFont(new Font("黑体",Font.BOLD,20));
        input.setForeground(Color.white);
        JScrollPane JS_input = new JScrollPane(input);
        JS_input.setBounds(0,310,495,110);
        JS_input.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JS_input.setOpaque(false);
        JS_input.getViewport().setOpaque(false);
        add(JS_input);

        JButton close = new JButton("关闭");
        JButton send = new JButton("发送");
        close.setBounds(266,430,85,30);
        send.setBounds(154,430,85,30);
        close.setFont(new Font("黑体", Font.BOLD, 15));
        send.setFont(new Font("黑体", Font.BOLD, 15));
        send.setForeground(Color.white);
        close.setForeground(Color.white);
        send.setContentAreaFilled(false);
        close.setContentAreaFilled(false);
        add(close);
        add(send);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        str = new friend().group_user(group_id);
        for(int i = 0; i < str.size(); i++){
            if(str.get(i)==admin_id)
                continue;
            else
                str1.add(str.get(i));
        }
        JPanel jp_jsp = new JPanel(new GridLayout(30,1));
        JScrollPane jsp = new JScrollPane(jp_jsp);
        jp_jsp.setOpaque(false);
       jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);


        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        String user_head;
        jbls = new JLabel[str.size()];
        for(int i=0; i<jbls.length; i++)
        {
            user_head = new friend().get_head(str.get(i));
            jbls[i] = new JLabel(Integer.toString(str.get(i)), new ImageIcon("Images/"+user_head) , JLabel.LEFT);
            //client.pchat(str.get(i), this);
            jbls[i].setOpaque(true);
            jp_jsp.add(jbls[i]);
        }
        jsp.setBounds(493, 0, 202, 473);
        add(jsp);
        this.setVisible(true);
        client.check_online(admin_id, str);
        client.pchat(group_id,this);
        admin_name = new friend().get_id(admin_id);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String msg = input.getText();
                    dialog.append(admin_name +":\n" + msg+"\n");
                    msg = "g" + group_id + ":" + admin_id + ":" + msg;
                    input.setText("");
                    client.groupsend(msg, str1);
                }catch (Exception c){
                    c.getStackTrace();
                }
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
