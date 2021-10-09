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
            //connection.setRequestProperty("cookie", "uuid=uuid~MTU2NTEzNTcyODU=; appid=app_000000035837; authmethod=authpwd; _ga=GA1.2.817870491.1627823988; _accept_cookie_choose=1|1|1; _ga=GA1.3.817870491.1627823988; _dmpa_id=171814fb227d54f99492974915471628776526151.1628776576.1.1628776576.1628776576; utag_main=v_id:017bb9f0f6e3001adeaa3971c58f03072003d06a00bd0$_sn:1$_se:1$_ss:1$_st:1630913981988$ses_id:1630912181988;exp-session$_pn:1;exp-session; channel_name=baidu; channel_category=search; _gcl_au=1.1.1650389735.1630912182; __hau=HuaweiConnect.1630912182.1931349572; lang=zh_CN; Hm_lvt_48e5a2ca327922f1ee2bb5ea69bdd0a6=1631875047; language=zh_CN; _dmpa_ref=[\"\",\"\",1633310270,\"https://uniportal.huawei.com/\"]; hwsso_login=\"\"; rxVisitor=1633525225122U1E1HD3OKA1DTD7Q1555A6JVTIKK91IF; SZXPROITALENTSOCRECRUITMENT=0000p7rKfjmBYmydfolkQ1PITRS:szxyap1352-mwc_CloneID; hwsso_uniportal=iSBzalAgmFiYQdcNrjDnD1vYG2j_b43_bkQSw2xcrK3Grkv3O3ahnJBs_bEkrHsRI6I_bIElj5dvQyVwSb_bGKgSFa5wuyFrJXxEGrw0UBj8Uv_bB6NYL35khjfk0FBlFr6YY3fRhJfaIYfT6k_alsCyXH55LawQyyUQsuqGE5SLKelI5Tv0v1aUhDH9jnyIoNLTM82zKs_aL7uX4hqKIftzHfFlsChEirj7MjRCLSlQQ_aH9GTZjk6ik15myTXZepZNeuImjUD_ahjNyM4BR2JoYa_aJUciC_bF6GoH7KU2A73NTeiAKPziTy_b3apBO5N478uVvZGDVZb9_bRSXtB6AQzfnMaEsGPw_c_c; hwssotinter=EF-53-BC-F5-F9-60-AF-9E-7C-65-DF-7E-6F-56-2A-53; hwssotinter3=27612554217273; sid=04-72-86-38-F9-21-4A-F1-16-E2-AD-92-1A-D8-A8-09-59-82-BD-11-06-69-F1-43-3D-06-6E-85-4B-B5-1A-FF-2C-EE-07-3E-06-61-1D-9A; uid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; suid=7D-6A-BC-18-D7-A2-00-0B-D6-B7-16-D6-88-BC-47-27; sip=1A-B6-05-7A-44-7A-62-A8-23-C7-9F-4B-69-4D-78-09-31-DD-35-52-A5-7C-E0-59; logFlag=in; al=10; SZXPROITALENTRECCAMPPORTAL=0000q9rQlmM-nquCb_r6d82bIQT:szxguap1340-mwc_CloneID; dtSa=-; dtLatC=2; _gid=GA1.2.161111994.1633525240; _gat_gtag_UA_7728030_15=1; dtPC=1$325229758_521h-vUXVTKJVJSNOZTIPYZYNYPUVBFVSUHQTP; dtCookie=1$MINRGCHCR8GUU9AUTSGSENH07IKJ4KNE|aa6bd06c3ecde758|1; rxvt=1633527047887|1633525225125");
            connection.setRequestProperty("cookie", "authmethod=authpwd; _ga=GA1.2.817870491.1627823988; _accept_cookie_choose=1|1|1; _ga=GA1.3.817870491.1627823988; _dmpa_id=171814fb227d54f99492974915471628776526151.1628776576.1.1628776576.1628776576; utag_main=v_id:017bb9f0f6e3001adeaa3971c58f03072003d06a00bd0$_sn:1$_se:1$_ss:1$_st:1630913981988$ses_id:1630912181988;exp-session$_pn:1;exp-session; channel_name=baidu; channel_category=search; _gcl_au=1.1.1650389735.1630912182; __hau=HuaweiConnect.1630912182.1931349572; lang=zh_CN; Hm_lvt_48e5a2ca327922f1ee2bb5ea69bdd0a6=1631875047; language=zh_CN; _gid=GA1.2.161111994.1633525240; _gid=GA1.3.161111994.1633525240; _dmpa_ref=[\"\",\"\",1633527135,\"https://uniportal.huawei.com/\"]; hwsso_login=\"\"; hwsso_uniportal=XUTO4g7LytXvPf6XFj4TZh6DCGNg7rRyhK8yuUIa5j8TkY5pg4Zc36_bxnftXXxxh_avCNR2HTbtRrAbabbIJETouI0QbTgsD2AASPUn9w51oPw4_a72ZHM9SoeiYHgB6wmqonLFtWeRrkWqG4TGUcDGc0oGiALyid_bxhLXhczVWpahEmnvwx5c2ALwbkkIDjZ66rEwza_aipAv3wRWp_bBe1_b8V1jo3PBDaup48c9n8Fz97x2jLw_adFkg8yh2QEOm_bUdQDg0NN49p1p0ZUnI8rOzEvaoI7F7dQGYUQGhyN7z4RmLyz6j8lblHfr6Zk8N3Lr3IazcMk0QUfatDltLHZNvCw_c_c; hwssotinter=E8-45-3D-B1-F8-D2-87-2A-54-51-94-4A-51-20-1B-A7; hwssotinter3=27613256006376; sid=A8-2F-EB-63-16-CB-FA-83-60-2B-AC-79-55-54-03-9C-AD-6A-67-32-0A-FE-4D-59-BC-4F-C4-10-F4-99-9A-5A-4A-0D-72-C1-AE-F9-D9-D1; uid=AD-8C-CF-46-46-8F-91-A5-67-86-2F-C5-C4-6D-FD-92; suid=AD-8C-CF-46-46-8F-91-A5-67-86-2F-C5-C4-6D-FD-92; sip=1A-B6-05-7A-44-7A-62-A8-1C-77-FB-D5-A6-B4-82-A0-31-DD-35-52-A5-7C-E0-59; logFlag=in; al=10; SZXPROITALENTRECCAMPPORTAL=0000siDdGa0bgjgNthQDG4EnA73:szxguap1340-mwc_CloneID; _gat_UA-7728030-15=1");

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