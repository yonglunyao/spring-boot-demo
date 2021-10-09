package com.demo.controller;

import com.demo.DingTalkRobot;
import com.demo.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;

@Controller
@RequestMapping("/app")
public class HuaweiStatus {
    @RequestMapping("/status")
    @ResponseBody
    public String getStatus() {
        String url = "https://career.huawei.com/reccampportal/services/portal/portaluser/20190918/queryMyJobInterviewEvolve";

        HttpRequest httpRequest = new HttpRequest();

        while (true) {
            String param = "reqTime=";
            long time = Calendar.getInstance().getTimeInMillis();
            param += String.valueOf(time);

            String res = httpRequest.sendGet(url, param);
            int len = res.length();

            if(len<1897){
                DingTalkRobot.sendMessage("可能出问题了，你去看看吧！", false);
            }
            else if (len > 1897) {
                DingTalkRobot.sendMessage("可能发offer啦，快去看看吧！", true);
                System.out.println(len);
            }
            else{
                if (time % 3600000 == 0){
                    DingTalkRobot.sendMessage("还没查到你的offer，耐心等等吧！time: " + time, false);
                }

                DingTalkRobot.sendMessage("还没查到你的offer，耐心等等吧！time: " + time, false);
            }

            try {
                Thread.sleep(10000);
            } catch (Exception e) {
            }

        }

    }
}
