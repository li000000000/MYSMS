package com.example.sms.component;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * 发送http请求
 * 类Ajax
 */
public abstract class HttpUtil {

    private volatile String result = null;

    public abstract void handle (String result);

    public HttpUtil(TextView wait, String service, String parameter){
        sendReq_t(wait,service,parameter);
    }

    //异步发送请求并回调处理方法
    private void sendReq_t(TextView wait,String service,String parameter) {

        Thread getData = new Thread(() -> {
            try {
                Thread.sleep(1000);
                result = sendPost(service,parameter,null);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        Thread waitData = new Thread(() -> {
            int count = 0;
            while (result == null) {
                setWaiting(count++, 300, wait);
            }
            handle(result);
        });

        getData.start();
        waitData.start();
    }
    
    //发送http get请求
    private String sendRequest(String service,String urlStr) {
        try {
            
            URL url = new URL(service+urlStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuffer buffer = new StringBuffer();
            String temp = null;

            while ((temp = bufferedReader.readLine()) != null) {
                buffer.append(temp);
            }
            bufferedReader.close();
            reader.close();
            inputStream.close();
            return buffer.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //发送http post请求
    public static String sendPost(String url, String param, Map<String, String> header) throws IOException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(15000);
        if (header!=null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        out = new PrintWriter(conn.getOutputStream());
        out.print(param);
        out.flush();
        in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "utf8"));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        if(out!=null){
            out.close();
        }
        if(in!=null){
            in.close();
        }
        return result;
    }

    private void setWaiting(int count, int sleepTime, TextView wait) {
        int num = count%4;
        StringBuffer sb = new StringBuffer("waiting");
        for (int i = 0; i < num; i++) {
            sb.append(".");
        }
        wait.setText(sb);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
