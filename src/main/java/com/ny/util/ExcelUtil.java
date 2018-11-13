package com.ny.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
public class ExcelUtil {
    public static void export(int currRow, OutputStream os, Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        ArrayList<Map<String, Object>> arrayList = (ArrayList<Map<String, Object>>) map.get("result");
        List<Map<String, Object>> list = arrayList;
        // 一个sheet页最大行数，此处设置成65000主要是为了兼容excel2003及以下版本
        int recordcount = 65000;
        // 需要导出数据的总行数
        int total = list.size() + 2;
        int col = 7;
        try {
            // 第一次写入数据需要写入excel设置信息
            if (currRow == 0) {
                sb.append("<?xml version=\"1.0\"?>");
                sb.append("\n");
                sb.append("<?mso-application progid=\"Excel.Sheet\"?>");
                sb.append("\n");
                sb.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
                sb.append("\n");
                sb.append("  xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
                sb.append("\n");
                sb.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
                sb.append("\n");
                sb.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
                sb.append("\n");
                sb.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
                sb.append("\n");
                sb.append(" <Styles>\n");
                sb.append("  <Style ss:ID=\"Default\" ss:Name=\"Normal\">\n");
                sb.append("   <Alignment ss:Vertical=\"Center\"/>\n");
                sb.append("   <Borders/>\n");
                sb.append("   <Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\"/>\n");
                sb.append("   <Interior/>\n");
                sb.append("   <NumberFormat/>\n");
                sb.append("   <Protection/>\n");
                sb.append("  </Style>\n");
                sb.append(" </Styles>\n");
                sb.append("<Worksheet ss:Name=\"Sheet1\">");
                sb.append("\n");
                sb.append("<Table x:FullColumns=\"1\" x:FullRows=\"1\">");
                sb.append("\n");
            } else {
                currRow = currRow + 2;
                total = total - 2;
            }
            int listIndex = 0;
            for (int i = currRow; i < (currRow + total); i++) {
                // 一个sheet写满
                if (i != 0 && i % recordcount == 0) {
                    os.write(sb.toString().getBytes());
                    os.flush();
                    sb.setLength(0);
                    sb.append("</Table>");
                    sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
                    sb.append("\n");
                    sb.append("<ProtectObjects>False</ProtectObjects>");
                    sb.append("\n");
                    sb.append("<ProtectScenarios>False</ProtectScenarios>");
                    sb.append("\n");
                    sb.append("</WorksheetOptions>");
                    sb.append("\n");
                    sb.append("</Worksheet>");
                    sb.append("<Worksheet ss:Name=\"Sheet").append(i / recordcount + 1).append("\">");
                    sb.append("\n");
                    sb.append("<Table x:FullColumns=\"1\" x:FullRows=\"1\">");
                    sb.append("\n");
                }
                sb.append("<Row>");
                for (int j = 0; j < col; j++) {
                    if (i == 0) {
                        sb.append("<Cell><Data ss:Type=\"String\">总行数：" + map.get("total") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">文章总字数：" + map.get("sumValue") + "</Data></Cell>");
                        sb.append("\n");
                        break;
                    } else if (i == 1) {
                        // "站点", "栏目", "网址", "标题", "责任编辑", "作者", "来源", "发布时间"
                        sb.append("<Cell><Data ss:Type=\"String\">站点</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">栏目</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">网址</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">标题</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">责任编辑</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">作者</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">来源</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">发布时间</Data></Cell>");
                        sb.append("\n");
                        break;
                    } else {
                        Map map1 = list.get(listIndex);
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("site") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("column") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("id") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("title") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("editor") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("author") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("source") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("publishTime") + "</Data></Cell>");
                        sb.append("\n");
                        listIndex++;
                        break;
                    }
                }
                sb.append("</Row>");
                sb.append("\n");
            }
            // 注意编码
            os.write(sb.toString().getBytes("utf-8"));
            os.flush();
            sb.setLength(0);
            // 最后一个sheet页的尾部以及整个book的尾部
            String totalStr = "total";
            if ((currRow + total) >= Integer.parseInt(map.get(totalStr).toString())) {
                sb.append("</Table>");
                sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
                sb.append("\n");
                sb.append("<ProtectObjects>False</ProtectObjects>");
                sb.append("\n");
                sb.append("<ProtectScenarios>False</ProtectScenarios>");
                sb.append("\n");
                sb.append("</WorksheetOptions>");
                sb.append("\n");
                sb.append("</Worksheet>");
                sb.append("</Workbook>");
                sb.append("\n");
                os.write(sb.toString().getBytes());
                os.flush();
                sb.setLength(0);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void export2(OutputStream os, List<Map<String, Object>> lists, String flag) throws Exception {
        StringBuffer sb = new StringBuffer();
        // 一个sheet页最大行数，此处设置成65000主要是为了兼容excel2003及以下版本
        int recordcount = 65000;
        // 需要导出数据的总行数
        int total = lists.size() + 1;
        try {
            sb.append("<?xml version=\"1.0\"?>");
            sb.append("\n");
            sb.append("<?mso-application progid=\"Excel.Sheet\"?>");
            sb.append("\n");
            sb.append("<Workbook xmlns=\"urn:schemas-microsoft-com:office:spreadsheet\"");
            sb.append("\n");
            sb.append("  xmlns:o=\"urn:schemas-microsoft-com:office:office\"");
            sb.append("\n");
            sb.append(" xmlns:x=\"urn:schemas-microsoft-com:office:excel\"");
            sb.append("\n");
            sb.append(" xmlns:ss=\"urn:schemas-microsoft-com:office:spreadsheet\"");
            sb.append("\n");
            sb.append(" xmlns:html=\"http://www.w3.org/TR/REC-html40\">");
            sb.append("\n");
            sb.append(" <Styles>\n");
            sb.append("  <Style ss:ID=\"Default\" ss:Name=\"Normal\">\n");
            sb.append("   <Alignment ss:Vertical=\"Center\"/>\n");
            sb.append("   <Borders/>\n");
            sb.append("   <Font ss:FontName=\"宋体\" x:CharSet=\"134\" ss:Size=\"12\"/>\n");
            sb.append("   <Interior/>\n");
            sb.append("   <NumberFormat/>\n");
            sb.append("   <Protection/>\n");
            sb.append("  </Style>\n");
            sb.append(" </Styles>\n");
            sb.append("<Worksheet ss:Name=\"Sheet1\">");
            sb.append("\n");
            sb.append("<Table x:FullColumns=\"1\" x:FullRows=\"1\">");
            sb.append("\n");

            for (int i = 0; i < total; i++) {
                // 一个sheet写满
                if (i != 0 && i % recordcount == 0) {
                    os.write(sb.toString().getBytes());
                    os.flush();
                    sb.setLength(0);
                    sb.append("</Table>");
                    sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
                    sb.append("\n");
                    sb.append("<ProtectObjects>False</ProtectObjects>");
                    sb.append("\n");
                    sb.append("<ProtectScenarios>False</ProtectScenarios>");
                    sb.append("\n");
                    sb.append("</WorksheetOptions>");
                    sb.append("\n");
                    sb.append("</Worksheet>");
                    sb.append("<Worksheet ss:Name=\"Sheet" + (i / recordcount + 1) + "\">");
                    sb.append("\n");
                    sb.append("<Table x:FullColumns=\"1\" x:FullRows=\"1\">");
                    sb.append("\n");
                }
                sb.append("<Row>");
                if (i == 0) {
                    if ("site".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">站点</Data></Cell>");
                    } else if ("column".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">站点</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">栏目</Data></Cell>");
                    } else if ("author".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">作者</Data></Cell>");
                    } else if ("editor".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">责任编辑</Data></Cell>");
                    } else if ("source".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">来源</Data></Cell>");
                    }
                    sb.append("<Cell><Data ss:Type=\"String\">文章篇数</Data></Cell>");
                    sb.append("<Cell><Data ss:Type=\"String\">文章字数</Data></Cell>");
                } else {
                    Map map1 = lists.get(i - 1);
                    if ("site".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("site") + "</Data></Cell>");
                    } else if ("column".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("site") + "</Data></Cell>");
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("column") + "</Data></Cell>");
                    } else if ("author".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("author") + "</Data></Cell>");
                    } else if ("editor".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("editor") + "</Data></Cell>");
                    } else if ("source".equals(flag)) {
                        sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("source") + "</Data></Cell>");
                    }
                    sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("total") + "</Data></Cell>");
                    sb.append("<Cell><Data ss:Type=\"String\">" + map1.get("sum") + "</Data></Cell>");
                }
                sb.append("\n");
                sb.append("</Row>");
                if (i != 0 && i % 5000 == 0) {
                    // 注意编码
                    os.write(sb.toString().getBytes("utf-8"));
                    os.flush();
                    sb.setLength(0);
                }
                sb.append("\n");
            }
            // 注意编码
            os.write(sb.toString().getBytes("utf-8"));
            sb.setLength(0);
            sb.append("</Table>");
            sb.append("<WorksheetOptions xmlns=\"urn:schemas-microsoft-com:office:excel\">");
            sb.append("\n");
            sb.append("<ProtectObjects>False</ProtectObjects>");
            sb.append("\n");
            sb.append("<ProtectScenarios>False</ProtectScenarios>");
            sb.append("\n");
            sb.append("</WorksheetOptions>");
            sb.append("\n");
            sb.append("</Worksheet>");
            sb.append("</Workbook>");
            sb.append("\n");
            os.write(sb.toString().getBytes());
            os.flush();
            sb.setLength(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
