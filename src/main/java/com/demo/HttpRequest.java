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
            connection.setRequestProperty("cookie", "uuid=uuid~MTU2NTEzNTcyODU=; appid=app_000000035837; uuid=uuid~MTU2NTEzNTcyODU=; appid=app_000000035837; authmethod=authpwd; _ga=GA1.2.238289208.1629045158; _accept_cookie_choose=1|1|1; _ga=GA1.3.238289208.1629045158; _dmpa_id=38790eb8ce90c741d4dee87472331629730911706.1629730944.1.1629730944.1629730944; lang=zh_CN; rxVisitor=1634469114262DQVFQSP3VGKH8GRDDOC3573DL1VD0S8F; hwsso_login=\"\"; suid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; sip=1A-B6-05-7A-44-7A-62-A8-71-67-35-C2-87-DD-BA-24-31-DD-35-52-A5-7C-E0-59; al=10; login_uid=\"\"; login_logFlag=out; uid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; SZXPROITALENTSOCRECRUITMENT=0000y50Rn1wNFOapo-1d9W7C0sc:szxguap1352-mwc_CloneID; dtLatC=4; dtCookie=5$I0ET5RU06DOTUI1O54VCGN4V2F7FQAD6|aa6bd06c3ecde758|1; dtSa=true|C|-1|15651357285|-|1634732437795|332434395_245|https://career.huawei.com/reccampportal/portal5/index.html|华为招聘-找工作-求职-最新招聘信息-华为招聘官方网站|1634732417359|; rxvt=1634734237848|1634732412254; dtPC=5$332434395_245h-vQGOSCTZEHOVTZQHUBDXJLXGWHMGIUNLF; _dmpa_ref=[\"\",\"\",1634734128,\"https://uniportal.huawei.com/\"]; hwssot3=27623570471075; hwsso_uniportal=kV2Uh2PH9sCRLN4VwvxX4Y87f3y336ipby6Ofx43_b8xU4bfjUqrBJ0K2lQrkqSfAo4ivfYUKkErS4_bgRNVTFY8kX84P_aAuyopiUWYSIsg6HokALioGYhmozrgNyZcfweXzwoB8lor4UhjXXDubs4_bI_bwj1MVXXEDTCTw143PJv4AOIjw4Y_bV7a5EdsWLEUKRE6MBHe7e8KeuNQjhNUATuVCMLIyn3VLSyoLAjosJGHXocbbWUCIYqYkrvB_a66cU2zD7iYmY_aJOuoykQi9F7_aj6QNFkwW2jV_bgD0mtS2wvCjXz6gXcGGH97uiaeKs1zXEvjqxD9MDmeINuGpOhe_a6yw_c_c; hwssotinter=22-0B-95-42-A2-2A-F9-4F-B6-B4-B3-5E-B2-B2-08-FF; hwssotinter3=27624514150122; sid=24-A8-DD-42-F6-60-EA-10-68-AB-1C-14-F0-B9-89-A1-6A-D3-D1-B1-C2-F3-5A-1C-F4-34-C9-74-AE-2A-47-66-09-C0-C8-44-53-F2-DE-70; logFlag=in; SZXPROITALENTRECCAMPPORTAL=0000lnGkwS-Fjup_IXLQbdbLusO:szxguap1340-mwc_CloneID; _gid=GA1.3.1439611514.1634859021; _dmpa_ses_time=1634860821466; _dmpa_ses=f0b0573c9498bb99b10908a6452feaf1b7006862");

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