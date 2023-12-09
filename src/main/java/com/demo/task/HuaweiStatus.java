package com.demo.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.util.DingTalkRobot;
import com.demo.util.HttpRequest;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HuaweiStatus {
    private double lastTarget = 0;

    private double currentTarget = 0;


    public String sendMessage(String oldMessage) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = new Date();
        String day = format.format(date);
        String url = "http://h5.yyy.team007.com/api/tongji/index?timeType=day&time=" + day + "&member_id=10052&device_id=53467";
        System.out.println(url);

        String message = "";
        try {
            String res = HttpRequest.sendGet(url);

            message = generateMessage(res);
            System.out.println(message + "\n");
            DingTalkRobot.sendMessage(message, currentTarget > lastTarget);
        } catch (Exception e) {
            DingTalkRobot.sendMessage(e.getMessage(), true);
        }

        return message;
    }

    public String generateMessage(String str) {
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

        int index = arr.size() > 15 ? arr.size() - 15 - 1 : 0;
        double target = sortedArr.get(index);
        lastTarget = currentTarget;
        currentTarget = target;

        int lastIndex = index >= 4 ? index - 4 : index;
        List<Double> lastArr = sortedArr.subList(lastIndex, sortedArr.size());
        List<String> lastStrArr = new ArrayList<>();
        for (Double num : lastArr) {
            lastStrArr.add(String.format("%.2f", num));
        }

        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        String message = "当前时间：" + format.format(new Date()) + "\n";
        message = message + "计费流量：" + String.format("%.2f", target) + "\n";
        message = message + "最新流量：" + String.format("%.2f", arr.get(arr.size() - 1)) + "\n";
        message = message + "预估价格：" + String.format("%.2f", target / 100 * 4.139) + "\n";
        try {
            message = message + getMoreInfo();
        } catch (Exception e) {
            message = message + "异常信息：" + e.getMessage();
        }
        message = message + "计费数组：\n" + lastStrArr + "\n";
        return message;
    }

    public String getMoreInfo() {
        String url = "http://h5.yyy.team007.com/api/member/device-list?tag_id=0&limit=20&sort=room_info&flag=desc";
        String result = HttpRequest.sendGet(url);
        JSONObject json = JSON.parseObject(result);
        JSONObject data = json.getJSONArray("data").getJSONObject(0);
        double yesterday_flow95 = Double.parseDouble(data.getString("yesterday_flow95").split(" ")[0]) * 1000;
        String yesterday_money = data.getString("yesterday_money");
        String today_flow = data.getString("today_flow");
        String month_flow = data.getString("month_flow");

        String message = "昨日收益：" + yesterday_money + "\n";
        message = message + "昨日带宽：" + String.format("%.2f", yesterday_flow95) + "\n";
        message = message + "今日流量：" + today_flow + "\n";
        message = message + "本月流量：" + month_flow + "\n";
        message = message + "奖励收益：" + String.format("%.2f", getPrizeMoney()) + "\n";
        message = message + "全部收益：" + String.format("%.2f", getAllEarnMoney()) + "\n";
        message = message + "支付收益：" + String.format("%.2f", getPayedMoney()) + "\n";
        message = message + "待付收益：" + String.format("%.2f", getAllEarnMoney() - getPayedMoney()) + "\n";

        return message;
    }

    public double getMonthMoney(String day) {
        String url = "http://h5.yyy.team007.com:80/api/tongji/member-money?time=" + day + "&member_id=10052";
        String result = HttpRequest.sendGet(url);
        JSONArray json = JSON.parseObject(result).getJSONArray("month_data");
        double money = 0;
        for (int i = 0; i < json.size(); i++) {
            double dayMoney = json.getJSONObject(i).getDouble("money");
            money += dayMoney;
        }
        return money;
    }

    public double getAllEarnMoney() {
        List<String> days = new ArrayList<>();
        days.add("2023-10-01");
        days.add("2023-11-01");
        days.add("2023-12-01");
        days.add("2024-01-01");
        days.add("2024-02-01");
        days.add("2024-03-01");
        days.add("2024-04-01");
        days.add("2024-05-01");

        double money = 0;
        for (String day : days) {
            money += getMonthMoney(day);
        }

        money += getPrizeMoney();

        return money;
    }

    public double getPayedMoney() {
        String url = "http://h5.yyy.team007.com:80/api/member/info";
        String result = HttpRequest.sendGet(url);
        String money = JSON.parseObject(result).getJSONObject("data").getString("tixian_money");
        return Double.parseDouble(money);
    }

    public double getPrizeMoney() {
        String url = "http://h5.yyy.team007.com:80/api/lottery/prize-list?is_self=1&limit=30";
        String result = HttpRequest.sendGet(url);
        JSONArray list = JSON.parseObject(result).getJSONObject("data").getJSONArray("list");

        double prizeMoney = 0;
        for (int i = 0; i < list.size(); i++) {
            double money = Double.parseDouble(list.getJSONObject(i).getString("prize_money"));
            prizeMoney += money;
        }

        return prizeMoney;
    }
}
