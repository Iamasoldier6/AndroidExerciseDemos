package com.iamasoldier6.tulingrobotdemo.util;

import com.google.gson.Gson;
import com.iamasoldier6.tulingrobotdemo.bean.ChatMessage;
import com.iamasoldier6.tulingrobotdemo.bean.CommonException;
import com.iamasoldier6.tulingrobotdemo.bean.Result;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;


/**
 * @author：Iamasoldier6
 * @date: 2016/9/12
 * <p>
 * 用于访问 API 的工具类
 */

public class HttpUtil {

    private static String API_KEY = "9704d842f36f40549264fdca5ec84bb2";
    private static String URL = "http://www.tuling123.com/openapi/api";

    /**
     * 发送消息，得到返回消息
     */
    public static ChatMessage sendMessage(String msg) {
        ChatMessage message = new ChatMessage();
        String url = setParams(msg);
        String res = doGet(url);
        Gson gson = new Gson();
        Result result = gson.fromJson(res, Result.class);

        if (result.getCode() > 400000 || result.getText() == null ||
                result.getText().trim().equals("")) {
            message.setMessage("该动能等待开发...");
        } else {
            message.setMessage(result.getText());
        }
        message.setType(ChatMessage.Type.INPUT);
        message.setDate(new Date());

        return message;
    }

    /**
     * 拼接 URL
     */
    private static String setParams(String message) {
        try {
            message = URLEncoder.encode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return URL + "?key=" + API_KEY + "&info=" + message;
    }

    /**
     * Get 请求，获得返回数据
     */
    private static String doGet(String urlStr) {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5 * 1000);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1; // 用来保存实际读到的字节数
                byte[] buf = new byte[128]; // 创建一个 128 个字节大小的缓冲区，用来存放输入流中的字节

                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len); // 将指定 byte 数组中从偏移量 0 开始的 len 个字节写入该文件输出流
                }
                baos.flush(); // 更新缓存中的数据到文件
                return baos.toString();
            } else {
                throw new CommonException("服务器连接错误！ ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException("服务器连接错误！ ");
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            conn.disconnect();
        }
    }
}
