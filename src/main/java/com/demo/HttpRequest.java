package com.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author yonglunyao
 */
public class HttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @return URL 所代表远程资源的响应结果
     */
    public String sendGet(String url) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
//            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//            connection.setRequestProperty("connection", "Keep-Alive");
//            connection.setRequestProperty("cookie", "uuid=uuid~MTU2NTEzNTcyODU=; appid=app_000000035837; authmethod=authpwd; _ga=GA1.2.817870491.1627823988; _accept_cookie_choose=1|1|1; _ga=GA1.3.817870491.1627823988; _dmpa_id=171814fb227d54f99492974915471628776526151.1628776576.1.1628776576.1628776576; utag_main=v_id:017bb9f0f6e3001adeaa3971c58f03072003d06a00bd0$_sn:1$_se:1$_ss:1$_st:1630913981988$ses_id:1630912181988;exp-session$_pn:1;exp-session; channel_category=search; channel_name=baidu; _gcl_au=1.1.1650389735.1630912182; __hau=HuaweiConnect.1630912182.1931349572; lang=zh_CN; Hm_lvt_48e5a2ca327922f1ee2bb5ea69bdd0a6=1631875047; _dmpa_ref=[\"\",\"\",1635230851,\"https://uniportal.huawei.com/\"]; _gid=GA1.2.537622052.1635579609; rxVisitor=1635641419117QK7PPQCC2PBUC9L5VMSPOJNE2MKUPDLH; hwsso_login=\"\"; SZXPROITALENTSOCRECRUITMENT=0000fw6swU8KUTgksqQ2wAgq4og:szxgvap1352-mwc_CloneID; hwsso_uniportal=3PCyOsYrX9hvkvHyejnQKLmo6NDugYK1bgqpeVzNMId2lHg0xWFUqA_aqwojrmwZcg8xuspm_arR7wL09Qgg6kHWqSh1_a0ggKZLXN5aOt3c4_aZ71J4tTlPDL3U09whOHFFZpPYXuB2FzoH7dRj5gYldbYURY5LNgBGsOQZXVHRsb_b5SnEORC8oGq7egU9qwuIYfQkrVgqz4_aqkmh0UHQs_bG7UtyabbX86xy5bN5aJA2XD8ndqLBXA4JWai5bjtof6Xe7bExOjNYN9_bqYpgS3Tdn5m7w1veFgdiQHUWolX_aFA5Wc5AMWJrDQqqB6a7E6isM44wDubPvTFCQ2f3MYrzeaw_c_c; hwssotinter=00-95-BC-D6-E1-C5-33-66-50-A9-91-22-1D-2C-19-BD; hwssotinter3=27632364712713; sid=C8-74-25-A8-D2-00-6C-2C-22-6D-09-88-BC-7C-E3-C6-DC-56-5E-FA-27-30-6B-97-73-6A-6E-68-3C-07-EB-D8-32-39-4D-95-FE-9E-54-BC; uid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; suid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; sip=7E-1A-97-8E-3D-46-19-E3-29-1A-B9-E5-14-99-AC-F9-31-DD-35-52-A5-7C-E0-59; logFlag=in; al=10; SZXPROITALENTRECCAMPPORTAL=0000J6TSAL5a20v9ZetGVZC3hr_:szxgvap1340-mwc_CloneID; dtLatC=2; dtCookie=5$LDQTQ1FOKOBL2UPJ0R1AQJ414D4IEJIO|aa6bd06c3ecde758|1; dtSa=true|C|-1|icon-avatar-50|-|1635641723473|41721169_700|https://career.huawei.com/reccampportal/portal5/index.html|华为招聘-找工作-求职-最新招聘信息-华为招聘官方网站|1635641427778|; rxvt=1635643523527|1635641419118; dtPC=5$41721169_700h-vVKUTZFPTSOLVKWYRFWFIXTNAFUFXOXXT");
//
//            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
//            connection.setRequestProperty("user-agent",
//                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
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
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
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
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}