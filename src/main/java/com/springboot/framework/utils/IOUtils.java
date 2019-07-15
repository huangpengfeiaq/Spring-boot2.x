package com.springboot.framework.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/16 0:43
 */
public class IOUtils {
    public IOUtils() {
    }

//    public static String readStreamAsString(InputStream in, String charset) throws IOException {
//        if (in == null) {
//            return "";
//        } else {
//            Reader reader = null;
//            Writer writer = new StringWriter();
//            char[] buffer = new char[1024];
//
//            try {
//                int n = true;
//                reader = new BufferedReader(new InputStreamReader(in, charset));
//
//                int n;
//                while ((n = reader.read(buffer)) != -1) {
//                    writer.write(buffer, 0, n);
//                }
//
//                String result = writer.toString();
//                return result;
//            } finally {
//                in.close();
//                if (reader != null) {
//                    reader.close();
//                }
//
//                if (writer != null) {
//                    writer.close();
//                }
//
//            }
//        }
//    }

    /**
     * 腾讯云（输入流转ByteArray）
     */
    public static byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            byte[] b = new byte[4096];
            boolean var3 = false;

            int n;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }

            byte[] var4 = output.toByteArray();
            return var4;
        } finally {
            output.close();
        }
    }

    /**
     * 阿里云（输入流转ByteArray）
     */
    public static byte[] readStreamAsByteArray(InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        } else {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            boolean var3 = true;

            int len;
            while ((len = in.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }

            output.flush();
            return output.toByteArray();
        }
    }

    public static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException var2) {
                ;
            }
        }

    }

    public static void safeClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException var2) {
                ;
            }
        }

    }

    public static boolean checkFile(File file) {
        if (file == null) {
            return false;
        } else {
            boolean exists = false;
            boolean isFile = false;
            boolean canRead = false;

            try {
                exists = file.exists();
                isFile = file.isFile();
                canRead = file.canRead();
            } catch (SecurityException var5) {
                return false;
            }

            return exists && isFile && canRead;
        }
    }
}
