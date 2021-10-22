package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * @author yonglunyao
 */
public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("cookie", "uuid=uuid~MTU2NTEzNTcyODU=; appid=app_000000035837; authmethod=authpwd; _ga=GA1.2.817870491.1627823988; _accept_cookie_choose=1|1|1; _ga=GA1.3.817870491.1627823988; _dmpa_id=171814fb227d54f99492974915471628776526151.1628776576.1.1628776576.1628776576; utag_main=v_id:017bb9f0f6e3001adeaa3971c58f03072003d06a00bd0$_sn:1$_se:1$_ss:1$_st:1630913981988$ses_id:1630912181988;exp-session$_pn:1;exp-session; channel_category=search; channel_name=baidu; _gcl_au=1.1.1650389735.1630912182; __hau=HuaweiConnect.1630912182.1931349572; lang=zh_CN; Hm_lvt_48e5a2ca327922f1ee2bb5ea69bdd0a6=1631875047; language=zh_CN; _gid=GA1.2.787725868.1634526625; _dmpa_ref=[\"\",\"\",1634712647,\"https://uniportal.huawei.com/\"]; rxVisitor=1634865594727LMVAA4PQ85GOG4T5HIGV5HQNS0TKONV4; hwsso_login=\"\"; SZXPROITALENTSOCRECRUITMENT=00008E5quaDV9YaVHE6HB60F2jK:szxguap1352-mwc_CloneID; hwsso_uniportal=phacOWO3l27qI2_bMIjmvrycCVPzoibFXN1smMvjvuu_bbdWG7OvkPsdfoxzZxR0lKuhXU_bLXP_aQ0v4gPSMYBXlpmloXbsH6gIzUnZQSGyakSJIO0vPwca7JAqu9fIU1cIM_aaYVXDlzyNC88I_aflU0q4lzAnLJN3bQrQiyki8s9L7JWYLP4DB_bTPLxuFD_a_a09uI4pRXwKO3qnMju4jj7pW2p9ia5lX0JO_aZ70_bUNCukI4a58y1EjOaaGuwCW9R8b_aeutsTwaLOlCAccx8HOEMRC45BZhqaS8_aT0gY39xpFpxuRUMsGwZAeC2cdJoniM6rqkeAPNevUuXiQWig4kZk_bDg_c_c; hwssotinter=71-40-41-0A-2E-2C-6B-8C-4A-AC-F4-C1-27-36-8E-11; hwssotinter3=27624545261453; sid=32-A9-10-8F-06-2D-5B-6F-09-9C-60-3E-21-8F-40-A9-68-A0-AB-92-D2-0A-C7-1C-F8-B6-73-31-93-D6-D1-68-A1-0B-66-B8-DF-3C-74-02; uid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; suid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; sip=1A-B6-05-7A-44-7A-62-A8-48-6B-AD-8E-C8-E8-04-24-31-DD-35-52-A5-7C-E0-59; logFlag=in; al=10; SZXPROITALENTRECCAMPPORTAL=0000yLYCK9yNi-STVruqMgo2R87:szxgvap1340-mwc_CloneID; dtLatC=2; dtCookie=7$34IFUBDH5II34A8JKA825FH1NRGCGV5L|aa6bd06c3ecde758|1; dtSa=true|C|-1|15651357285|-|1634865602035|465599731_134|https://career.huawei.com/reccampportal/portal5/index.html|华为招聘-找工作-求职-最新招聘信息-华为招聘官方网站|1634865594719|; rxvt=1634867402102|1634865594729; dtPC=7$465599731_134h-vCCSKFMJVMVWBMIORXROHAXUGUCONMUNS; _gat_gtag_UA_7728030_15=1");

            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                //System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}