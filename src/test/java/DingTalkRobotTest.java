import com.demo.DingTalkRobot;
import com.demo.HttpUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;
import java.util.List;


public class DingTalkRobotTest {
    @Test
    public void test() {

        try {
            //钉钉机器人地址（配置机器人的webhook）
            //String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=ca577862751820ec266eb1e055c58f612757c83cab304b6581936fcbaa8098ce";
            String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=a7b2c5b405d6993dce21785794fa76af7c4abf1583fb2aaf2f130a184edccf96";

            DingTalkRobot dingTalkRobot = new DingTalkRobot();
            //是否通知所有人
            boolean isAtAll = false;
            //通知具体人的手机号码列表
            List<String> mobileList = Lists.newArrayList();

            //钉钉机器人消息内容
            String content = "testing!!!";
            //组装请求内容
            String reqStr = dingTalkRobot.buildReqStr(content, isAtAll, mobileList);

            //推送消息（http请求）
            String result = HttpUtil.postJson(dingUrl, reqStr);


            System.out.println("result == " + result);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
