package com.fengx.mytest;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws ParseException {
//        test1();

        String text = "大学名称   大学分类   招生省份   招生年份   考生类别   批次名称   最低分数线   最低位次\n" +
                "贵州大学    综合    贵州    2021    文科    本一    592    4006\n" +
                "贵州师范大学    师范    贵州    2021    文科    本一    577    6023\n" +
                "贵州大学(农林类专业其它)    综合    贵州    2021    文科    本一    566    7806\n" +
                "贵州民族大学    民族    贵州    2021    文科    本一    562    8746\n" +
                "贵州大学(中外合作)    综合    贵州    2021    文科    本一    560    9031\n" +
                "贵州财经大学    财经    贵州    2021    文科    本一    560    9087\n" +
                "遵义医科大学(珠海校区)    医药    贵州    2021    文科    本一    560    9191\n" +
                "贵州师范学院    师范    贵州    2021    文科    本一    559    9346\n" +
                "贵州大学(人武校区)    综合    贵州    2021    文科    本一    556    10093\n" +
                "贵州医科大学    医药    贵州    2021    文科    本一    556    9985\n" +
                "贵州中医药大学    医药    贵州    2021    文科    本一    556    10058\n" +
                "遵义医科大学    医药    贵州    2021    文科    本一    556    9971\n" +
                "贵州师范大学    师范    贵州    2021    文科    本二    547    12222\n" +
                "贵州民族大学(民族班)    民族    贵州    2021    文科    本二    542    13739\n" +
                "贵州民族大学    民族    贵州    2021    文科    本二    535    15817\n" +
                "贵州师范学院    师范    贵州    2021    文科    本二    532    17040\n" +
                "贵州财经大学    财经    贵州    2021    文科    本二    530    17884\n" +
                "贵州医科大学    医药    贵州    2021    文科    本二    530    17799\n" +
                "遵义师范学院    师范    贵州    2021    文科    本二    529    17918\n" +
                "贵阳学院    综合    贵州    2021    文科    本二    525    19727\n" +
                "贵州理工学院    理工    贵州    2021    文科    本二    522    20820\n" +
                "遵义师范学院(定向)    师范    贵州    2021    文科    本二    521    21255\n" +
                "贵州警察学院    政法    贵州    2021    文科    本二    520    21572\n" +
                "贵州理工学院(民族班)    理工    贵州    2021    文科    本二    519    21884\n" +
                "铜仁学院    综合    贵州    2021    文科    本二    518    22526\n" +
                "凯里学院    师范    贵州    2021    文科    本二    517    22746\n" +
                "六盘水师范学院    师范    贵州    2021    文科    本二    517    22703\n" +
                "遵义医科大学    医药    贵州    2021    文科    本二    516    23366\n" +
                "贵州民族大学(民汉双语)(民族班)    民族    贵州    2021    文科    本二    515    23921\n" +
                "遵义医科大学(珠海校区)    医药    贵州    2021    文科    本二    515    23658\n" +
                "黔南民族师范学院    师范    贵州    2021    文科    本二    514    24140\n" +
                "黔南民族师范学院(民族班)    师范    贵州    2021    文科    本二    514    24347\n" +
                "六盘水师范学院(民族班)    师范    贵州    2021    文科    本二    513    24707\n" +
                "贵州工程应用技术学院    师范    贵州    2021    文科    本二    511    25579\n" +
                "安顺学院    综合    贵州    2021    文科    本二    511    25322\n" +
                "安顺学院(民族班)    综合    贵州    2021    文科    本二    511    25683\n" +
                "兴义民族师范学院(民族班)    师范    贵州    2021    文科    本二    511    25283\n" +
                "兴义民族师范学院    师范    贵州    2021    文科    本二    510    25928\n" +
                "凯里学院(民族班)    师范    贵州    2021    文科    本二    509    26293\n" +
                "六盘水师范学院(民汉双语)(民族班)    师范    贵州    2021    文科    本二    509    26351\n" +
                "贵州师范学院(民族班)    师范    贵州    2021    文科    本二    506    28049\n" +
                "茅台学院    理工    贵州    2021    文科    本二    501    30080\n" +
                "贵阳人文科技学院    民族    贵州    2021    文科    本二    500    31038\n" +
                "贵州商学院    财经    贵州    2021    文科    本二    499    31201\n" +
                "贵阳信息科技学院    综合    贵州    2021    文科    本二    497    32528\n" +
                "贵州民族大学(少数民族语言类专业其它)    民族    贵州    2021    文科    本二    494    33982\n" +
                "贵州医科大学神奇民族医药学院    医药    贵州    2021    文科    本二    494    34368\n" +
                "贵阳康养职业大学    医药    贵州    2021    文科    本二    494    34229\n" +
                "贵州黔南科技学院    综合    贵州    2021    文科    本二    488    37176\n" +
                "贵州中医药大学时珍学院    医药    贵州    2021    文科    本二    487    38293\n" +
                "贵州黔南经济学院    财经    贵州    2021    文科    本二    487    38037\n" +
                "贵州大学    综合    贵州    2021    文科    本二    486    38438\n" +
                "遵义医科大学医学与科技学院    医药    贵州    2021    文科    本二    486    38807\n" +
                "贵州中医药大学    医药    贵州    2021    文科    本二    485    39087\n" +
                "铜仁学院(民汉双语)(民族班)    综合    贵州    2021    文科    本二    485    38996\n" +
                "贵州师范大学(中外合作)    师范    贵州    2021    文科    本二    483    40459\n" +
                "贵州民族大学(少数民族彝语类)    民族    贵州    2021    文科    本二    482    41069\n" +
                "贵州商学院(中外合作)    财经    贵州    2021    文科    本二    480    42532\n" +
                "贵州财经大学(中外合作、国际本科学术互认课程项目)    财经    贵州    2021    文科    本二    479    43049\n" +
                "贵阳人文科技学院(少数民族语言类专业其它)    民族    贵州    2021    文科    本二    479    42916\n";
//                "遵义医药高等专科学校    医药    贵州    2021    文科    专科    474    45855\n" +
//                "贵阳幼儿师范高等专科学校    师范    贵州    2021    文科    专科    473    46622\n" +
//                "铜仁幼儿师范高等专科学校    师范    贵州    2021    文科    专科    471    47586\n" +
//                "黔南民族医学高等专科学校    医药    贵州    2021    文科    专科    460    54299\n" +
//                "黔南民族幼儿师范高等专科学校    师范    贵州    2021    文科    专科    460    54384\n" +
//                "毕节幼儿师范高等专科学校    师范    贵州    2021    文科    专科    460    54719\n" +
//                "贵州交通职业技术学院    理工    贵州    2021    文科    专科    459    55398\n" +
//                "贵阳康养职业大学    医药    贵州    2021    文科    专科    455    57795\n" +
//                "黔南民族医学高等专科学校(民族班)    医药    贵州    2021    文科    专科    451    60350\n" +
//                "贵州轻工职业技术学院    理工    贵州    2021    文科    专科    449    61825\n" +
//                "贵阳幼儿师范高等专科学校(民族班)    师范    贵州    2021    文科    专科    449    61562\n" +
//                "六盘水幼儿师范高等专科学校    师范    贵州    2021    文科    专科    449    61280\n" +
//                "毕节医学高等专科学校    医药    贵州    2021    文科    专科    448    62178\n" +
//                "贵州护理职业技术学院    医药    贵州    2021    文科    专科    442    65960\n" +
//                "遵义职业技术学院    综合    贵州    2021    文科    专科    435    70025\n" +
//                "贵阳职业技术学院    综合    贵州    2021    文科    专科    434    70929\n" +
//                "铜仁职业技术学院    综合    贵州    2021    文科    专科    432    71950\n" +
//                "贵阳职业技术学院(民族班)    综合    贵州    2021    文科    专科    432    71702\n" +
//                "贵州职业技术学院    综合    贵州    2021    文科    专科    432    71754\n" +
//                "黔东南民族职业技术学院    综合    贵州    2021    文科    专科    429    73717\n" +
//                "贵州健康职业学院    医药    贵州    2021    文科    专科    426    75674\n" +
//                "铜仁职业技术学院(民族班)    综合    贵州    2021    文科    专科    425    75942\n" +
//                "贵州建设职业技术学院    理工    贵州    2021    文科    专科    425    75966\n" +
//                "贵州财经职业学院    财经    贵州    2021    文科    专科    423    77157\n" +
//                "贵州交通职业技术学院(民族班)    理工    贵州    2021    文科    专科    423    77454\n" +
//                "安顺职业技术学院    综合    贵州    2021    文科    专科    419    79563\n" +
//                "毕节职业技术学院    综合    贵州    2021    文科    专科    410    84974\n" +
//                "贵州工业职业技术学院    理工    贵州    2021    文科    专科    407    86475\n" +
//                "黔东南民族职业技术学院(民族班)    综合    贵州    2021    文科    专科    391    95148\n" +
//                "铜仁职业技术学院(农林类专业其它)    综合    贵州    2021    文科    专科    389    95712\n" +
//                "黔西南民族职业技术学院    综合    贵州    2021    文科    专科    389    95957\n" +
//                "六盘水职业技术学院    综合    贵州    2021    文科    专科    388    96611\n" +
//                "贵州电子商务职业技术学院    综合    贵州    2021    文科    专科    384    98143\n" +
//                "贵州电子科技职业学院    理工    贵州    2021    文科    专科    380    100169\n" +
//                "贵州水利水电职业技术学院    理工    贵州    2021    文科    专科    371    103905\n" +
//                "贵州经贸职业技术学院    财经    贵州    2021    文科    专科    369    104828\n" +
//                "贵州农业职业学院    农林    贵州    2021    文科    专科    363    107262\n" +
//                "贵州装备制造职业学院    理工    贵州    2021    文科    专科    351    111327\n" +
//                "黔南民族职业技术学院    综合    贵州    2021    文科    专科    346    112923\n" +
//                "贵州轻工职业技术学院(中外合作)    理工    贵州    2021    文科    专科    342    114056\n" +
//                "贵州航空职业技术学院    理工    贵州    2021    文科    专科    322    118439\n" +
//                "贵州航天职业技术学院    理工    贵州    2021    文科    专科    319    118977\n" +
//                "贵州电子信息职业技术学院    理工    贵州    2021    文科    专科    312    120070\n" +
//                "贵州电子信息职业技术学院(民族班)    理工    贵州    2021    文科    专科    298    121736\n" +
//                "贵州航天职业技术学院(民族班)    理工    贵州    2021    文科    专科    282    123134\n" +
//                "贵州食品工程职业学院    综合    贵州    2021    文科    专科    260    124295\n" +
//                "贵州城市职业学院(民族班)    综合    贵州    2021    文科    专科    244    124854\n" +
//                "贵州民用航空职业学院    其他    贵州    2021    文科    专科    214    125473\n" +
//                "贵州应用技术职业学院    理工    贵州    2021    文科    专科    197    125685\n" +
//                "贵州盛华职业学院    综合    贵州    2021    文科    专科    190    125744\n" +
//                "贵州文化旅游职业学院    综合    贵州    2021    文科    专科    188    125752\n" +
//                "贵州机电职业技术学院    综合    贵州    2021    文科    专科    187    125757\n" +
//                "贵州工商职业学院    综合    贵州    2021    文科    专科    184    125780\n" +
//                "毕节工业职业技术学院    综合    贵州    2021    文科    专科    183    125786\n" +
//                "贵州工程职业学院    综合    贵州    2021    文科    专科    183    125784\n" +
//                "贵州工贸职业学院    综合    贵州    2021    文科    专科    183    125781\n" +
//                "贵州城市职业学院    综合    贵州    2021    文科    专科    180    125798\n";
        String[] split = text.split("\n");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
//        double d = 79.95;
//        //向上取整
//        double downd = Math.ceil(d * 100);
//        //然后把向上取整的数转换成int类型
//        int i = new Double(downd).intValue();
//        //转换成long类型
//        long l = new Double(downd).longValue();
//        System.out.println("最终转int的结果值:=========>>>>>>>>i:"+i);
//        System.out.println("最终转long的结果值:=========>>>>>>>l:"+l);
//        System.out.println("保留2位不进位" + Math.round(d * 10) / 10.0);
//
//        System.out.println(Math.round(d));
//
////        String pattern = "([0-9])";
////        Matcher match = Pattern.compile(pattern).matcher("矿物资源工程211");
////        while (match.find()) {
////            System.out.println(match.group());
////        }
//
//        System.out.println(secondsToLocalTime(1475));
//
//        String date = "2月15日";
////        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM月dd日");
//        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
//        Date parse = format.parse(date);
//        System.out.println(parse.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-");
//        System.out.println(LocalDate.now().format(formatter));
//
//        LocalDate date = LocalDate.now();
//        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth()); // 获取当前月的第一天
//        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth()); // 获取当前月的最后一天
//        System.out.println(firstDay);
//        System.out.println(lastDay);
//        LocalDate timetableDate = LocalDate.now();
//        LocalDate date1 = timetableDate.minusWeeks(1);
//        System.out.println(date1.atStartOfDay());
//        System.out.println(timetableDate.atStartOfDay().with(LocalTime.MAX));

//        int sort = -1;
//        List<Double> list = Lists.newArrayList(0D, 10D, 20D, null, null);
//        list.sort((o1, o2) -> {
//            if (o1 == null) {
//                return -sort;
//            }
//            if (o2 == null) {
//                return sort;
//            }
//            return sort == 1?o1.compareTo(o2):o2.compareTo(o1);
//        });
//        System.out.println(list);
    }

    private static LocalTime secondsToLocalTime(int second) {
        int hours = (second % (60 * 60 * 24)) / (60 * 60);
        int minutes = (second % (60 * 60)) / (60);
        int seconds = (second % (60));
        return LocalTime.of(hours, minutes, seconds, 0);
    }


    private static void test1() {
        String html = "<html></div><div class=\"_3nj4GN _3oieia\" role=\"button\" tabindex=\"0\" aria-label=\"给文章点赞\"><i aria-label=\"ic-like\" class=\"anticon\"><svg width=\"1em\" height=\"1em\" fill=\"currentColor\" aria-hidden=\"true\" focusable=\"false\" class=\"\"><use xlink:href=\"#ic-like\"></use></svg></i><span>赞<!-- -->231</span></div><div class=\"_3nj4GN ant-dropdown-trigger\" role=\"button\" tabindex=\"0\" aria-label=\"更多操作\"><i aria-label=\"ic-others\" class=\"anticon\"><svg width=\"1em\" height=\"1em\" fill=\"currentColor\" aria-hidden=\"true\" focusable=\"false\" class=\"\"><use xlink:href=\"#ic-others\"></use></svg></i></div></div></div></div><div class=\"_1LI0En\" style=\"height: 56px;\"></div></footer><div class=\"_27yofX _2gYj97\"><div role=\"button\" tabindex=\"-1\" aria-label=\"抽奖关闭\" class=\"_1bKQQB\"></div><div class=\"TlIPNx\" role=\"button\" tabindex=\"0\"><div class=\"_3eg6aX\">抽奖</div><img class=\"_3ONY7G\" src=\"data:image/png;base64,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\" alt=\"reward\"></div></div><div class=\"_3Pnjry\"><div class=\"_1pUUKr _2Z1aZJ\"><div class=\"_2VdqdF\" role=\"button\" tabindex=\"-1\" aria-label=\"给文章点赞\"><i aria-label=\"ic-like\" class=\"anticon\"><svg width=\"1em\" height=\"1em\" fill=\"currentColor\" aria-hidden=\"true\" focusable=\"false\" class=\"\"><use xlink:href=\"#ic-like\"></use></svg></i></div><div class=\"P63n6G\"><div class=\"_2LKTFF\"><span class=\"_1GPnWJ RhY_sp\">232<!-- -->赞</span><span class=\"_1GPnWJ\" role=\"button\" tabindex=\"-1\" aria-label=\"查看点赞列表\">231<!-- -->赞</span></div></div></div><div class=\"_1pUUKr\"><div class=\"_2VdqdF\" role=\"button\" tabindex=\"-1\" aria-label=\"赞赏作者\"><i aria-label=\"ic-shang\" class=\"anticon\"><svg width=\"1em\" height=\"1em\" fill=\"currentColor\" aria-hidden=\"true\" focusable=\"false\" class=\"\"><use xlink:href=\"#ic-shang\"></use></svg></i></div><div class=\"P63n6G\" role=\"button\" tabindex=\"-1\" aria-label=\"查看赞赏列表\">赞赏</div></div><div class=\"_1pUUKr\"><div class=\"_2VdqdF _1fDw5l\"><span class=\"t-eN3x RhY_sp\"></span></div><div class=\"P63n6G\">更多好文</div></div></div></div><script async=\"\" src=\"https://hm.baidu.com/hm.js?0c0e9d9b1e7d617b3e6842e85b9fb068\"></script><script async=\"\" src=\"https://www.google-analytics.com/analytics.js\"></script><script id=\"__NEXT_DATA__\" type=\"application/json\">{\"dataManager\":\"[]\",\"props\":{\"isServer\":true,\"initialState\":{\"global\":{\"done\":false,\"artFromType\":null,\"fontType\":\"black\",\"$modal\":{\"ContributeModal\":false,\"RewardListModal\":false,\"PayModal\":false,\"CollectionModal\":false,\"LikeListModal\":false,\"ReportModal\":false,\"QRCodeShareModal\":false,\"BookCatalogModal\":false,\"RewardModal\":false},\"$ua\":{\"value\":\"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3883.400 QQBrowser/10.8.4582.400\",\"isIE11\":false,\"earlyIE\":null,\"chrome\":\"70.0\",\"firefox\":null,\"safari\":null,\"isMac\":false},\"$diamondRate\":{\"displayable\":false,\"rate\":0},\"readMode\":\"day\",\"locale\":\"zh-CN\",\"seoList\":[]},\"note\":{\"data\":{\"is_author\":false,\"last_updated_at\":1596585625,\"public_title\":\"为什么有些人看起来特别年轻？\",\"purchased\":false,\"liked_note\":true,\"comments_count\":36,\"free_content\":\"\\u003cdiv class=\\\"image-package\\\"\\u003e\\n\\u003cdiv class=\\\"image-container\\\" style=\\\"max-width: 700px; max-height: 467px;\\\"\\u003e\\n\\u003cdiv class=\\\"image-container-fill\\\" style=\\\"padding-bottom: 66.64%;\\\"\\u003e\\u003c/div\\u003e\\n\\u003cdiv class=\\\"image-view\\\" data-width=\\\"3900\\\" data-height=\\\"2599\\\"\\u003e\\u003cimg data-original-src=\\\"//upload-images.jianshu.io/upload_images/14490662-375fbfc6a5cfdeff.jpg\\\" data-original-width=\\\"3900\\\" data-original-height=\\\"2599\\\" data-original-format=\\\"image/jpeg\\\" data-original-filesize=\\\"9676982\\\"\\u003e\\u003c/div\\u003e\\n\\u003c/div\\u003e\\n\\u003cdiv class=\\\"image-caption\\\"\\u003e\\u003c/div\\u003e\\n\\u003c/div\\u003e\\u003cp\\u003e有位老领导，60年代中期生人，大约15年前我们在一个单位工作，很多人都是“这领导好年轻”。其实，那时候他已经40多岁了。\\u003c/p\\u003e\\u003cp\\u003e听人谈论的多了以后，我就注意观察老领导，发现他也没什么特别的养生爱好，相反他还是个工作狂，经常白天下村累一天、晚上又叫上我一起写材料。\\u003c/p\\u003e\\u003cp\\u003e从他身上没找到答案，我又主义观察其他认识的人当中那些显年轻的，后来慢慢发现了一些共同点。\\u003c/p\\u003e\\u003ch4\\u003e其一：脸型的因素\\u003c/h4\\u003e\\u003cp\\u003e通常，脸型越有棱角，人越显得成熟。相反，圆脸会给人以“减龄”的既视感。\\u003c/p\\u003e\\u003cp\\u003e前面说的那位老领导就是圆脸，而且被别人称之为“娃娃脸”，给人的第一映像就是“嫩”。\\u003c/p\\u003e\\u003cp\\u003e这个因素其实在不同性别的人身上也可以得到印证。\\u003c/p\\u003e\\u003cp\\u003e比如，我有一位女同学就是娃娃脸，上次同学聚餐的时候，一帮女人叽叽喳喳在一起比谁比谁年轻。\\u003c/p\\u003e\\u003cp\\u003e我注意观察了一下，这个女同学还真是相对年轻一些，不了解的，完全看不出是有俩孩子的中年女人。\\u003c/p\\u003e\\u003cp\\u003e同样的，娱乐圈里赵丽颖也是圆脸，相对而言，赵丽颖看起来比同龄人要年轻一些。\\u003c/p\\u003e\\u003ch4\\u003e其二：肤色的因素\\u003c/h4\\u003e\\u003cp\\u003e本人是抠脚大汉一枚，也是娃娃脸。\\u003c/p\\u003e\\u003cp\\u003e先说一件趣事儿，换上一个单位的时候，遇到个超级热心的同事大姐。\\u003c/p\\u003e\\u003cp\\u003e开始彼此都不熟悉，就是工作接触那种，其它都没什么交流。\\u003c/p\\u003e\\u003cp\\u003e慢慢熟悉以后，有一次聊天听她说：“要不是你带老婆、孩子来单位玩儿，我还以为你单身，打算给你介绍女朋友呢！”\\u003c/p\\u003e\\u003cp\\u003e我笑着回答她：“咱是老黄瓜刷绿漆——装嫩，其实早就过了而立之年了。”\\u003c/p\\u003e\\u003cp\\u003e我以前经常晒太阳、皮肤黝黑的时候，看起来就比较老气，尽管娃娃脸不会变。\\u003c/p\\u003e\\u003cp\\u003e后来太阳晒的少了，皮肤慢慢变白净了一些，看起来确实年轻了不少。\\u003c/p\\u003e\\u003cp\\u003e人们形容年轻女士有句话叫——肤白貌美。\\u003c/p\\u003e\\u003cp\\u003e因为皮肤白净，人看起来就漂亮一些，一白遮百丑呀。\\u003c/p\\u003e\\u003ch4\\u003e其三：心态的因素\\u003c/h4\\u003e\\u003cp\\u003e心态是内在的，但可以反映到行动上，也就是可以外化的。\\u003c/p\\u003e\\u003cp\\u003e心态好的人，待人待事行云流水，心理压力小，看起来不催老。\\u003c/p\\u003e\\u003cp\\u003e仔细观察不难发现，那些长寿的人都有一个共同点——乐观、心里不藏事儿、天塌下来就那样。\\u003c/p\\u003e\\u003cp\\u003e以前走乡串户遇到过好几个90岁以上，还有百岁老人，他们就是这种性格，听他们聊过去，感觉就是活得没心没肺，哪儿黑哪儿歇的性格。\\u003c/p\\u003e\\u003cp\\u003e还有就是，心态好也体现在行动力上。\\u003c/p\\u003e\\u003cp\\u003e比如我爷爷，在我的印象中，他从来不会生气，而且和谁都是自来熟。\\u003c/p\\u003e\\u003cp\\u003e记得他活在的时候，经常带我去看电影，那时候农村看电影是稀奇事，电影院大多是小孩子常去的地方。\\u003c/p\\u003e\\u003cp\\u003e我爷爷是个特例，他也经常去，乐得和我们这些娃娃一起看电影，也不论题材，遇着啥看啥，整天乐呵呵的。\\u003c/p\\u003e\\u003cp\\u003e我爷爷是苦出身，年轻时遭了不少罪，但他活到80多岁，心态好可能是很重要的一个因素。\\u003c/p\\u003e\\u003ch4\\u003e其四：运动的因素\\u003c/h4\\u003e\\u003cp\\u003e运动有益新陈代谢，这是毋庸置疑的。\\u003c/p\\u003e\\u003cp\\u003e从自身体验来说，感觉经常运动的人会自带活力，整个人会显得精神、年轻。\\u003c/p\\u003e\\u003cp\\u003e这里说到另外一个同事，70初生人。\\u003c/p\\u003e\\u003cp\\u003e据他自己说，十年前他很胖，体重最高到200来斤，看起来老气横秋。\\u003c/p\\u003e\\u003cp\\u003e后来他开始跑步减肥，每天十公里以上，一直坚持到现在。\\u003c/p\\u003e\\u003cp\\u003e不少熟悉他的人都说他变年轻了，看起来整个人身体协调、皮肤细腻了不少。\\u003c/p\\u003e\\u003cp\\u003e每个人都期望有一颗年轻的心，从古代帝王痴迷长生术，到现代人各种滋补、养生、锻炼，都是为了让自己年轻一点、活得久一点。\\u003c/p\\u003e\\u003cp\\u003e其实，除了先天的因素以外，要保持年轻的状态，后天的努力必不可少。\\u003c/p\\u003e\\u003cp\\u003e愿每一位看到答案的友友永远年轻，点个赞的话再走的话，直接年轻十八岁。哈哈！\\u003c/p\\u003e\",\"voted_down\":false,\"rewardable\":true,\"show_paid_comment_tips\":false,\"share_image_url\":\"https://upload.jianshu.io/users/upload_avatars/14490662/b248a473-ba18-4e79-84b7-232b0199a1e9.jpg\",\"slug\":\"973586f78e5a\",\"user\":{\"liked_by_user\":false,\"following_count\":1719,\"gender\":1,\"avatar_widget\":null,\"slug\":\"a42e5ae55501\",\"intro\":\"写着写着，就习惯了。\",\"likes_count\":12493,\"nickname\":\"水木空影\",\"badges\":[{\"text\":\"幸运四叶草\",\"intro_url\":\"https://www.jianshu.com/p/3bc50b869c89\",\"image_url\":\"https://upload.jianshu.io/user_badge/d60756bb-83ca-4515-9eda-a8cd7c72e188\",\"icon\":\"other\"},{\"text\":\"鼠年大吉\",\"intro_url\":\"https://www.jianshu.com/p/6fd7763ac371\",\"image_url\":\"https://upload.jianshu.io/user_badge/abcf30a4-74ae-4fa4-838e-febd35361ce1\",\"icon\":\"other\"},{\"text\":\"简书优秀创作者\",\"intro_url\":\"https://www.jianshu.com/mobile/creator\",\"image_url\":\"https://upload.jianshu.io/user_badge/b4853dc7-5c16-4875-a2cd-7cf764bbd934\",\"icon\":\"other\"}],\"total_fp_amount\":\"78998017193502385051660\",\"wordage\":378967,\"avatar\":\"https://upload.jianshu.io/users/upload_avatars/14490662/b248a473-ba18-4e79-84b7-232b0199a1e9.jpg\",\"id\":14490662,\"liked_user\":false},\"likes_count\":231,\"paid_type\":\"fbook_free\",\"show_ads\":false,\"paid_content_accessible\":false,\"hide_search_input\":false,\"total_fp_amount\":\"150392000000000000000\",\"trial_open\":false,\"reprintable\":true,\"vip_note\":false,\"bookmarked\":false,\"wordage\":1318,\"featured_comments_count\":0,\"more_notes\":1,\"downvotes_count\":0,\"wangxin_trial_open\":null,\"guideShow\":{\"audit_user_nickname_spliter\":0,\"pc_note_bottom_btn\":1,\"pc_like_author_guidance\":1,\"show_more_notes\":1,\"h5_real_name_auth_link\":1,\"audit_user_background_image_spliter\":0,\"audit_note_spliter\":0,\"new_user_no_ads\":1,\"audit_post_spliter\":0,\"launch_tab\":0,\"include_post\":0,\"pc_login_guidance\":1,\"audit_comment_spliter\":0,\"pc_note_bottom_qrcode\":1,\"audit_user_avatar_spliter\":0,\"audit_collection_spliter\":0,\"show_vips_link\":1,\"pc_top_lottery_guidance\":1,\"subscription_guide_entry\":1,\"creation_muti_function_on\":1,\"pdd_ad_percent\":1,\"explore_score_searcher\":0,\"audit_user_spliter\":0,\"h5_ab_test\":1,\"home_index_ad\":0,\"reason_text\":1,\"pc_note_popup\":2},\"commentable\":true,\"total_rewards_count\":0,\"id\":75332626,\"notebook\":{\"name\":\"\"},\"activity_collection_slug\":null,\"description\":\"有位老领导，60年代中期生人，大约15年前我们在一个单位工作，很多人都是“这领导好年轻”。其实，那时候他已经40多岁了。 听人谈论的多了以后，我就注意观察老领导，发现他也没什...\",\"first_shared_at\":1596585629,\"views_count\":8886,\"notebook_id\":39050737},\"baseList\":{\"likeList\":[],\"rewardList\":[]},\"status\":\"success\",\"statusCode\":0},\"user\":{\"isLogin\":true,\"userInfo\":{\"jsd_level\":{\"name\":\"白金贵族\",\"level\":6},\"bind_wechat\":true,\"slug\":\"6004115ca941\",\"intro\":\"一个有点菜的程序员\\r\\n\\r\\n欢迎互关、互赞\",\"nickname\":\"枫未晚\",\"avatar\":\"https://upload.jianshu.io/users/upload_avatars/19619388/403b6205-b30a-4b4a-9721-e9a2269079bc.jpeg\",\"member\":{\"type\":\"bronze\",\"expires_at\":1659406514},\"ads_free\":true,\"id\":19619388}},\"comments\":{\"list\":[],\"featuredList\":[]}},\"initialProps\":{\"pageProps\":{\"query\":{\"slug\":\"973586f78e5a\"}},\"localeData\":{\"common\":{\"jianshu\":\"简书\",\"diamond\":\"简书钻\",\"totalAssets\":\"总资产{num}\",\"diamondValue\":\" (约{num}元)\",\"login\":\"登录\",\"logout\":\"注销\",\"register\":\"注册\",\"on\":\"开\",\"off\":\"关\",\"follow\":\"关注\",\"followBook\":\"关注连载\",\"following\":\"已关注\",\"cancelFollow\":\"取消关注\",\"publish\":\"发布\",\"wordage\":\"字数\",\"audio\":\"音频\",\"read\":\"阅读\",\"reward\":\"赞赏\",\"zan\":\"赞\",\"comment\":\"评论\",\"expand\":\"展开\",\"prevPage\":\"上一页\",\"nextPage\":\"下一页\",\"floor\":\"楼\",\"confirm\":\"确定\",\"delete\":\"删除\",\"report\":\"举报\",\"fontSong\":\"宋体\",\"fontBlack\":\"黑体\",\"chs\":\"简体\",\"cht\":\"繁体\",\"jianChat\":\"简信\",\"postRequest\":\"投稿请求\",\"likeAndZan\":\"喜欢和赞\",\"rewardAndPay\":\"赞赏和付费\",\"home\":\"我的主页\",\"markedNotes\":\"收藏的文章\",\"likedNotes\":\"喜欢的文章\",\"paidThings\":\"已购内容\",\"wallet\":\"我的钱包\",\"setting\":\"设置\",\"feedback\":\"帮助与反馈\",\"loading\":\"加载中...\",\"needLogin\":\"请登录后进行操作\",\"trialing\":\"文章正在审核中...\",\"reprintTip\":\"禁止转载，如需转载请通过简信或评论联系作者。\"},\"error\":{\"rewardSelf\":\"无法打赏自己的文章哟~\"},\"message\":{\"paidNoteTip\":\"付费购买后才可以参与评论哦\",\"CommentDisableTip\":\"作者关闭了评论功能\",\"contentCanNotEmptyTip\":\"回复内容不能为空\",\"addComment\":\"评论发布成功\",\"deleteComment\":\"评论删除成功\",\"likeComment\":\"评论点赞成功\",\"setReadMode\":\"阅读模式设置成功\",\"setFontType\":\"字体设置成功\",\"setLocale\":\"显示语言设置成功\",\"follow\":\"关注成功\",\"cancelFollow\":\"取消关注成功\",\"copySuccess\":\"复制代码成功\"},\"header\":{\"homePage\":\"首页\",\"download\":\"下载APP\",\"discover\":\"发现\",\"message\":\"消息\",\"reward\":\"赞赏支持\",\"editNote\":\"编辑文章\",\"writeNote\":\"写文章\",\"techarea\":\"IT技术\",\"vips\":\"会员\"},\"note\":{},\"noteMeta\":{\"lastModified\":\"最后编辑于 \",\"wordage\":\"字数 {num}\",\"viewsCount\":\"阅读 {num}\"},\"divider\":{\"selfText\":\"以下内容为付费内容，定价 ¥{price}\",\"paidText\":\"已付费，可查看以下内容\",\"notPaidText\":\"还有 {percent} 的精彩内容\",\"modify\":\"点击修改\"},\"paidPanel\":{\"buyNote\":\"支付 ¥{price} 继续阅读\",\"buyBook\":\"立即拿下 ¥{price}\",\"freeTitle\":\"该作品为付费连载\",\"freeText\":\"购买即可永久获取连载内的所有内容，包括将来更新的内容\",\"paidTitle\":\"还没看够？拿下整部连载！\",\"paidText\":\"永久获得连载内的所有内容, 包括将来更新的内容\",\"vipLinkTitle\":\"加入会员，解锁整部内容\\u003e\\u003e\"},\"book\":{\"last\":\"已是最后\",\"lookCatalog\":\"查看连载目录\",\"header\":\"文章来自以下连载\"},\"action\":{\"like\":\"{num}人点赞\",\"collection\":\"收入专题\",\"report\":\"举报文章\"},\"comment\":{\"allComments\":\"全部评论\",\"featuredComments\":\"精彩评论\",\"closed\":\"评论已关闭\",\"close\":\"关闭评论\",\"open\":\"打开评论\",\"desc\":\"按时间倒序\",\"asc\":\"按时间正序\",\"disableText1\":\"用户已关闭评论，\",\"disableText2\":\"与Ta简信交流\",\"placeholder\":\"写下你的评论...\",\"publish\":\"发表\",\"create\":\" 添加新评论\",\"reply\":\" 回复\",\"restComments\":\"还有{num}条评论，\",\"expandImage\":\"展开剩余{num}张图\",\"deleteText\":\"确定要删除评论么？\"},\"collection\":{\"title\":\"被以下专题收入，发现更多相似内容\",\"putToMyCollection\":\"收入我的专题\"},\"seoList\":{\"title\":\"推荐阅读\",\"more\":\"更多精彩内容\"},\"sideList\":{\"title\":\"推荐阅读\"},\"wxShareModal\":{\"desc\":\"打开微信“扫一扫”，打开网页后点击屏幕右上角分享按钮\"},\"bookChapterModal\":{\"try\":\"试读\",\"toggle\":\"切换顺序\"},\"collectionModal\":{\"title\":\"收入到我管理的专题\",\"search\":\"搜索我管理的专题\",\"newCollection\":\"新建专题\",\"create\":\"创建\",\"nothingFound\":\"未找到相关专题\",\"loadMore\":\"展开查看更多\"},\"contributeModal\":{\"search\":\"搜索专题投稿\",\"newCollection\":\"新建专题\",\"addNewOne\":\"去新建一个\",\"nothingFound\":\"未找到相关专题\",\"loadMore\":\"展开查看更多\",\"managed\":\"我管理的专题\",\"recommend\":\"推荐专题\"},\"QRCodeShow\":{\"payTitle\":\"微信扫码支付\",\"payText\":\"支付金额\"},\"rewardModal\":{\"title\":\"给作者送糖\",\"custom\":\"自定义\",\"placeholder\":\"给Ta留言...\",\"choose\":\"选择支付方式\",\"balance\":\"简书余额\",\"tooltip\":\"网站该功能暂时下线，如需使用，请到简书App操作\",\"confirm\":\"确认支付\",\"success\":\"赞赏成功\"},\"payModal\":{\"payBook\":\"购买连载\",\"payNote\":\"购买文章\",\"promotion\":\"优惠券\",\"promotionFetching\":\"优惠券获取中...\",\"noPromotion\":\"无可用优惠券\",\"promotionNum\":\"{num}张可用\",\"noUsePromotion\":\"不使用优惠券\",\"validPromotion\":\"可用优惠券\",\"invalidPromotion\":\"不可用优惠券\",\"total\":\"支付总额\",\"tip1\":\"· 你将购买的商品为虚拟内容服务，购买后不支持退订、转让、退换，请斟酌确认。\",\"tip2\":\"· 购买后可在“已购内容”中查看和使用。\",\"success\":\"购买成功\"},\"reportModal\":{\"abuse\":\"辱骂、人身攻击等不友善内容\",\"minors_forbidden\":\"未成年违规内容\",\"ad\":\"广告及垃圾信息\",\"plagiarism\":\"抄袭或未授权转载\",\"placeholder\":\"写下举报的详情情况（选填）\",\"success\":\"举报成功\"},\"guidModal\":{\"modalAText\":\"相似文章推荐\",\"subText\":\"下载简书APP，浏览更多相似文章\",\"btnAText\":\"先不下载，下次再说\",\"followOkText\":\"关注作者成功！\",\"followTextTip\":\"下载简书APP，作者更多精彩内容更新及时提醒！\",\"followBtn\":\"下次再说\",\"downloadTipText\":\"更多精彩内容，就在简书APP\",\"footerDownLoadText\":\"下载简书APP\",\"modabTitle\":\"免费送你2次抽奖机会\",\"modalbTip\":\"抽取10000收益加成卡，下载简书APP概率翻倍\",\"modalbFooterTip\":\"下载简书APP，天天参与抽大奖\",\"modalReward\":\"抽奖\",\"scanQrtip\":\"扫码下载简书APP\",\"downloadAppText\":\"下载简书APP，随时随地发现和创作内容\",\"redText\":\"阅读\",\"likesText\":\"赞\",\"downLoadLeft\":\"更多好文\",\"leftscanText\":\"把文字装进口袋\"}},\"currentLocale\":\"zh-CN\",\"asPath\":\"/p/973586f78e5a\"}},\"page\":\"/p/[slug]\",\"query\":{\"slug\":\"973586f78e5a\"},\"buildId\":\"QDh0uQ7w2tmwJ7RT4ukt3\",\"assetPrefix\":\"https://cdn3.jianshu.io/shakespeare\"}</script><script nomodule=\"\" src=\"https://cdn3.jianshu.io/shakespeare/_next/static/runtime/polyfills-83c9f0eea3aa0edfd89e.js\"></script><script async=\"\" data-next-page=\"/p/[slug]\" src=\"https://cdn3.jianshu.io/shakespeare/_next/static/QDh0uQ7w2tmwJ7RT4ukt3/pages/p/%5Bslug%5D.js\"></script><script async=\"\" data-next-page=\"/_app\" src=\"https://cdn3.jianshu.io/shakespeare/_next/static/QDh0uQ7w2tmwJ7RT4ukt3/pages/_app.js\"></script><script src=\"https://cdn3.jianshu.io/shakespeare/_next/static/runtime/webpack-1d3e45bf15d10b268413.js\" async=\"\"></script><script src=\"https://cdn3.jianshu.io/shakespeare/_next/static/chunks/commons.2df73d88cfeb34563866.js\" async=\"\"></script><script src=\"https://cdn3.jianshu.io/shakespeare/_next/static/chunks/styles.9c45855723c2256f150d.js\" async=\"\"></script><script src=\"https://cdn3.jianshu.io/shakespeare/_next/static/runtime/main-caf00e13f3942238a0fd.js\" async=\"\"></script><div id=\"qb-sougou-search\" style=\"display: none; opacity: 0;\"><p>搜索</p><p class=\"last-btn\">复制</p><iframe src=\"\"></iframe></div></body><div id=\"qbTrans-pageTrans-dialog\" style=\"display: none;\"><span class=\"qbTrans-pageTrans-dialog-success\" style=\"display: none;\"></span> <span class=\"qbTrans-pageTrans-dialog-guide\" style=\"display: none;\"></span> <span class=\"qbTrans-pageTrans-dialog-text\">是否将当前网页翻译成中文</span> <div id=\"qbTrans-pageTrans-dialog-btn\" class=\"qbTrans-pageTrans-dialog-btn\">网页翻译</div> <div class=\"qbTrans-pageTrans-dialog-btn-empty\">中英对照</div> <!----> <!----> <div class=\"qbTrans-pageTrans-dialog-close\"><span class=\"qbTrans-pageTrans-dialog-close-text\">关闭</span></div></div></html>";


        String pattern = "(_3oieia)";
        Matcher match = Pattern.compile(pattern).matcher(html);
        while (match.find()) {
            System.out.println(match.group());
        }
//
//        String pattern2 = "(<i class=\"iconfont ic-paid1\"></i> \\S*)";
//        Matcher match2 = Pattern.compile(pattern2).matcher(html);
//        while (match2.find()) {
//            System.out.println(match2.group());
//        }
//
//        String pattern3 = "(<meta name=\"csrf-token\" content=\"\\S*\")";
//        Matcher match3 = Pattern.compile(pattern3).matcher(html);
//        while (match3.find()) {
//            System.out.println(match3.group());
//        }
//
//        String pattern4 = "(href=\"/p/\\S*#comments)";
//        Matcher match4 = Pattern.compile(pattern4).matcher(html);
//        while (match4.find()) {
//            System.out.println(match4.group().replace("href=\"", "").replace("#comments", ""));
//        }
    }
}
