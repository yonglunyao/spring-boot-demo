import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.util.HttpRequest;
import org.junit.Test;

public class HttpTest {
    @Test
    public void test() {
        String url = "http://h5.yyy.team007.com/api/member/device-list?tag_id=0&limit=20&sort=room_info&flag=desc";
        String result = HttpRequest.sendGet(url);
        JSONObject json = JSON.parseObject(result);
        JSONObject data = json.getJSONArray("data").getJSONObject(0);
        String yesterday_flow95 = data.getString("yesterday_flow95");
        String yesterday_money = data.getString("yesterday_money");
        String today_flow = data.getString("today_flow");
        String month_flow = data.getString("month_flow");
        return;
    }
}
