package com.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONObject;
import com.demo.DingTalkRobot;
import com.demo.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app")
public class HuaweiStatus {
    @RequestMapping("/status")
    @ResponseBody
    public String getStatus() {
        class MyThread extends Thread {
            @Override
            public void run() {
                String oldMessage = "";
                while (true) {
                    oldMessage = HuaweiStatus.sendMessage(oldMessage);
                }
            }
        }

        MyThread t = new MyThread();
        t.start();

        return "success";
    }

    public static String sendMessage(String oldMessage) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String day = format.format(date);
        String url = "http://h5.yyy.team007.com/api/tongji/index?timeType=day&time=" + day + "&member_id=10052&device_id=53467";
        System.out.println(url);


        HttpRequest httpRequest = new HttpRequest();
        String res = httpRequest.sendGet(url);
        System.out.println(res);

        String message = HuaweiStatus.generateMessage(res);
        DingTalkRobot.sendMessage(message, message.equals(oldMessage));

        try {
            Thread.sleep(300000);
        } catch (Exception e) {
            DingTalkRobot.sendMessage(e.getMessage(), false);
        }

        return message;
    }

    public static String generateMessage(String str) {
        JSONObject json = JSON.parseObject(str);
        JSONArray data = json.getJSONArray("data");

        List<Double> arr = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject entry = data.getJSONObject(i);
            if (entry.getString("type").equals("业务F14")) {
                arr.add(entry.getDouble("value") / 1024 / 1024);
            }
        }
        List<Double> sortedArr = arr.stream().sorted(Double::compare).collect(Collectors.toList());

        int index = arr.size() > 15 ? arr.size() - 15 : 0;
        double target = sortedArr.get(index);

        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        String message = "当前时间：" + format.format(new Date()) + "\n";
        message = message + "计费流量：" + String.format("%.2f", target) + "\n";
        message = message + "最新流量：" + String.format("%.2f", arr.get(arr.size() - 1)) + "\n";
        message = message + "预估价格：" + String.format("%.2f", target / 100 * 4.3) + "\n";
        return message;
    }
}
