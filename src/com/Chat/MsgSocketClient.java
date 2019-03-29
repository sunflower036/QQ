package com.Chat;

/**
 * Created by lmz on 2018/4/27 0027.
 */
import com.Interface.FriendView;
import com.Interface.friend;

import java.awt.*;
import java.io.*;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MsgSocketClient {
    Socket socket = null;
    InputStreamReader input = null;
    InputStream in = null;
    OutputStream out = null;
    int admin_id;
    ArrayList<String> receive = new ArrayList<>();
    public ArrayList<Integer> chat_open = new ArrayList<>();
    int i;
    FileInputStream fis;
    FileOutputStream fos;
    DataOutputStream dos;
    DataInputStream dis;
    private static DecimalFormat df = null;
    static {
        // 设置数字格式，保留一位有效小数
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }

    public void socketStart(int admin_id){
        this.admin_id = admin_id;
        try{
            //this.sessionMap = sessionMap;
            this.socket = new Socket("LMZ", 8888);
            System.out.println("客户端启动.......");
            chatsend(admin_id, admin_id+":login");
            //System.out.println(admin_id+":login");
        }catch (Exception e){
            e.getStackTrace();
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

    public void chat(int friend_id, PrivateChat pc){
        PrivateChat privateChat = pc;
        Socket socket = this.socket;

        try{
            // 接受返回数据
            new Thread() {
                public void run() {
                    try {
                        while (true) {
                            receive = privateChat.client.receive;
                            for(int i = 0; i < receive.size(); i++){
                                if(receive.get(i)!=null && receive.get(i).split(":")[0].equals(Integer.toString(friend_id))){
                                    String friend_name = new friend().get_id(Integer.parseInt(receive.get(i).split(":")[0]));
                                    privateChat.dialog.append(friend_name + ":\n" + receive.get(i).split(":")[1]+"\n");
                                    receive.remove(i);
                                }
                                else if(receive.get(i)!=null && receive.get(i).split(":")[0].equals("file")){
                                    if(receive.get(i).split(":")[1].equals(Integer.toString(friend_id))){
                                        String friend_name = new friend().get_id(Integer.parseInt(receive.get(i).split(":")[1]));
                                        String file_name = receive.get(i).split(":")[2];
                                        privateChat.dialog.append("Already received file : " + file_name + " from " + new friend().get_id(friend_id));
                                        receive.remove(i);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void pchat(int group_id, PublicChat pc){
        PublicChat publicChat = pc;
        Socket socket = this.socket;
        try{
            // 接受返回数据
            new Thread() {
                public void run() {
                    try {
                        while (true) {
                            for(int i = 0; i < receive.size(); i++){
                                //System.out.println(Integer.toString(friend_id));
                                if(receive.get(i)!=null && receive.get(i).split(":")[0].equals("g"+Integer.toString(group_id))){
                                    String friend_name = new friend().get_id(Integer.parseInt(receive.get(i).split(":")[1]));
                                    publicChat.dialog.append(friend_name + ":\n" + receive.get(i).split(":")[2]+"\n");
                                    //System.out.println("receive "+receive.get(i));
                                    receive.remove(i);
                                }
                                else if(receive.get(i)!=null && receive.get(i).split(":")[0].equals("online")){
                                    for(int j = 0; j < receive.get(i).split(":")[1].split(",").length; j++){

                                        if(publicChat.str.contains(Integer.parseInt((receive.get(i).split(":")[1].split(","))[j]))){

                                            int index = Integer.parseInt(receive.get(i).split(":")[1].split(",")[j]);
                                            //index = online_id
                                            for(int k = 0; k < publicChat.jbls.length; k++){
                                                if(publicChat.jbls[k].getText().equals(Integer.toString(index))){
                                                    publicChat.jbls[k].setBackground(Color.green);
                                                    //System.out.println("set green");
                                                }
                                            }
                                        }
                                    }
                                    receive.remove(i);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void chatreceive(){

        Socket socket = this.socket;
        try{
            // 接受返回数据
            new Thread() {
                public void run() {
                    try {
                        while (true) {
                            in = socket.getInputStream();
                            ObjectInputStream ois = new ObjectInputStream(in);
                            Message msg = (Message) ois.readObject();
                            System.out.println("received message: "+msg.getMsg());

                            if(msg.getMsg().split(":")[0].equals("file")){
                                //in = socket.getInputStream();
                                dis = new DataInputStream(in);
                                String fileName = dis.readUTF();
                                receive.add("file:" + msg.getMsg().split(":")[2] + ":" + fileName);
                                long fileLength = dis.readLong();
                                File directory = new File("D:\\"+admin_id);
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
                            }
                            else{
                                receive.add(msg.getMsg());
                                if(!msg.getMsg().equals("") && !msg.getMsg().split(":")[0].equals("online")){
                                    //if not contain friend_id
                                    if(chat_open==null || !chat_open.contains(Integer.parseInt(msg.getMsg().split(":")[0]))){
                                        int friend_id = Integer.parseInt(msg.getMsg().split(":")[0]);
                                        new PrivateChat(admin_id,new friend().get_id(friend_id),MsgSocketClient.this);
                                        chat_open.add(friend_id);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }catch (Exception e){
            e.getStackTrace();
        }
    }


    public void chatsend(int friend_id, String messsage) {
        try{
            socket = this.socket;
            out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            input = new InputStreamReader(System.in);
            Message msg = new Message();
            msg.setIP(Integer.toString(friend_id));
            msg.setMsg(messsage);
            oos.writeObject(msg);
            System.out.println("已发送："+msg.getMsg());
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void groupsend(String messsage, ArrayList<Integer> list) {
        try {
            socket = this.socket;
            out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            input = new InputStreamReader(System.in);
            Message msg = new Message();
            for (i = 0; i < list.size(); i++) {
                msg.setIP(Integer.toString(list.get(i)));
                msg.setMsg(messsage);
                System.out.println("已发送" + msg.getMsg());
                oos.writeObject(msg);
            }
        }catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void check_online(int admin_id, ArrayList<Integer> online){
        try{
            socket = this.socket;
            out = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            input = new InputStreamReader(System.in);
            Message msg = new Message();
            String message = "checkonline:";
            message += online.get(0);
            for(int i = 1 ; i < online.size(); i++){
                message += "," + online.get(i);
            }
            msg.setMsg(message);
            msg.setIP(Integer.toString(admin_id));
            oos.writeObject(msg);
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    public void filesend(File file, int friend_id, int admin_id) throws Exception{
        try {
            socket = this.socket;
            out = socket.getOutputStream();
            fis = new FileInputStream(file);
            dos = new DataOutputStream(out);

            ObjectOutputStream oos = new ObjectOutputStream(out);
            //input = new InputStreamReader(System.in);
            Message msg = new Message();
            String message = "file:"+friend_id+":"+admin_id;
            msg.setMsg(message);
            msg.setIP(Integer.toString(friend_id));
            oos.writeObject(msg);

            // 文件名和长度
            dos.writeUTF(file.getName());
            dos.flush();
            dos.writeLong(file.length());
            dos.flush();

            // 开始传输文件
            System.out.println("======== 开始传输文件 ========");
            byte[] bytes = new byte[1024];
            int length = 0;
            long progress = 0;
            while((length = fis.read(bytes, 0, bytes.length)) != -1) {
                dos.write(bytes, 0, length);
                dos.flush();
                progress += length;
                System.out.print("| " + (100*progress/file.length()) + "% |");
            }

            System.out.println();
            System.out.println("======== 文件传输成功 ========");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
