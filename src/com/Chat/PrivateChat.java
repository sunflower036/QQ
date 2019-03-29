package com.Chat;
import com.Interface.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

class frame_background  {
    public static void setBg (JFrame frame,String path){
        ((JPanel)frame.getContentPane()).setOpaque(false);
        ImageIcon img = new ImageIcon(path);
        JLabel background = new JLabel(img);
        frame.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
        background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
    }
}



public class PrivateChat extends JFrame {
    String friend_name;
    String admin_name;
    int friend_id;
    JButton send;
    JTextArea input,dialog;
    MsgSocketClient client = new MsgSocketClient();

    public PrivateChat(int admin_id, String friend_name, MsgSocketClient cc) {
        this.client = cc;
        this.friend_name = friend_name;
        int friend_id = new friend().name_id(friend_name);
        this.friend_id = friend_id;
        setBounds(300,150,700,500);
        setResizable(false);
        setLayout(null);
        setTitle(friend_name);
        frame_background.setBg(this,"Images/29.jpg");

        JFrame f = new JFrame();
        JButton b1 = new JButton();
        b1.setIcon(new ImageIcon("Images/25.png"));
        b1.setBounds(0,0,50,50);
        b1.setContentAreaFilled(false);
        add(b1);

        final JFileChooser fc = new JFileChooser();

        b1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(f);
                File file = fc.getSelectedFile();
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    JOptionPane.showMessageDialog(f, "打开文件:" + file.getAbsolutePath());
                }
                //file is file route
                if(file.exists()){
                    try{
                        cc.filesend(file, friend_id, admin_id);
                        dialog.append("Successfully send "+file.getName()+" to "+new friend().get_id(friend_id));
                    }catch (Exception s){
                        s.getStackTrace();
                    }
                }

            }
        });


        dialog = new JTextArea();//对话显示框
        dialog.setFont(new Font("宋体",Font.BOLD,20));
        dialog.setSelectedTextColor(Color.BLACK);
        dialog.disable();//设置成只读属性
        dialog.setOpaque(false);

        JScrollPane JS_dialog = new JScrollPane(dialog);
        JS_dialog.setBounds(0,50,695,250);
        JS_dialog.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JS_dialog.setOpaque(false);
        JS_dialog.getViewport().setOpaque(false);
        add(JS_dialog);

        input = new JTextArea();//聊天输入框
        input.setOpaque(false);
        JScrollPane JS_input = new JScrollPane(input);
        JS_input.setBounds(0,310,695,110);
        input.setFont(new Font("黑体",Font.BOLD,20));
        input.setForeground(Color.white);
        JS_input.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JS_input.setOpaque(false);
        JS_input.getViewport().setOpaque(false);
        add(JS_input);

        JButton close = new JButton("关闭");
        send = new JButton("发送");
        close.setBounds(356,430,85,30);
        send.setBounds(444,430,85,30);
        close.setFont(new Font("黑体", Font.BOLD, 15));
        send.setFont(new Font("黑体", Font.BOLD, 15));
        send.setForeground(Color.white);
        close.setForeground(Color.white);
        send.setContentAreaFilled(false);
        close.setContentAreaFilled(false);
        add(close);
        add(send);
        this.setVisible(true);
        dialog.setCaretPosition(dialog.getDocument().getLength());
        client.chat(friend_id,this);
        admin_name = new friend().get_id(admin_id);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String msg = input.getText();
                    dialog.append(admin_name +":\n" + msg+"\n");
                    msg = admin_id + ":" + msg;
                    input.setText("");
                    client.chatsend(friend_id,msg);
                }catch (Exception c){
                    c.getStackTrace();
                }

            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                for(int i = 0; i < cc.chat_open.size(); i++){
                    if(cc.chat_open.get(i)==friend_id)
                        cc.chat_open.remove(i);
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