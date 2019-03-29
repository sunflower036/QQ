package com.Chat;

/**
 * Created by lmz on 2018/4/27 0027.
 */
import java.io.Serializable;

public class Message implements Serializable {

    // 客户端编号
    String ip;

    // 消息内容
    String msg;

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
