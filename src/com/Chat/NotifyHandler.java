package com.Chat;

/**
 * Created by lmz on 2018/4/27 0027.
 */
import javax.lang.model.util.SimpleElementVisitor7;
import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class NotifyHandler extends Thread {
    Socket socket = null;

    Map<String, Socket> sessionMap = null;

    private static DecimalFormat df = null;
    int friend_id, admin_id;
    private FileInputStream fis;
    private DataOutputStream dos;
    static {
        // 设置数字格式，保留一位有效小数
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }
    private DataInputStream dis;
    private FileOutputStream fos;


    public NotifyHandler(Socket socket, Map<String, Socket> sessionMap) {
        this.socket = socket;
        this.sessionMap = sessionMap;
    }

    public void run() {
        try {
            InputStream in = socket.getInputStream();

            // 实现一次连接多次通话
            while (true) {
                ObjectInputStream ois = new ObjectInputStream(in);
                try {
                    Message msg = (Message) ois.readObject();

                    if(msg.getMsg().split(":")[0].equals("file")){
                        String DB_URL = "jdbc:mysql://LMZ/login";
                        String USER = "root";
                        String PASS = "xflmz36";
                        Connection conn = null;
                        PreparedStatement pstmt = null;
                        friend_id = Integer.parseInt(msg.getMsg().split(":")[1]);
                        admin_id = Integer.parseInt(msg.getMsg().split(":")[2]);
                        dis = new DataInputStream(in);
                        // 文件名和长度
                        String fileName = dis.readUTF();
                        long fileLength = dis.readLong();
                        File directory = new File("D:\\FTCache");
                        if(!directory.exists()) {
                            directory.mkdir();
                        }
                        File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
                        fos = new FileOutputStream(file);

                        // 开始接收文件
                        byte[] bytes = new byte[1024];
                        int length = 0;
                        Long temp = fileLength;
                        while(temp > 0) {
                            length = dis.read(bytes, 0, bytes.length);
                            fos.write(bytes, 0, length);
                            fos.flush();
                            temp -= length;
                        }
                        System.out.println("======== 文件接收成功 [File Name：" + fileName + "] [Size：" + getFormatFileSize(fileLength) + "] ========");
                        try{
//                            Class.forName("com.mysql.jdbc.Driver");
//                            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//                            String sql ="insert into file(friend_id, admin_id, file_name) value(?,?,?);";
//                            pstmt = conn.prepareStatement(sql);
//                            pstmt.setInt(1,friend_id);
//                            pstmt.setInt(2,admin_id);
//                            pstmt.setString(3,fileName);
//                            pstmt.executeUpdate();
//                            //send file to client
                            Socket targetSocket = sessionMap.get(msg.getIP());
                            OutputStream out = targetSocket.getOutputStream();
                            fis = new FileInputStream(file);
                            dos = new DataOutputStream(out);

                            ObjectOutputStream oos = new ObjectOutputStream(out);
                            Message m = new Message();
                            m.setMsg("file:"+friend_id+":"+admin_id);
                            m.setIP(Integer.toString(friend_id));
                            oos.writeObject(m);

                            dos.writeUTF(file.getName());
                            dos.flush();
                            dos.writeLong(file.length());
                            dos.flush();
                            bytes = new byte[1024];
                            length = 0;
                            while((length = fis.read(bytes, 0, bytes.length)) != -1) {
                                dos.write(bytes, 0, length);
                                dos.flush();
                            }
                            System.out.println("传输完毕");

//                            sql ="delete from file where friend_id=?";
//                            pstmt = conn.prepareStatement(sql);
//                            pstmt.setInt(1,friend_id);
//                            pstmt.executeUpdate();
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
                    }
                    else if(msg.getMsg().split(":")[0].indexOf("g")==-1){
                        String admin_id = msg.getMsg().split(":")[0];
                        String message = msg.getMsg().split(":")[1];
                        System.out.println(msg.getMsg());

                        if(message.equals("login")){
                            sessionMap.put(admin_id, socket);
                            ArrayList<String> str = new ArrayList<String>();
                            String DB_URL = "jdbc:mysql://LMZ/login";
                            String USER = "root";
                            String PASS = "xflmz36";
                            Connection conn = null;
                            PreparedStatement pstmt = null;
                            String name = null;
                            try{
                                Class.forName("com.mysql.jdbc.Driver");
                                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                                String sql ="select message from message where target=?";
                                pstmt = conn.prepareStatement(sql);
                                pstmt.setString(1,admin_id);
                                ResultSet rs = pstmt.executeQuery();
                                while(rs.next()){
                                    str.add(rs.getString(1));
                                }
                                //System.out.println(str);
                                String m="";
                                for(int i = 0; i < str.size(); i++){
                                    m += str.get(i);
                                }
                                //System.out.println("m="+m);
                                msg.setMsg(m);
                                sql ="delete from message where target=?";
                                pstmt = conn.prepareStatement(sql);
                                pstmt.setString(1,admin_id);
                                pstmt.executeUpdate();
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
                        }
                        if(msg.getMsg().split(":")[0].equals("checkonline")){
                            try {
                                Socket targetSocket = sessionMap.get(msg.getIP());
                                ArrayList<Integer> str = new ArrayList<>();
                                java.util.Iterator it = sessionMap.entrySet().iterator();
                                while(it.hasNext()){
                                    java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                                    str.add(Integer.parseInt(entry.getKey().toString()));
                                }
                                String message1 = "online:";
                                message1 += str.get(0);
                                for(int i = 1; i < str.size(); i++){
                                    message1 += "," + str.get(i);
                                }
                                msg.setMsg(message1);
                                System.out.println("msg:"+msg.getMsg());
                                OutputStream out = targetSocket.getOutputStream();
                                ObjectOutputStream oos = new ObjectOutputStream(out);
                                oos.writeObject(msg);
                                System.out.println("服务端已转发");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(sessionMap.get(msg.getIP())==null){
                            //database
                            String DB_URL = "jdbc:mysql://LMZ/login";
                            String USER = "root";
                            String PASS = "xflmz36";
                            Connection conn = null;
                            PreparedStatement pstmt = null;
                            String name = null;
                            try{
                                Class.forName("com.mysql.jdbc.Driver");
                                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                                String sql ="insert into message(source, target, message)value(?,?,?);";
                                pstmt = conn.prepareStatement(sql);
                                pstmt.setString(1,admin_id);
                                pstmt.setString(2,msg.getIP());
                                pstmt.setString(3,admin_id+":"+message);
                                pstmt.executeUpdate();
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
                        }
                        else{
                            // 发送数据
                            try {
                                Socket targetSocket = sessionMap.get(msg.getIP());
                                //System.out.println(msg.getIP());
                                OutputStream out = targetSocket.getOutputStream();
                                ObjectOutputStream oos = new ObjectOutputStream(out);
                                oos.writeObject(msg);
                                System.out.println("服务端已转发");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{
                        String admin_id = msg.getMsg().split(":")[1];
                        String message = msg.getMsg();
                        System.out.println(msg.getIP());
                        if(sessionMap.get(msg.getIP())==null){
                            //database
                            //System.out.println("shujuku");
                            String DB_URL = "jdbc:mysql://LMZ/login";
                            String USER = "root";
                            String PASS = "xflmz36";
                            Connection conn = null;
                            PreparedStatement pstmt = null;
                            String name = null;
                            try{
                                Class.forName("com.mysql.jdbc.Driver");
                                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                                String sql ="insert into message(source, target, message)value(?,?,?);";
                                pstmt = conn.prepareStatement(sql);
                                pstmt.setString(1,admin_id);
                                pstmt.setString(2,msg.getIP());
                                pstmt.setString(3,message);
                                pstmt.executeUpdate();
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
                        }
                        else{
                            // 发送数据
                            try {
                                //System.out.println("message"+msg.getMsg());
                                Socket targetSocket = sessionMap.get(msg.getIP());
                                //System.out.println(msg.getIP());
                                OutputStream out = targetSocket.getOutputStream();
                                ObjectOutputStream oos = new ObjectOutputStream(out);
                                oos.writeObject(msg);
                                System.out.println("服务端已转发");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }


                    if (socket.isClosed()) {
                        break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    try {
                        socket.close();
                        break;
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private String getFormatFileSize(long length) {
        double size = ((double) length) / (1 << 30);
        if(size >= 1) {
            return df.format(size) + "GB";
        }
        size = ((double) length) / (1 << 20);
        if(size >= 1) {
            return df.format(size) + "MB";
        }
        size = ((double) length) / (1 << 10);
        if(size >= 1) {
            return df.format(size) + "KB";
        }
        return length + "B";
    }
}
