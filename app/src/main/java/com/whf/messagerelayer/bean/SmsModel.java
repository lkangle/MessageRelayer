package com.whf.messagerelayer.bean;

import android.annotation.SuppressLint;
import android.telephony.SmsMessage;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsModel implements Serializable {
    @SuppressLint("SimpleDateFormat")
    public final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 发件人
    private String address;
    // 短信内容
    private String content;
    // 收件时间
    private String datetime;

    public static SmsModel fromMessage(SmsMessage message) {
        return new SmsModel().setContent(message.getMessageBody())
                .setAddress(message.getOriginatingAddress())
                .setDatetime(message.getTimestampMillis());
    }

    public static SmsModel test() {
        return new SmsModel().setContent("邮箱配置成功！")
                .setAddress("phone")
                .setDatetime(System.currentTimeMillis());
    }

    public String getAddress() {
        return address;
    }

    public SmsModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SmsModel setContent(String content) {
        this.content = content;
        return this;
    }

    public SmsModel addContent(String content) {
        this.content = this.content + content;
        return this;
    }

    public String getDatetime() {
        return datetime;
    }

    public SmsModel setDatetime(long timestamp) {
        Date date = new Date(timestamp);
        this.datetime = format.format(date);
        return this;
    }
}
