package com.demo;

import com.alibaba.fastjson.JSON;
import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yonglunyao
 */
public class DingTalkRobot {

    private final static String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=afaa8c6514f443b692da66696df45495cc2181ff7fba2231da9262e0aad4bcf5";

    public static void sendMessage(String msg, boolean isAtAll) {
        try {
            //钉钉机器人地址（配置机器人的webhook）
            DingTalkRobot dingTalkRobot = new DingTalkRobot();


            //通知具体人的手机号码列表
            List<String> mobileList = Lists.newArrayList();

            //钉钉机器人消息内容
            String content = msg;
            //组装请求内容
            String reqStr = buildReqStr(content, isAtAll, mobileList);

            //推送消息（http请求）
            String result = HttpUtil.postJson(dingUrl, reqStr);


            System.out.println("result == " + result);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    /**
     * 组装请求报文
     *
     * @param content
     * @return
     */
    public static String buildReqStr(String content, boolean isAtAll, List<String> mobileList) {
        String tag = "【通知】\n";

        //消息内容
        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", tag + content + "\n");

        //通知人
        Map<String, Object> atMap = new HashMap<>();
        //1.是否通知所有人
        atMap.put("isAtAll", isAtAll);
        //2.通知具体人的手机号码列表
        atMap.put("atMobiles", mobileList);

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);

        return JSON.toJSONString(reqMap);
    }
}
