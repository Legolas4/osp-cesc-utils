package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;


/**
 * @author wangxin
 * @version 2018/8/13 9:50
 * @description
 */
public class HttpUtil {

    public static byte[] doPostJson(String url, Object object) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
		byte[] resultBytes = new byte[100000];
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity stringEntity = new StringEntity(object.toString(), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            // 执行http请求
            response = httpClient.execute(httpPost);
			resultBytes = EntityUtils.toByteArray(response.getEntity());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultBytes;
    }

    /**
     * 用于调用es接口查询信息
     *
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPostJson(String url, JSONObject json) {
        // 创建Httpclient对象
        JSONObject resultJsonObject = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            resultJsonObject = JSON.parseObject(result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultJsonObject;
    }
}
