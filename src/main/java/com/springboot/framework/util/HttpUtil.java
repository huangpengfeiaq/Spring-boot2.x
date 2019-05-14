package com.springboot.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.springboot.framework.constant.Errors;
import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 */
public class HttpUtil {

  private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

  private static Charset chartset = Charset.forName(CharEncoding.UTF_8);

  /**
   * 获取请求客户端的IP地址
   * 
   * @param request
   * @return
   */
  public String getClientIp(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    // 判断是否为IPV6地址
    if (ip != null && ip.indexOf(":") >= 0) {
      ip = "127.0.0.1";
    }
    logger.debug("获取客户端浏览器ip地址=" + ip);
    return ip;
  }

  /**
   * <pre>
   * post发送json数据请求
   * 设置了Content-Type=application/json
   * </pre>
   * 
   * @param url：请求路径
   * @param jsonFormString：json格式的字符串
   * @return
   * @throws Exception
   */
  public static JSONObject doPostJson(String url, String jsonFormString) {
    // logger.info("doPostJson请求内容：{}", jsonFormString);

    JSONObject response = null;
    try {
      HttpClient client = HttpClients.createDefault();
     
      HttpPost post = new HttpPost(url);
      post.addHeader("text/plain", CharEncoding.UTF_8);
      post.addHeader("Content-Type", "application/json");
      
      StringEntity s = new StringEntity(jsonFormString, chartset);
      post.setEntity(s);
      HttpResponse httpResponse = client.execute(post);

      if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String result = EntityUtils.toString(httpResponse.getEntity(), chartset);
        response = JSONObject.parseObject(result);
      } else {
        String errorMsg = EntityUtils.toString(httpResponse.getEntity(), chartset);
        throw new RuntimeException(errorMsg);
      }
      if (response != null) {
        logger.info("doPostJson响应结果：{}", response.toJSONString());
      }
    } catch (Exception e) {
      e.printStackTrace();
      ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, e.getMessage());
    }

    return response;
  }

  /**
   * 读取内容
   * 
   * @param request
   * @return
   */
  public static String readString(HttpServletRequest request) {
    try {
      StringBuffer out = new StringBuffer();
      InputStream in = request.getInputStream();
      byte[] b = new byte[4096];
      for (int n; (n = in.read(b)) != -1;) {
        out.append(new String(b, 0, n, chartset));
      }
      return out.toString();
    } catch (Exception e) {
      logger.info("", e);
    }
    return "";
  }

  /**
   * 执行一个HTTP GET请求，返回请求响应的string
   * 
   * @param url 请求的URL地址
   * @return 返回请求响应的HTML
   * @throws IOException
   * @throws ClientProtocolException
   */
  public static String doGet(String url) {

    String response = null;
    try {
      HttpClient client = HttpClients.createDefault();
      HttpGet method = new HttpGet(url);
      HttpResponse httpResponse = client.execute(method);
      if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        response = EntityUtils.toString(httpResponse.getEntity(), chartset);
        logger.info("doGet响应结果：{}", response);
      } else {
        String errorMsg = EntityUtils.toString(httpResponse.getEntity(), chartset);
        throw new RuntimeException(errorMsg);
      }
    } catch (Exception e) {
      e.printStackTrace();
      ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, e.getMessage());
    }
    return response;
  }
}
