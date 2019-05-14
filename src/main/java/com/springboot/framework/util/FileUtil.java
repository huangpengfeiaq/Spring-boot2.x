package com.springboot.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * 文件工具类
 */
public class FileUtil {
  private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

  private static String chartset = "UTF-8";

  private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

  /**
   * 获取文件分隔符
   */
  public static String getFileSeparator() {
    return File.separator;
  }

  public static byte[] getBytes(String path) throws Exception {
    InputStream is = new FileInputStream(new File(path));
    byte[] iconBytes = new byte[is.available()];
    is.read(iconBytes);
    is.close();
    return iconBytes;
  }

  /**
   * 读取内容
   */
  public static String readString(InputStream in) {
    if (in == null) {
      return "";
    }

    StringBuffer out = new StringBuffer();
    byte[] b = new byte[4096];
    try {
      for (int n; (n = in.read(b)) != -1;) {
        out.append(new String(b, 0, n, chartset));
      }
    } catch (Exception e) {
      logger.info("", e);
    }
    return out.toString();
  }

  public static String toString(Reader input) throws IOException {
    StringWriter sw = new StringWriter();
    copy(input, sw);
    return sw.toString();
  }

  private static int copy(Reader input, Writer output) throws IOException {
    long count = copyLarge(input, output);
    if (count > Integer.MAX_VALUE) {
      return -1;
    }
    return (int) count;
  }

  private static long copyLarge(Reader input, Writer output) throws IOException {
    char[] buffer = new char[DEFAULT_BUFFER_SIZE];
    long count = 0;
    int n = 0;
    while (-1 != (n = input.read(buffer))) {
      output.write(buffer, 0, n);
      count += n;
    }
    return count;
  }

  /**
   * 加载配置oss文件
   */
  public static Map<String, String> loadFileProperties(String filePath) throws IOException {
    if (null == filePath || "".equals(filePath.trim())) {
      System.out.println("The file path is null,return");
      return null;
    }
    filePath = filePath.trim();
    // 获取资源文件
    InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(filePath);
    // 从输入流中读取属性列表
    Properties prop = new Properties();
    prop.load(is);
    // 返回Properties中包含的key-value的Set视图
    Set<Entry<Object, Object>> set = prop.entrySet();
    // 返回在此Set中的元素上进行迭代的迭代器
    Iterator<Entry<Object, Object>> it = set.iterator();
    String key = null, value = null;
    Map<String, String> map = new HashMap<String, String>();
    // 循环取出key-value
    while (it.hasNext()) {
      Entry<Object, Object> entry = it.next();
      key = String.valueOf(entry.getKey());
      value = String.valueOf(entry.getValue());
      // 将key-value放入map中
      map.put(key, value);
    }
    return map;
  }

 static String imgNames = "bmp,dib,gif,jfif,jpe,jpeg,jpg,png,tif,tiff,ico";
  /**
   * 判定是否为图片
   * bmp,dib,gif,jfif,jpe,jpeg,jpg,png,tif,tiff,ico
   * @param suffixName 后缀名
   * @return true:是，false:不是图片
   */
  public static boolean isImg(String suffixName){
  	return imgNames.contains(suffixName);
  }	
}
