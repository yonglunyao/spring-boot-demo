import com.demo.util.DingTalkRobot;
import com.demo.util.HttpUtil;
import org.assertj.core.util.Lists;
import org.junit.Test;
import java.util.List;


public class DingTalkRobotTest {
    @Test
    public void test() {

        try {
            //钉钉机器人地址（配置机器人的webhook）
            //String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=ca577862751820ec266eb1e055c58f612757c83cab304b6581936fcbaa8098ce";
            String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=afaa8c6514f443b692da66696df45495cc2181ff7fba2231da9262e0aad4bcf5";

            //是否通知所有人
            boolean isAtAll = true;
            //通知具体人的手机号码列表
            List<String> mobileList = Lists.newArrayList();

            //钉钉机器人消息内容
            String content = "testing!!!";
            //组装请求内容
            String reqStr = DingTalkRobot.buildReqStr(content, isAtAll, mobileList);
            System.out.println("reqStr == " + reqStr);

            //推送消息（http请求）
            String result = HttpUtil.postJson(dingUrl, reqStr);


            System.out.println("result == " + result);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
