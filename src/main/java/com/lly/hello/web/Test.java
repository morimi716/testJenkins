package com.lly.hello.web;

/**
 * @author Louly
 * @Date 2019/6/11 15:54
 * @Version 1.0.0
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    // 准备一个队列，保存所有等待爬取的连接地址
    // List集合类来模拟队列的处理，添加的连接都放入到最后一个，所以提取连接都从第一个开始提取。
    private static List<String> allWaitUrls = new ArrayList<String>();

    // 使用Set集合保存所有已经处理过的连接地址，该集合类型不允许重复，所以适合完成这个工作
    private static Set<String> allOverUrls = new HashSet<String>();

    public static void main(String[] args) throws Exception {
        String seedUrl = "http://mp.weixin.qq.com/mp/homepage?__biz=MzU2MjY3NTkxMA==&hid=2&sn=8e1de53239695d261a6e197548d0af2f&scene=18#wechat_redirect";
        getUrlData(seedUrl);

    }

    public static void getUrlData(String urlStr) throws Exception {
        try {
            // 先判断连接是否被处理过
            if (!allOverUrls.contains(urlStr)) {

                // 建立与网页的连接
                URL url = new URL(urlStr);
                URLConnection conn = url.openConnection();
                // 读取输入的数据内容
                InputStream is = conn.getInputStream();
                // 转换为缓冲字符流
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 逐行读取数据
                String line = null;
                // 可变字符串类，类似StringBuffer
                StringBuilder builder = new StringBuilder();

                String desc = null;

                // 准备提取超链接的匹配规则 和 相关类
                String hrefRegex = "<a .*href=.*</a>";
                Pattern hrefP = Pattern.compile(hrefRegex);

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    // 提取描述信息
//                    String descRegex = "<meta .*name=\"[dD][eE][sS][cC][rR][iI][pP][tT][iI][oO][nN]\".*>";
//                    Pattern descP = Pattern.compile(descRegex);
//                    Matcher descM = descP.matcher(line);
//
//                    if (descM.find()) {
//                        desc = descM.group();
//                        desc = desc.substring(desc.indexOf("content=") + 9);
//                        desc = desc.substring(0, desc.indexOf("\""));
//                        // System.out.println(desc);
//                    }
//                    // 匹配超链接的格式
//                    Matcher hrefM = hrefP.matcher(line);
//                    while (hrefM.find()) {
//                        String href = hrefM.group();
//                        href = href.substring(href.indexOf("href=") + 6);
//                        href = href.substring(0, href.indexOf("\"")).trim();
//                        // 判断连接地址是否以http开头，如果是，才表示为有效连接
//                        if (href.startsWith("http:")) {
//                            // 将连接加入到队列中
//                            allWaitUrls.add(href);
//                        }
//                    }
                }

                reader.close();
                // 提取有效的数据
//                String titleRegex = "<title>.*</title>";
//                Pattern titleP = Pattern.compile(titleRegex);
//                Matcher titleM = titleP.matcher(builder.toString());
                // 取得标题的文字内容
//                String title = null;
                // 判断是否有满足条件的内容
//                if (titleM.find()) {
//                    title = titleM.group();
//                    title = title.substring(7);
//                    title = title.substring(0, title.indexOf("</title>"));
//                    // System.out.println(title);
//                }


                System.out.println(builder.toString());
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }

    }

}

