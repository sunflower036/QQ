package com.Interface;
import com.Chat.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by lmz on 2018/4/12 0012.
 */
public class FriendView implements ActionListener,MouseListener{
     JFrame jf=new JFrame();
    //背景图层
    ImageIcon background;
    JPanel buttom;
    JLabel imgLabel;
    JButton jb;
    public boolean flag=true;

    //上层北边
    JLabel name;
    JButton head;

    //上层南边
    JPanel jp;//卡片布局
    CardLayout cl;

    //第一张卡片
    JPanel jp1;
    JButton jp1_jb1,jp1_jb2,jp1_jb3,jp1_jb4,jp1_jb5;

    //第二张卡片
    JPanel jp2;
    JScrollPane jsp;
    JPanel jp_jsp;//用来放jsp
    JButton jp2_jb1,jp2_jb2,jp2_jb3,jp2_jb4,jp2_jb5;

    //第三张卡片
    JPanel jp3;
    JScrollPane jsp2;
    JPanel jp_jsp2;//用来放jsp2
    JButton jp3_jb1,jp3_jb2,jp3_jb3,jp3_jb4,jp3_jb5;

    //其他变量
    private int admin_id;
    ArrayList<String> str = new ArrayList<String>();
    ArrayList<String> str1 = new ArrayList<String>();
    //public ArrayList<Integer> chat_open = new ArrayList<>();
    String admin_name;
    private String admin_head;
    private String admin_back;

    private MsgSocketClient cc = new MsgSocketClient();
    private JButton b5=new JButton("更换背景");

    public void user(int admin_id){
        this.admin_id = admin_id;
    }

    //构造函数
    public FriendView(int admin_id)
    {
        cc.socketStart(admin_id);
        user(admin_id);
        friend friend = new friend();
        //处理北边的东西（头像，昵称）
        admin_head = friend.get_head(this.admin_id);
        head = new JButton(new ImageIcon("Images/"+admin_head));
        head.setBounds(10, 40, 50, 50);
        head.addActionListener(this);
        admin_name = friend.get_id(this.admin_id);
        name = new JLabel(admin_name);
        name.setBounds(70, 42, 80, 20);
        name.setFont(new Font("宋体",Font.BOLD, 16));
        name.setForeground(Color.white);
        admin_back=friend.get_back(this.admin_id);
        //处理背景
        backGround();

        //设置好友列表为卡片布局
        cl = new CardLayout();
        jp = new JPanel();
        jp.setOpaque(false);
        //jp.setBounds(0, 100, background.getIconWidth(), background.getIconHeight());
        jp.setBounds(0, 100, background.getIconWidth(), background.getIconHeight());

        //处理第一张卡片
        firstCard();
        //处理第二张卡片
        secondCard();
        //处理第三张卡片
        thirdCard();

        jf.add(head);
        jf.add(name);
        jp.setLayout(cl);
        jp.add(jp1,"1");
        jp.add(jp2,"2");
        jp.add(jp3,"3");
        jf.add(jp);
        jf.getLayeredPane().setLayout(null);
        jf.setLayout(null);
        jf.setSize(283, 720);
        jf.setLocation(800, 30);
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cc.chatreceive();
        //弹出菜单更换背景
        final JPopupMenu jp =new JPopupMenu();
        jp.add(b5);
        b5.setContentAreaFilled(false);
        jf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3){
                    //GG弹出菜单
                    jp.show(jf,e.getX(),e.getY());}}
        });
        b5.addActionListener(this);
        jf.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {
                if(flag==false)
                    change();
            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }


        //处理背景方法
    public void backGround()
    {
        background = new ImageIcon("Images/"+admin_back);
        imgLabel = new JLabel(background);
        imgLabel.setBounds(0, 0,  background.getIconWidth(), background.getIconHeight());
        buttom=(JPanel)jf.getContentPane();
        //将contentPane设置为透明的
        buttom.setOpaque(false);
        jf.getLayeredPane().add(imgLabel , new Integer(Integer.MIN_VALUE));
    }

    //处理第一张卡片方法
    public void firstCard()
    {
        jp1 = new JPanel();

        jp1_jb1 = new JButton("> 我的好友");
        jp1_jb1.addActionListener(this);
        jp1_jb1.setLayout(null);
        jp1_jb1.setSize(277, 35);
        jp1_jb1.setHorizontalAlignment(SwingConstants.LEFT );
        jp1_jb1.setFont(new Font("黑体", Font.BOLD, 15));
        jp1_jb1.setForeground(Color.white);
        jp1_jb1.setContentAreaFilled(false);

        jp1_jb2 = new JButton("> 我的群组");
        jp1_jb2.addActionListener(this);
        jp1_jb2.setLayout(null);
        jp1_jb2.setBounds(0, 35, 277, 35);
        jp1_jb2.setHorizontalAlignment(SwingConstants.LEFT );
        jp1_jb2.setFont(new Font("黑体", Font.BOLD, 15));
        jp1_jb2.setForeground(Color.white);
        jp1_jb2.setContentAreaFilled(false);

        jp1_jb3 = new JButton("添加好友");
        jp1_jb3.addActionListener(this);
        jp1_jb3.setLayout(null);
        jp1_jb3.setBounds(0, 70, 92, 35);
        jp1_jb3.setHorizontalAlignment(SwingConstants.LEFT );
        jp1_jb3.setFont(new Font("黑体", Font.BOLD, 13));
        jp1_jb3.setForeground(Color.white);
        jp1_jb3.setContentAreaFilled(false);

        jp1_jb4 = new JButton("添加群组");
        jp1_jb4.addActionListener(this);
        jp1_jb4.setLayout(null);
        jp1_jb4.setBounds(92, 70, 93, 35);
        jp1_jb4.setHorizontalAlignment(SwingConstants.LEFT );
        jp1_jb4.setFont(new Font("黑体", Font.BOLD, 13));
        jp1_jb4.setForeground(Color.white);
        jp1_jb4.setContentAreaFilled(false);

        jp1_jb5 = new JButton("通  知");
        jp1_jb5.addActionListener(this);
        jp1_jb5.setLayout(null);
        jp1_jb5.setBounds(184, 70, 93, 35);
        jp1_jb5.setHorizontalAlignment(SwingConstants.LEFT );
        jp1_jb5.setFont(new Font("黑体", Font.BOLD, 13));
        jp1_jb5.setForeground(Color.white);
        jp1_jb5.setContentAreaFilled(false);

        jp1.add(jp1_jb1);
        jp1.add(jp1_jb2);
        jp1.add(jp1_jb3);
        jp1.add(jp1_jb4);
        jp1.add(jp1_jb5);
        jp1.setLayout(null);
        jp1.setOpaque(false);
    }

    //处理第二张卡片方法
    public void secondCard()
    {
        jp2 = new JPanel();

        jp2_jb1 = new JButton("↓ 我的好友");
        jp2_jb1.addActionListener(this);
        jp2_jb1.setLayout(null);
        jp2_jb1.setSize(277, 35);
        jp2_jb1.setHorizontalAlignment(SwingConstants.LEFT );
        jp2_jb1.setFont(new Font("黑体", Font.BOLD, 15));
        jp2_jb1.setForeground(Color.white);
        jp2_jb1.setContentAreaFilled(false);

        jp2_jb2 = new JButton("> 我的群组");
        jp2_jb2.addActionListener(this);
        jp2_jb2.setLayout(null);
        jp2_jb2.setBounds(0, 284, 277, 35);
        jp2_jb2.setHorizontalAlignment(SwingConstants.LEFT );
        jp2_jb2.setFont(new Font("黑体", Font.BOLD, 15));
        jp2_jb2.setForeground(Color.white);
        jp2_jb2.setContentAreaFilled(false);

        jp2_jb3 = new JButton("添加好友");
        jp2_jb3.addActionListener(this);
        jp2_jb3.setLayout(null);
        jp2_jb3.setBounds(0, 319, 92, 35);
        jp2_jb3.setHorizontalAlignment(SwingConstants.LEFT );
        jp2_jb3.setFont(new Font("黑体", Font.BOLD, 13));
        jp2_jb3.setForeground(Color.white);
        jp2_jb3.setContentAreaFilled(false);

        jp2_jb4 = new JButton("添加群组");
        jp2_jb4.addActionListener(this);
        jp2_jb4.setLayout(null);
        jp2_jb4.setBounds(92, 319, 92, 35);
        jp2_jb4.setHorizontalAlignment(SwingConstants.LEFT );
        jp2_jb4.setFont(new Font("黑体", Font.BOLD, 13));
        jp2_jb4.setForeground(Color.white);
        jp2_jb4.setContentAreaFilled(false);

        jp2_jb5 = new JButton("通  知");
        jp2_jb5.addActionListener(this);
        jp2_jb5.setLayout(null);
        jp2_jb5.setBounds(184, 319, 93, 35);
        jp2_jb5.setHorizontalAlignment(SwingConstants.LEFT );
        jp2_jb5.setFont(new Font("黑体", Font.BOLD, 13));
        jp2_jb5.setForeground(Color.white);
        jp2_jb5.setContentAreaFilled(false);

        str = new friend().admin_friend(admin_id);
        str1 = new friend().admin_friend_head(admin_id);

        jp_jsp = new JPanel(new GridLayout(str.size(),1));
        jsp = new JScrollPane(jp_jsp);

        JLabel[] jbls = new JLabel[str.size()];
        for(int i=0; i<jbls.length; i++)
        {
            jbls[i] = new JLabel(str.get(i), new ImageIcon("Images/"+str1.get(i)), JLabel.LEFT);
            jbls[i].addMouseListener(this);
            jp_jsp.add(jbls[i]);
        }

        jsp.setBounds(1, 35, 275, 249);

        jp2.add(jsp);
        jp2.add(jp2_jb1);
        jp2.add(jp2_jb2);
        jp2.add(jp2_jb3);
        jp2.add(jp2_jb4);
        jp2.add(jp2_jb5);
        jp2.setLayout(null);
        jp2.setOpaque(false);
    }

    //处理第三张卡片方法
    public void thirdCard()
    {
        jp3 = new JPanel();

        jp3_jb1 = new JButton("> 我的好友");
        jp3_jb1.addActionListener(this);
        jp3_jb1.setLayout(null);
        jp3_jb1.setSize(277, 35);
        jp3_jb1.setHorizontalAlignment(SwingConstants.LEFT );
        jp3_jb1.setFont(new Font("黑体", Font.BOLD, 15));
        jp3_jb1.setForeground(Color.white);
        jp3_jb1.setContentAreaFilled(false);

        jp3_jb2 = new JButton("↓ 我的群组");
        jp3_jb2.addActionListener(this);
        jp3_jb2.setLayout(null);
        jp3_jb2.setBounds(0, 35, 277, 35);
        jp3_jb2.setHorizontalAlignment(SwingConstants.LEFT );
        jp3_jb2.setFont(new Font("黑体", Font.BOLD, 15));
        jp3_jb2.setForeground(Color.white);
        jp3_jb2.setContentAreaFilled(false);

        jp3_jb3 = new JButton("添加好友");
        jp3_jb3.addActionListener(this);
        jp3_jb3.setLayout(null);
        jp3_jb3.setBounds(0, 319, 92, 35);
        jp3_jb3.setHorizontalAlignment(SwingConstants.LEFT );
        jp3_jb3.setFont(new Font("黑体", Font.BOLD, 13));
        jp3_jb3.setForeground(Color.white);
        jp3_jb3.setContentAreaFilled(false);

        jp3_jb4 = new JButton("添加群组");
        jp3_jb4.addActionListener(this);
        jp3_jb4.setLayout(null);
        jp3_jb4.setBounds(92, 319, 92, 35);
        jp3_jb4.setHorizontalAlignment(SwingConstants.LEFT );
        jp3_jb4.setFont(new Font("黑体", Font.BOLD, 13));
        jp3_jb4.setForeground(Color.white);
        jp3_jb4.setContentAreaFilled(false);

        jp3_jb5 = new JButton("通  知");
        jp3_jb5.addActionListener(this);
        jp3_jb5.setLayout(null);
        jp3_jb5.setBounds(184, 319, 93, 35);
        jp3_jb5.setHorizontalAlignment(SwingConstants.LEFT );
        jp3_jb5.setFont(new Font("黑体", Font.BOLD, 13));
        jp3_jb5.setForeground(Color.white);
        jp3_jb5.setContentAreaFilled(false);

        str = new friend().admin_group(admin_id);
        str1 = new friend().admin_group_head(admin_id);

        jp_jsp2 = new JPanel(new GridLayout(str.size(),1));
        jsp2 = new JScrollPane(jp_jsp2);

        //初始化好友
        JLabel[] jbls = new JLabel[str.size()];
        for(int i=0; i<jbls.length; i++)
        {
            jbls[i] = new JLabel(str.get(i), new ImageIcon("Images/"+str1.get(i)),JLabel.LEFT);
            jbls[i].addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getClickCount()==2)
                    {
                        String str = ((JLabel)e.getSource()).getText();
                        int id = Integer.parseInt(str);
                        new PublicChat(id, admin_id, cc);
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

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
            });
            jp_jsp2.add(jbls[i]);
        }

        jsp2.setBounds(1, 70, 275, 249);

        jp3.add(jsp2);
        jp3.add(jp3_jb1);
        jp3.add(jp3_jb2);
        jp3.add(jp3_jb3);
        jp3.add(jp3_jb4);
        jp3.add(jp3_jb5);
        jp3.setLayout(null);
        jp3.setOpaque(false);
    }

    public void change(){
        new FriendView(admin_id);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b5){
            change_background ba = new change_background(this,flag);
            ba.change_user_back(admin_id);
        }
        if(e.getSource()==head){
            change_head ch = new change_head(this,flag);
            ch.change_user_head(admin_id);
        }
        //第一张卡片的按钮
        if(e.getSource()==jp1_jb1)
        {
            cl.show(jp, "2");
        }
        else if(e.getSource()==jp1_jb2)
        {
            cl.show(jp, "3");
        }
        else if(e.getSource()==jp1_jb3){
            new add_friend().view(admin_id);
        }
        else if(e.getSource()==jp1_jb4){
            new add_group().view(admin_id);
        }
        else if(e.getSource()==jp1_jb5){
            new notice(admin_id);
        }

        //第二张卡片的按钮
        if(e.getSource()==jp2_jb1)
        {
            cl.show(jp, "1");;
        }
        else if(e.getSource()==jp2_jb2)
        {
            cl.show(jp, "3");;
        }
        else if(e.getSource()==jp2_jb3){
            new add_friend().view(admin_id);
        }
        else if(e.getSource()==jp2_jb4){
            new add_group().view(admin_id);
        }
        else if(e.getSource()==jp2_jb5){
            new notice(admin_id);
        }

        //第三张卡片的按钮
        if(e.getSource()==jp3_jb1)
        {
            cl.show(jp, "2");
        }
        else if(e.getSource()==jp3_jb2)
        {
            cl.show(jp, "1");
        }
        else if(e.getSource()==jp3_jb3){
            new add_friend().view(admin_id);
        }
        else if(e.getSource()==jp3_jb4){
            new add_group().view(admin_id);
        }
        else if(e.getSource()==jp3_jb5){
            new notice(admin_id);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if(e.getClickCount()==2)
        {
            String str = ((JLabel)e.getSource()).getText();
            new PrivateChat(admin_id,str,cc);
            int friend_id = new friend().name_id(str);
            cc.chat_open.add(friend_id);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

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
