package com.ny.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 *
 */
public class NetWorkHelper {
    @Deprecated
    public String getMessage(String path) {

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "GBK"));
            String line;
            StringBuilder buffer = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            conn.disconnect();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Deprecated
    public String getURLResponse(String urlString) {
        // 连接对象
        HttpURLConnection conn = null;
        InputStream is = null;
        StringBuilder resultData = new StringBuilder();
        try {
            // URL对象
            URL url = new URL(urlString);
            // 使用URL打开一个链接
            conn = (HttpURLConnection) url.openConnection();
            // 允许输入流，即允许下载
            conn.setDoInput(true);

            // 在android中必须将此项设置为false
            // 允许输出流，即允许上传
            conn.setDoOutput(false);
            // 不使用缓冲
            conn.setUseCaches(false);
            // 使用get请求
            conn.setRequestMethod("GET");
            // 获取输入流，此时才真正建立链接
            is = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData.append(inputLine).append("\n");
            }
            System.out.println(resultData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("MalformedURLException:" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException:" + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return resultData.toString();
    }

    String getHttpsResponse(String hsUrl, String requestMethod) {
        URL url;
        InputStream is;
        StringBuilder resultData = new StringBuilder();
        try {
            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = { xtm };

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);
            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier((arg0, arg1) -> true);
            // 允许输入流，即允许下载
            con.setDoInput(true);

            // 在android中必须将此项设置为false
            // 允许输出流，即允许上传
            con.setDoOutput(false);
            // 不使用缓冲
            con.setUseCaches(false);
            if (null != requestMethod && !"".equals(requestMethod)) {
                // 使用指定的方式
                con.setRequestMethod(requestMethod);
            } else {
                // 使用get请求
                con.setRequestMethod("GET");
            }
            // 获取输入流，此时才真正建立链接
            is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData.append(inputLine).append("\n");
            }
            System.out.println(resultData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData.toString();
    }

    /**
     * 下载文件
     * 
     * @param hsUrl String
     * @return String
     */
    @Deprecated
    public String downLoadHttpsFile(String hsUrl, String fileName, String path) {
        URL url;
        InputStream is;
        String filePath = path + fileName;
        try {
            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = { xtm };

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier((arg0, arg1) -> true);
            // 允许输入流，即允许下载
            con.setDoInput(true);
            // 在android中必须将此项设置为false
            // 允许输出流，即允许上传
            con.setDoOutput(false);
            // 不使用缓冲
            con.setUseCaches(false);
            // 使用get请求
            con.setRequestMethod("GET");
            // 获取输入流，此时才真正建立链接
            is = con.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            is.close();
            byte[] fileBytes = outStream.toByteArray();

            File file = new File(filePath);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(fileBytes);
            fops.flush();
            fops.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    /**
     * HTTPS协议的POST请求
     *
     * @param hsUrl 请求地址
     * @param json  请求数据
     * @return String
     */
    @Deprecated
    public String postHttpsResponse(String hsUrl, String json) {
        URL url;
        InputStream is;
        StringBuilder resultData = new StringBuilder();
        try {
            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            TrustManager[] tm = { xtm };

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier((arg0, arg1) -> true);
            // 允许输入流，即允许下载
            con.setDoInput(true);
            // 在android中必须将此项设置为false
            // 允许输出流，即允许上传
            con.setDoOutput(true);
            // 不使用缓冲
            con.setUseCaches(false);
            // 使用get请求
            con.setRequestMethod("POST");
            // 表单数据
            if (null != json) {
                OutputStream outputStream = con.getOutputStream();
                outputStream.write(json.getBytes("UTF-8"));
                outputStream.close();
            }
            // 获取输入流，此时才真正建立链接
            is = con.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                resultData.append(inputLine).append("\n");
            }
            System.out.println(resultData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData.toString();
    }

    private X509TrustManager xtm = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
        }
    };
}