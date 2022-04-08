package com.fengx.mytest.base.regular;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式相关
 */
public class Test {

    /**
     * 定义script的正则表达式
     */
    private static final String REG_EX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    private static final String REG_EX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REG_EX_HTML = "<[^>]+>";
    /**
     * 定义空格回车换行符
     */
    private static final String REG_EX_SPACE = "\\s*|\t|\r|\n";
    /**
     * 定义所有w标签
     */
    private static final String REG_EXW = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";

    public static void main(String[] args) throws IOException {
//        Map<String, List<Integer>> map = new HashMap<>();
//        map.put("1", new ArrayList<Integer>(){{add(1); add(2); add(3);}});
//        map.put("2", new ArrayList<Integer>(){{add(4); add(5); add(6);}});
//        map.put("3", new ArrayList<Integer>(){{add(9); add(8); add(77);}});
//
//
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(new ArrayList<Integer>(){{add(1); add(2); add(3);}});
//        list.add(new ArrayList<Integer>(){{add(1); add(2); add(3);}});
//        long count = list.stream()
//                .flatMap(Collection::stream).mapToInt(Integer::intValue).sum();
//        System.out.println(count);
        String text = "var htmlStr='<p class=\"cjk\" style=\"margin-bottom: 0cm; line-height: 16px;\">关于融托优选<font face=\"Calibri, sans-serif\"><span>APP</span></font>正式发布的通知！</p><p class=\"cjk\" style=\"margin-bottom: 0cm; line-height: 16px;\"><br/></p><p class=\"cjk\" style=\"margin-bottom: 0cm; line-height: 16px;\">尊敬的用户：</p><p class=\"cjk\" style=\"margin-bottom: 0cm; line-height: 16px;\"><br/></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\">融托优选<font face=\"Calibri, sans-serif\"><span>APP</span></font>于<font face=\"Calibri, sans-serif\"><span>2019</span></font>年<font face=\"Calibri, sans-serif\"><span>1</span></font>月<font face=\"Calibri, sans-serif\"><span>12</span></font>日正式上线发布，欢迎各位下载安装。</p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"><br/></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\">融托优选，是一款综合类的第三方入住电子商务平台，集购物、旅游、娱乐、金融理财于一身，平台致力于帮用户排除不好的产品，让用户更轻松的选择商品；平台会对入住的第三方产品进行审核，符合标准的才允许上线。</p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"><br/></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\">秉承着让用户选择商品变轻松的原则，在产品类型、商品质量、用户体验等各个环节严格把控，力求能够帮助用户更便捷的提高生活品质。</p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"><br/></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"><br/></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"><span>详情请咨询</span><font face=\"Calibri, sans-serif\"><span><span>400-809-1991</span></span></font><span>，网址：</span><font face=\"Calibri, sans-serif\"><span><font color=\"#0000ff\"><u><a href=\"http://www.xiantaobx.com./\"><span>www.xiantaobx.com.</span></a></u></font></span></font></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"><br/></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"></p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\">融托优选运营团队</p><p class=\"cjk\" style=\"margin-bottom: 0cm; text-indent: 0.74cm; line-height: 16px;\"><font face=\"Calibri, sans-serif\"><span>2019</span></font>年<font face=\"Calibri, sans-serif\"><span>1</span></font>月<font face=\"Calibri, sans-serif\"><span>12</span></font>日</p><div><br/></div>';\n" +
                "            var stringtemp =htmlStr.replace(/<[^>]+>/g, \"\");\n" +
                "            console.log(htmlStr);\n" +
                "            console.log(stringtemp);";

        //匹配汉字和英文
        String s = delHtmlTag(text);
        System.out.println(s);

//        System.out.println(getChinese(text));
//        Double a = 0.0 * 0 / 0.0;
//        System.out.println(a.isNaN());

        String html = "<p><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; " +
                "font-weight: 700; white-space: normal;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); " +
                "font-family: 微软雅黑; font-size: 20px; font-weight: 700; white-space: normal;\">实训步骤&nbsp;</span>" +
                "<span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700; white-space: normal;\">" +
                "实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); " +
                "font-family: 微软雅黑; font-size: 20px; font-weight: 700; white-space: normal;\">" +
                "实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); " +
                "font-family: 微软雅黑; font-size: 20px; font-weight: 700; white-space: normal;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700; white-space: normal;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700; white-space: normal;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700; white-space: normal;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span><span style=\"color: rgb(89, 89, 89); font-family: 微软雅黑; font-size: 20px; font-weight: 700;\">实训步骤&nbsp;</span></p>";

        String pattern = "(font-family: \\S*;)";

        System.out.println(Pattern.matches(pattern, html));

        System.out.println();
        Matcher match = Pattern.compile(pattern).matcher(html);
        while (match.find()) {
            System.out.println(match.group());
        }
        System.out.println();

        System.out.println(html.replaceAll(pattern, ""));
    }

    public static String getChinese(String paramValue) {
        String regex = "[a-zA-Z0-9\\u4E00-\\u9FA5]*";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }

    /**
     * 删除Html标签
     *
     * @param htmlStr 要删除的html
     * @return String
     */
    public static String delHtmlTag(String htmlStr) {
        // Pattern.CASE_INSENSITIVE 不区分大小写的匹配
        List<Pattern> patterns = new ArrayList<Pattern>() {
            {
                // 过滤W标签
                add(Pattern.compile(REG_EXW, Pattern.CASE_INSENSITIVE));
                // 过滤script标签
                add(Pattern.compile(REG_EX_SCRIPT, Pattern.CASE_INSENSITIVE));
                // 过滤style标签
                add(Pattern.compile(REG_EX_STYLE, Pattern.CASE_INSENSITIVE));
                // 过滤html标签
                add(Pattern.compile(REG_EX_HTML, Pattern.CASE_INSENSITIVE));
                // 过滤空格回车标签
                add(Pattern.compile(REG_EX_SPACE, Pattern.CASE_INSENSITIVE));
            }
        };

        // 匹配去除html
        for (Pattern pattern :patterns) {
            htmlStr = matcherAll(htmlStr, "", pattern);
        }

        return htmlStr.trim();
    }

    /**
     * 匹配所有该模式下的值，并替换成to值
     *
     * @param text    被替换的字符串
     * @param to      要替换的字符串
     * @param pattern 匹配规则
     * @return String
     */
    private static String matcherAll(String text, String to, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(to);
    }
}
