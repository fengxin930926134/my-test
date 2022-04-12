package com.fengx.mytest;

import java.net.ConnectException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        LocalDate parse = LocalDate.parse("1995-02-08");
        System.out.println(parse.toString());
        System.out.println("D30233475BE76016E0530100007F6C04".length());

        String html = "2022-04-08 16:28:48.492  INFO 14588 --- [           main] c.f.mytest.other.jianshu.TestController  : <!DOCTYPE html>\n" +
                "<!--[if IE 6]><html class=\"ie lt-ie8\"><![endif]-->\n" +
                "<!--[if IE 7]><html class=\"ie lt-ie8\"><![endif]-->\n" +
                "<!--[if IE 8]><html class=\"ie ie8\"><![endif]-->\n" +
                "<!--[if IE 9]><html class=\"ie ie9\"><![endif]-->\n" +
                "<!--[if !IE]><!--> <html> <!--<![endif]-->\n" +
                "\n" +
                "<head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0,user-scalable=no\">\n" +
                "\n" +
                "  <!-- Start of Baidu Transcode -->\n" +
                "  <meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\n" +
                "  <meta http-equiv=\"Cache-Control\" content=\"no-transform\" />\n" +
                "  <meta name=\"applicable-device\" content=\"pc,mobile\">\n" +
                "  <meta name=\"MobileOptimized\" content=\"width\"/>\n" +
                "  <meta name=\"HandheldFriendly\" content=\"true\"/>\n" +
                "  <meta name=\"mobile-agent\" content=\"format=html5;url=https://www.jianshu.com/\">\n" +
                "  <!-- End of Baidu Transcode -->\n" +
                "\n" +
                "    <meta name=\"description\"  content=\"简书是一个优质的创作社区，在这里，你可以任性地创作，一篇短文、一张照片、一首诗、一幅画……我们相信，每个人都是生活中的艺术家，有着无穷的创造力。\">\n" +
                "    <meta name=\"keywords\"  content=\"简书,简书官网,图文编辑软件,简书下载,图文创作,创作软件,原创社区,小说,散文,写作,阅读\">\n" +
                "\n" +
                "  <meta name=\"tencent-site-verification\" content=\"39a5ed77a02c0103af6ac08addbc3851\"/>\n" +
                "  <meta name=\"360-site-verification\" content=\"604a14b53c6b871206001285921e81d8\" />\n" +
                "  <meta property=\"wb:webmaster\" content=\"294ec9de89e7fadb\" />\n" +
                "  <meta property=\"qc:admins\" content=\"104102651453316562112116375\" />\n" +
                "  <meta property=\"qc:admins\" content=\"11635613706305617\" />\n" +
                "  <meta property=\"qc:admins\" content=\"1163561616621163056375\" />\n" +
                "  <meta name=\"google-site-verification\" content=\"6ARJIxhZLIgZT7J8MZkENr5mR0-CqshgzYyA3r3jBWU\" />\n" +
                "  <meta http-equiv=\"mobile-agent\" content=\"format=html5; url=https://www.jianshu.com/\">\n" +
                "\n" +
                "  <!-- Apple -->\n" +
                "  <meta name=\"apple-mobile-web-app-title\" content=\"简书\">\n" +
                "\n" +
                "  \n" +
                "\n" +
                "    <title>简书 - 创作你的创作</title>\n" +
                "\n" +
                "  <meta name=\"csrf-param\" content=\"authenticity_token\" />\n" +
                "<meta name=\"csrf-token\" content=\"reR/VTMmYkgYV+OmCAwgKJA81Ngp/XxmNWZlX1/qGjZDX+bkBNaqoMN1za4xW4yBFxxcMgv++lcdMP45UlTeSw==\" />\n" +
                "\n" +
                "  <!--<script data-ad-client=\"ca-pub-3077285224019295\" async src=\"https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>-->\n" +
                "\n" +
                "  <link rel=\"stylesheet\" media=\"all\" href=\"https://cdn2.jianshu.io/assets/web-c0324e28cae6040f85e9.css\" />\n" +
                "  \n" +
                "  <link rel=\"stylesheet\" media=\"all\" href=\"https://cdn2.jianshu.io/assets/web/pages/home/index/entry-729ca26febca48412c43.css\" />\n" +
                "\n" +
                "  <link href=\"https://cdn2.jianshu.io/assets/favicons/favicon-e743bfb1821442341c3ab15bdbe804f7ad97676bd07a770ccc9483473aa76f06.ico\" rel=\"shortcut icon\" type=\"image/x-icon\">\n" +
                "      <link rel=\"apple-touch-icon-precomposed\" href=\"https://cdn2.jianshu.io/assets/apple-touch-icons/57-a6f1f1ee62ace44f6dc2f6a08575abd3c3b163288881c78dd8d75247682a4b27.png\" sizes=\"57x57\" />\n" +
                "      <link rel=\"apple-touch-icon-precomposed\" href=\"https://cdn2.jianshu.io/assets/apple-touch-icons/72-fb9834bcfce738fd7b9c5e31363e79443e09a81a8e931170b58bc815387c1562.png\" sizes=\"72x72\" />\n" +
                "      <link rel=\"apple-touch-icon-precomposed\" href=\"https://cdn2.jianshu.io/assets/apple-touch-icons/76-49d88e539ff2489475d603994988d871219141ecaa0b1a7a9a1914f4fe3182d6.png\" sizes=\"76x76\" />\n" +
                "      <link rel=\"apple-touch-icon-precomposed\" href=\"https://cdn2.jianshu.io/assets/apple-touch-icons/114-24252fe693524ed3a9d0905e49bff3cbd0228f25a320aa09053c2ebb4955de97.png\" sizes=\"114x114\" />\n" +
                "      <link rel=\"apple-touch-icon-precomposed\" href=\"https://cdn3.jianshu.io/assets/apple-touch-icons/120-1bb7371f5e87f93ce780a5f1a05ff1b176828ee0d1d130e768575918a2e05834.png\" sizes=\"120x120\" />\n" +
                "      <link rel=\"apple-touch-icon-precomposed\" href=\"https://cdn2.jianshu.io/assets/apple-touch-icons/152-bf209460fc1c17bfd3e2b84c8e758bc11ca3e570fd411c3bbd84149b97453b99.png\" sizes=\"152x152\" />\n" +
                "\n" +
                "  <!-- Start of 访问统计 -->\n" +
                "    <script>\n" +
                "    var _hmt = _hmt || [];\n" +
                "    (function() {\n" +
                "      var hm = document.createElement(\"script\");\n" +
                "      hm.src = \"//hm.baidu.com/hm.js?0c0e9d9b1e7d617b3e6842e85b9fb068\";\n" +
                "      var s = document.getElementsByTagName(\"script\")[0];\n" +
                "      s.parentNode.insertBefore(hm, s);\n" +
                "    })();\n" +
                "    (function () {\n" +
                "      var hm = document.createElement('script');\n" +
                "      hm.src = 'https://v1.cnzz.com/z_stat.php?id=1279807957&web_id=1279807957';\n" +
                "      var s = document.getElementsByTagName('script')[0];\n" +
                "      s.parentNode.insertBefore(hm, s);\n" +
                "    }());\n" +
                "  </script>\n" +
                "\n" +
                "  <!-- End of 访问统计 -->\n" +
                "</head>\n" +
                "\n" +
                "  <!-- 只给10%的用户添加代码 -->\n" +
                "  <!-- ###第四范式-智能推荐：代码直接复制 无需修改参数### -->\n" +
                "  <!-- ###功能：上报内容并反馈用户行为### -->\n" +
                "  <!--\n" +
                "  -->\n" +
                "  <body lang=\"zh-CN\" class=\"reader-black-font\">\n" +
                "    <!-- 全局顶部导航栏 -->\n" +
                "<nav class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\n" +
                "  <div class=\"width-limit\">\n" +
                "    <!-- 左上方 Logo -->\n" +
                "    <a class=\"logo\" href=\"/\"><img src=\"https://cdn2.jianshu.io/assets/web/nav-logo-4c7bbafe27adc892f3046e6978459bac.png\" alt=\"Nav logo\" /></a>\n" +
                "\n" +
                "    <!-- 右上角 -->\n" +
                "      <!-- 登录显示写文章 -->\n" +
                "      <a class=\"btn write-btn\" target=\"_blank\" href=\"/writer#/\">\n" +
                "        <i class=\"iconfont ic-write\"></i>写文章\n" +
                "</a>\n" +
                "    <!-- 如果用户登录，显示下拉菜单 -->\n" +
                "      <div class=\"user\">\n" +
                "        <div data-hover=\"dropdown\">\n" +
                "          <a class=\"avatar\" href=\"/u/6004115ca941\"><img src=\"https://upload.jianshu.io/users/upload_avatars/19619388/403b6205-b30a-4b4a-9721-e9a2269079bc.jpeg?imageMogr2/auto-orient/strip|imageView2/1/w/120/h/120\" alt=\"120\" /></a>\n" +
                "        </div>\n" +
                "        <ul class=\"dropdown-menu\">\n" +
                "          <li>\n" +
                "            <a href=\"/u/6004115ca941\">\n" +
                "              <i class=\"iconfont ic-icon_mine\"></i><span>我的主页</span>\n" +
                "</a>          </li>\n" +
                "          <li>\n" +
                "            <!-- TODO bookmarks_path -->\n" +
                "            <a href=\"/bookmarks\">\n" +
                "              <i class=\"iconfont ic-icon_collection\"></i><span>收藏的文章</span>\n" +
                "</a>          </li>\n" +
                "          <li>\n" +
                "            <a href=\"/users/6004115ca941/liked_notes\">\n" +
                "              <i class=\"iconfont ic-icon_like\"></i><span>喜欢的文章</span>\n" +
                "</a>          </li>\n" +
                "          <li>\n" +
                "            <a href=\"/my/paid_notes\">\n" +
                "              <i class=\"iconfont ic-icon_purchased\"></i><span>已购内容</span>\n" +
                "</a>          </li>\n" +
                "          <li>\n" +
                "            <a href=\"/wallet\">\n" +
                "              <i class=\"iconfont ic-icon_wallet\"></i><span>我的钱包</span>\n" +
                "</a>          </li>\n" +
                "          <li>\n" +
                "            <a href=\"/settings\">\n" +
                "              <i class=\"iconfont ic-icon_setting\"></i><span>设置</span>\n" +
                "</a>          </li>\n" +
                "          <li>\n" +
                "            <a href=\"/faqs\">\n" +
                "              <i class=\"iconfont ic-icon_help\"></i><span>帮助与反馈</span>\n" +
                "</a>          </li>\n" +
                "          <li>\n" +
                "            <a rel=\"nofollow\" data-method=\"delete\" href=\"/sign_out\">\n" +
                "              <i class=\"iconfont ic-icon_logout\"></i><span>退出</span>\n" +
                "</a>          </li>\n" +
                "        </ul>\n" +
                "      </div>\n" +
                "\n" +
                "    <div id=\"view-mode-ctrl\">\n" +
                "    </div>\n" +
                "    <div class=\"container\">\n" +
                "      <div class=\"navbar-header\">\n" +
                "        <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#menu\" aria-expanded=\"false\">\n" +
                "          <span class=\"icon-bar\"></span>\n" +
                "          <span class=\"icon-bar\"></span>\n" +
                "          <span class=\"icon-bar\"></span>\n" +
                "        </button>\n" +
                "      </div>\n" +
                "      <div class=\"collapse navbar-collapse\" id=\"menu\">\n" +
                "        <ul class=\"nav navbar-nav\">\n" +
                "            <li class=\"tab active\">\n" +
                "              <a href=\"/\">\n" +
                "                <span class=\"menu-text\">发现</span><i class=\"iconfont ic-navigation-discover menu-icon\"></i>\n" +
                "</a>            </li>\n" +
                "            <li class=\"tab \">\n" +
                "              <a href=\"/subscriptions\">\n" +
                "                <span class=\"menu-text\">关注</span><i class=\"iconfont ic-navigation-follow menu-icon\"></i>\n" +
                "</a>            </li>\n" +
                "              <li onclick=\"addPoint(event, '会员专区')\" class=\"tab \">\n" +
                "                <a href=\"/vips\">\n" +
                "                  <span class=\"menu-text\">会员</span><i style=\"transform:scale(1)\" class=\"iconfont ic-vip menu-icon\"></i>\n" +
                "</a>              </li>\n" +
                "            <li onclick=\"addPoint(event, '程序员专区')\" class=\"tab \">\n" +
                "              <a href=\"/techareas\">\n" +
                "                <span class=\"menu-text\">IT技术</span><i style=\"transform:scale(1)\" class=\"iconfont ic-techareas menu-icon\"></i>\n" +
                "</a>            </li>\n" +
                "            <li class=\"tab notification v-notification-dropdown-menu \">\n" +
                "              <a class=\"notification-btn\" href=\" \" data-hover=\"dropdown\">\n" +
                "                <span class=\"menu-text\">消息</span>\n" +
                "                <i class=\"iconfont ic-navigation-notification menu-icon\"></i>\n" +
                "                <span class=\"badge\"></span>\n" +
                "              </a >\n" +
                "            </li>\n" +
                "          <li class=\"search\">\n" +
                "            <form target=\"_blank\" action=\"/search\" accept-charset=\"UTF-8\" method=\"get\"><input name=\"utf8\" type=\"hidden\" value=\"&#x2713;\" />\n" +
                "              <input type=\"text\" name=\"q\" id=\"q\" value=\"\" autocomplete=\"off\" placeholder=\"搜索\" class=\"search-input\" />\n" +
                "              <a class=\"search-btn\" href=\"javascript:void(null)\"><i class=\"iconfont ic-search\"></i></a>\n" +
                "</form>          </li>\n" +
                "        </ul>\n" +
                "          <div id=\"navbar-app-download\"></div>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</nav>\n" +
                "<script type=\"text/javascript\">\n" +
                "function addPoint (e, tabName) {\n" +
                "  e.stopPropagation();\n" +
                "  JsSensor.trackEvent(SAEVENTS.PC_HEADER_TAB_CLICK,{tab:tabName, place:'首页'});\n" +
                "  }\n" +
                "</script>\n" +
                "    \n" +
                "<div class=\"container index\">\n" +
                "  <div class=\"row\">\n" +
                "    <div class=\"col-xs-16 main\">\n" +
                "      <!-- Banner -->\n" +
                "            <div id=\"indexCarousel\" class=\"carousel slide\">\n" +
                "              <div class=\"carousel-inner\">\n" +
                "                  <div class=\"item active\">\n" +
                "                    <div class=\"banner\" data-banner-name=\"繁星计划——简书短篇征稿函\">\n" +
                "                        <a target=\"_blank\" href=\"https://www.jianshu.com/activity?utm_medium=index-banner&amp;utm_source=desktop\"><img src=\"https://upload.jianshu.io/admin_banners/web_images/5055/348f9e194f4062a17f587e2963b7feb0b0a5a982.png?imageMogr2/auto-orient/strip|imageView2/1/w/1250/h/540\" alt=\"540\" /></a>\n" +
                "                    </div>\n" +
                "                  </div>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "\n" +
                "      <!--\n" +
                "        <div class=\"recommend-collection\">\n" +
                "            <a class=\"collection\" target=\"_blank\" href=\"/c/7b2be866f564?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              <img src=\"https://upload.jianshu.io/collections/images/83/1.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/64/h/64\" alt=\"64\" />\n" +
                "              <div class=\"name\">摄影</div>\n" +
                "</a>            <a class=\"collection\" target=\"_blank\" href=\"/c/yD9GAd?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              <img src=\"https://upload.jianshu.io/collections/images/4/sy_20091020135145113016.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/64/h/64\" alt=\"64\" />\n" +
                "              <div class=\"name\">读书</div>\n" +
                "</a>            <a class=\"collection\" target=\"_blank\" href=\"/c/fcd7a62be697?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              <img src=\"https://upload.jianshu.io/collections/images/95/1.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/64/h/64\" alt=\"64\" />\n" +
                "              <div class=\"name\">故事</div>\n" +
                "</a>            <a class=\"collection\" target=\"_blank\" href=\"/c/8c92f845cd4d?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              <img src=\"https://upload.jianshu.io/collections/images/283250/%E6%BC%AB%E7%94%BB%E4%B8%93%E9%A2%98.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/64/h/64\" alt=\"64\" />\n" +
                "              <div class=\"name\">手绘</div>\n" +
                "</a>            <a class=\"collection\" target=\"_blank\" href=\"/c/V2CqjW?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              <img src=\"https://upload.jianshu.io/collections/images/14/6249340_194140034135_2.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/64/h/64\" alt=\"64\" />\n" +
                "              <div class=\"name\">@IT·互联网</div>\n" +
                "</a>            <a class=\"collection\" target=\"_blank\" href=\"/c/cc7808b775b4?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              <img src=\"https://upload.jianshu.io/collections/images/76/12.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/64/h/64\" alt=\"64\" />\n" +
                "              <div class=\"name\">自然科普</div>\n" +
                "</a>            <a class=\"collection\" target=\"_blank\" href=\"/c/5AUzod?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              <img src=\"https://upload.jianshu.io/collections/images/13/%E5%95%8A.png?imageMogr2/auto-orient/strip|imageView2/1/w/64/h/64\" alt=\"64\" />\n" +
                "              <div class=\"name\">旅行·在路上</div>\n" +
                "</a>\n" +
                "            <a class=\"more-hot-collection\" target=\"_blank\" href=\"/recommendations/collections?utm_medium=index-collections&amp;utm_source=desktop\">\n" +
                "              更多热门专题 <i class=\"iconfont ic-link\"></i>\n" +
                "</a>        </div>\n" +
                "      -->\n" +
                "      <div class=\"split-line\"></div>\n" +
                "      <div id=\"list-container\">\n" +
                "        <!-- 文章列表模块 -->\n" +
                "        <ul class=\"note-list\" infinite-scroll-url=\"/\">\n" +
                "              \n" +
                "\n" +
                "<li id=\"note-97401608\" data-note-id=\"97401608\" class=\"\">\n" +
                "  <div class=\"content\">\n" +
                "    <a class=\"title\" target=\"_blank\" href=\"/p/ff1c499c66f0\">今天下班很晚了</a>\n" +
                "    <p class=\"abstract\">\n" +
                "      十点下班，到宿舍，到洗漱好，到开始今天的日更。已经十一点半了。 最近的自己，真的感觉自己，每天都很累，回来的路上加上吃饭的时间。 说实话，用在学...\n" +
                "    </p>\n" +
                "    <div class=\"meta\">\n" +
                "        <span class=\"jsd-meta\">\n" +
                "          <i class=\"iconfont ic-paid1\"></i> 64.9\n" +
                "        </span>\n" +
                "      <a class=\"nickname\" target=\"_blank\" href=\"/u/b9c9af5e1d07\">小迷糊的叨叨</a>\n" +
                "        <a target=\"_blank\" href=\"/p/ff1c499c66f0#comments\">\n" +
                "          <i class=\"iconfont ic-list-comments\"></i> 41\n" +
                "</a>      <span><i class=\"iconfont ic-list-like\"></i> 225</span>\n" +
                "        <span><i class=\"iconfont ic-list-money\"></i> 1</span>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</li>\n" +
                "\n" +
                "    \n" +
                "\n" +
                "<li id=\"note-100189882\" data-note-id=\"100189882\" class=\"\">\n" +
                "  <div class=\"content\">\n" +
                "    <a class=\"title\" target=\"_blank\" href=\"/p/4c526be9ca68\">清明</a>\n" +
                "    <p class=\"abstract\">\n" +
                "      是日清明。 今天的天气很好。不敢随便的走动，即使身边的好多人都选择了去踏青，但是我还是选择了宅在家里，哪里都不去。 昨天孩子中午没有午睡，晚上八...\n" +
                "    </p>\n" +
                "    <div class=\"meta\">\n" +
                "        <span class=\"jsd-meta\">\n" +
                "          <i class=\"iconfont ic-paid1\"></i> 2.4\n" +
                "        </span>\n" +
                "      <a class=\"nickname\" target=\"_blank\" href=\"/u/ddc9cc89d39d\">辛梓梓</a>\n" +
                "        <a target=\"_blank\" href=\"/p/4c526be9ca68#comments\">\n" +
                "          <i class=\"iconfont ic-list-comments\"></i> 0\n" +
                "</a>      <span><i class=\"iconfont ic-list-like\"></i> 4</span>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</li>\n" +
                "\n" +
                "    \n" +
                "\n" +
                "<li id=\"note-100070889\" data-note-id=\"100070889\" class=\"\">\n" +
                "  <div class=\"content\">\n" +
                "    <a class=\"title\" target=\"_blank\" href=\"/p/7d40a9760ea9\">（1000/107）叛逆的小孩</a>\n" +
                "    <p class=\"abstract\">\n" +
                "      今天忙得脚不沾地，到晚上的时候我一个朋友的孩子跑到我上班的地方找我，刚开始以为是她爸爸妈妈带来找我的，后面再三确定才知道真的是她自己跑出来的。 ...\n" +
                "    </p>\n" +
                "    <div class=\"meta\">\n" +
                "        <span class=\"jsd-meta\">\n" +
                "          <i class=\"iconfont ic-paid1\"></i> 1.2\n" +
                "        </span>\n" +
                "      <a class=\"nickname\" target=\"_blank\" href=\"/u/65278170da6c\">朝颜_c1a6</a>\n" +
                "        <a target=\"_blank\" href=\"/p/7d40a9760ea9#comments\">\n" +
                "          <i class=\"iconfont ic-list-comments\"></i> 0\n" +
                "</a>      <span><i class=\"iconfont ic-list-like\"></i> 5</span>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</li>\n" +
                "\n" +
                "    \n" +
                "\n" +
                "<li id=\"note-98225472\" data-note-id=\"98225472\" class=\"\">\n" +
                "  <div class=\"content\">\n" +
                "    <a class=\"title\" target=\"_blank\" href=\"/p/a7ae2232c951\">春养肝</a>\n" +
                "    <p class=\"abstract\">\n" +
                "      春养肝，指春季养肝。春天在五行中属木，而人体的五脏之中肝也是属木性，因而春气通肝。在春天，肝气旺盛而升发，中医认为，春天是肝旺之时。春季养肝应该...\n" +
                "    </p>\n" +
                "    <div class=\"meta\">\n" +
                "        <span class=\"jsd-meta\">\n" +
                "          <i class=\"iconfont ic-paid1\"></i> 46.4\n" +
                "        </span>\n" +
                "      <a class=\"nickname\" target=\"_blank\" href=\"/u/7f2599d472b9\">MR唐</a>\n" +
                "        <a target=\"_blank\" href=\"/p/a7ae2232c951#comments\">\n" +
                "          <i class=\"iconfont ic-list-comments\"></i> 15\n" +
                "</a>      <span><i class=\"iconfont ic-list-like\"></i> 209</span>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</li>\n" +
                "\n" +
                "    \n" +
                "\n" +
                "<li id=\"note-100161546\" data-note-id=\"100161546\" class=\"have-img\">\n" +
                "    <a class=\"wrap-img\" href=\"/p/09942261b6f6\" target=\"_blank\">\n" +
                "      <img data-echo=\"https://upload-images.jianshu.io/upload_images/27041221-21dabe90b1ca91b4.png?imageMogr2/auto-orient/strip|imageView2/1/w/360/h/240\" class=\"img-blur\" src=\"https://upload-images.jianshu.io/upload_images/27041221-21dabe90b1ca91b4.png?imageMogr2/auto-orient/strip|imageView2/1/w/180/h/120\" alt=\"120\" />\n" +
                "    </a>\n" +
                "  <div class=\"content\">\n" +
                "    <a class=\"title\" target=\"_blank\" href=\"/p/09942261b6f6\">常见体质舌诊解析！</a>\n" +
                "    <p class=\"abstract\">\n" +
                "      舌诊，是一个可以窥探身体秘密的技术。血虚、气虚、痰湿、还是血瘀？都可以在你伸出舌头的刹那，看出个大概。 风寒感冒初起时，舌苔会变厚，这时候吃驱寒...\n" +
                "    </p>\n" +
                "    <div class=\"meta\">\n" +
                "        <span class=\"jsd-meta\">\n" +
                "          <i class=\"iconfont ic-paid1\"></i> 9.6\n" +
                "        </span>\n" +
                "      <a class=\"nickname\" target=\"_blank\" href=\"/u/1b24a73dd2a8\">Fu_ling</a>\n" +
                "        <a target=\"_blank\" href=\"/p/09942261b6f6#comments\">\n" +
                "          <i class=\"iconfont ic-list-comments\"></i> 5\n" +
                "</a>      <span><i class=\"iconfont ic-list-like\"></i> 93</span>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</li>\n" +
                "\n" +
                "    \n" +
                "\n" +
                "<li id=\"note-100094592\" data-note-id=\"100094592\" class=\"\">\n" +
                "  <div class=\"content\">\n" +
                "    <a class=\"title\" target=\"_blank\" href=\"/p/02f5e70df50c\">清明节上坟</a>\n" +
                "    <p class=\"abstract\">\n" +
                "      今天中午我和我弟约好一起去给亲人上坟。我回到家不到半小时，正和我妈聊天，我弟给我打电话，他从城里直接开车去地里，让我也去地里。 我到了以后，我弟...\n" +
                "    </p>\n" +
                "    <div class=\"meta\">\n" +
                "        <span class=\"jsd-meta\">\n" +
                "          <i class=\"iconfont ic-paid1\"></i> 25.0\n" +
                "        </span>\n" +
                "      <a class=\"nickname\" target=\"_blank\" href=\"/u/025f2425cbdb\">念薇薇</a>\n" +
                "        <a target=\"_blank\" href=\"/p/02f5e70df50c#comments\">\n" +
                "          <i class=\"iconfont ic-list-comments\"></i> 59\n" +
                "</a>      <span><i class=\"iconfont ic-list-like\"></i> 192</span>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</li>\n" +
                "\n" +
                "    \n" +
                "\n" +
                "<li id=\"note-99154682\" data-note-id=\"99154682\" class=\"\">\n" +
                "  <div class=\"content\">\n" +
                "    <a class=\"title\" target=\"_blank\" href=\"/p/d202981ca00e\">月经先期的辩证治疗</a>\n" +
                "    <p class=\"abstract\">\n" +
                "         辩证主要辨其属于气虚或血热，治疗以安定冲任为大法，或补脾固肾益气，或清热泻火，或滋阴清热。    一、气虚型。1.脾气虚：主要证候经期提前...\n" +
                "    </p>\n" +
                "    <div class=\"meta\">\n" +
                "        <span class=\"jsd-meta\">\n" +
                "          <i class=\"iconfont ic-paid1\"></i> 28.5\n" +
                "        </span>\n" +
                "      <a class=\"nickname\" target=\"_blank\" href=\"/u/e34d47d2f07b\">印象中的杏林</a>\n" +
                "        <a target=\"_blank\" href=\"/p/d202981ca00e#comments\">\n" +
                "          <i class=\"iconfont ic-list-comments\"></i> 1\n" +
                "</a>      <span><i class=\"iconfont ic-list-like\"></i> 107</span>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</li>\n" +
                "\n" +
                "\n" +
                "        </ul>\n" +
                "        <!-- 文章列表模块 -->\n" +
                "      </div>\n" +
                "    </div>\n" +
                "    <div class=\"col-xs-7 col-xs-offset-1 aside\">\n" +
                "      <div class=\"board\">\n" +
                "        <a target=\"_blank\" href=\"/mobile/campaign/day_by_day/join?from=home\"><img src=\"https://cdn2.jianshu.io/assets/web/banner-s-daily-e6f6601abc495573ad37f2532468186f.png\" alt=\"Banner s daily\" /></a>\n" +
                "        <a target=\"_blank\" href=\"/mobile/club\"><img src=\"https://cdn2.jianshu.io/assets/web/banner-s-club-aa8bdf19f8cf729a759da42e4a96f366.png\" alt=\"Banner s club\" /></a>\n" +
                "        <a utm_medium=\"index-banner-s\" target=\"_blank\" href=\"/mobile/books?category_id=284\"><img src=\"https://cdn2.jianshu.io/assets/web/banner-s-7-1a0222c91694a1f38e610be4bf9669be.png\" alt=\"Banner s 7\" /></a>\n" +
                "        <a utm_medium=\"index-banner-s\" target=\"_blank\" href=\"/publications\"><img src=\"https://cdn3.jianshu.io/assets/web/banner-s-5-4ba25cf5041931a0ed2062828b4064cb.png\" alt=\"Banner s 5\" /></a>\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- 首页右侧 App 下载提示 -->\n" +
                "      <a id=\"index-aside-download-qrbox\" class=\"col-xs-8 download\" data-toggle=\"popover\" data-placement=\"top\" data-html=\"true\" data-trigger=\"hover\" data-content=\"&lt;img src=&quot;https://cdn2.jianshu.io/assets/web/download-index-side-qrcode-bef8a3919bdba9e8d965956b37291e98.png&quot; alt=&quot;Download index side qrcode&quot; /&gt;\" href=\"/apps?utm_medium=desktop&amp;utm_source=index-aside-click\">\n" +
                "        <img class=\"qrcode\" src=\"https://cdn2.jianshu.io/assets/web/download-index-side-qrcode-bef8a3919bdba9e8d965956b37291e98.png\" alt=\"Download index side qrcode\" />\n" +
                "        <div class=\"info\">\n" +
                "          <div class=\"title\">下载简书手机App<i class=\"iconfont ic-link\"></i></div>\n" +
                "          <div class=\"description\">随时随地发现和创作内容</div>\n" +
                "        </div>\n" +
                "</a>      <!-- smua广告 -->\n" +
                "      <div data-vcomp=\"third-party-ad\" props-data-smua=\"u2823526\" props-data-place=\"侧边栏上方广告\" props-data-script-url=\"//udiab1.jianshu.com/common/d/common/yxxv/source/g/openjs/g_az.js\" props-data-w=\"280\" props-data-h=\"280\" ></div>\n" +
                "      <!-- 推荐作者 -->\n" +
                "      <div data-vcomp=\"recommended-author-list\"></div>\n" +
                "      <!-- 广告 -->\n" +
                "      <div data-vcomp=\"third-party-ad\" props-data-m60=\"mar60\" props-data-smua=\"u2823526\" props-data-place=\"侧边栏下方广告\" props-data-script-url=\"//udiab1.jianshu.com/production/bw/production/v_v_t_eeyt.js\" props-data-w=\"280\" props-data-h=\"280\"></div>\n" +
                "      <!-- 抽奖弹窗 -->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "<div data-vcomp=\"side-tool\"></div>\n" +
                "<footer class=\"container\">\n" +
                "  <div class=\"row\">\n" +
                "    <div class=\"col-xs-17 main\">\n" +
                "      \n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<a target=\"_blank\" href=\"https://www.jianshu.com/c/jppzD2\">关于简书</a><em> · </em><a target=\"_blank\" href=\"https://www.jianshu.com/contact\">联系我们</a><em> · </em><a target=\"_blank\" href=\"https://www.jianshu.com/c/bfeec2e13990\">加入我们</a><em> · </em><a target=\"_blank\" href=\"https://www.jianshu.com/p/fc1c113e5b6b\">简书出版</a><em> · </em><a target=\"_blank\" href=\"https://www.jianshu.com/press\">品牌与徽标</a><em> · </em><a target=\"_blank\" href=\"https://www.jianshu.com/faqs\">帮助中心</a><em> · </em><a target=\"_blank\" href=\"https://www.jianshu.com/p/cabc8fa39830\">合作伙伴</a><em> · </em><a target=\"_blank\" href=\"https://www.picmoon.com/\">涂檬-原创插画社区</a>      <div class=\"icp\">\n" +
                "        ©2012-2021 上海佰集信息科技有限公司 / 简书 / \n" +
                "        <a target=\"_blank\" href=\"https://beian.miit.gov.cn\">沪ICP备11018329号-5 / </a>\n" +
                "        <a target=\"_blank\" href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31010402002252\">\n" +
                "          <img src=\"https://cdn2.jianshu.io/assets/web/smrz-557fa318122c99a66523209bf9753a27.png\" alt=\"Smrz\" />\n" +
                "</a>        <a target=\"_blank\" href=\"http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31010402002252\">沪公网安备31010402002252号 / </a>\n" +
                "        <a target=\"_blank\" href=\"https://www.12377.cn/\">\n" +
                "          <img src=\"https://cdn2.jianshu.io/assets/web/wxb-e6e244a25f15a58bc91ceb4ea6d0e70a.png\" alt=\"Wxb\" />\n" +
                "</a>        <a target=\"_blank\" href=\"http://www.shjbzx.cn/\">\n" +
                "          <img src=\"https://cdn2.jianshu.io/assets/web/weifa-57fe174be588966e9ae70967539666e3.jpg\" alt=\"Weifa\" />\n" +
                "</a>        简书网举报邮箱：help@jianshu.com \n" +
                "        <br>\n" +
                "        举报电话：18510346566 / \n" +
                "        <a target=\"_blank\" href=\"javascript:void(null)\">\n" +
                "          <img src=\"https://cdn2.jianshu.io/assets/web/fanzha-10518f0f6b33635180b190975ae68ca6.jpg\" alt=\"Fanzha\" />\n" +
                "</a>        亲爱的市民朋友，上海警方反诈劝阻电话“962110”系专门针对避免您财产被骗受损而设，请您一旦收到来电，立即接听 / \n" +
                "        <a target=\"_blank\" href=\"http://218.242.124.22:8081/businessCheck/verifKey.do?showType=extShow&amp;serial=9031000020171107081457000002158769-SAIC_SHOW_310000-20171115131223587837&amp;signData=MEQCIADWZ5aTcBeER5SOVp0ly+ElvKnwtjczum6Gi6wZ7/wWAiB9MAPM22hp947ZaBobux5PDkd0lfqoCOkVV7zjCYME6g==\">\n" +
                "          <img src=\"https://cdn2.jianshu.io/assets/web/zggsrz-5695587dccf490ca3e651f4228f7479e.png\" alt=\"Zggsrz\" />\n" +
                "</a>      </div>\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</footer>\n" +
                "\n" +
                "<script>\n" +
                "  (function () {\n" +
                "    var start = new Date('2020-04-04T00:00:00');\n" +
                "    var end = new Date('2020-04-05T00:00:00');\n" +
                "    var now = new Date();\n" +
                "\n" +
                "    if (start <= now && now <= end) {\n" +
                "      document.body.className += ' gray-screen';\n" +
                "    }\n" +
                "  }());\n" +
                "</script>\n" +
                "<script type=\"text/javascript\">\n" +
                "window.onload = function () {\n" +
                "  JsSensor.trackEvent(SAEVENTS.PC_HOME_IMPRESSION);\n" +
                "  M.cnzz('PC+首页','曝光');\n" +
                "}\n" +
                "</script>\n" +
                "\n" +
                "    <script type=\"application/json\" data-name=\"page-data\">{\"user_signed_in\":true,\"locale\":\"zh-CN\",\"os\":\"windows\",\"read_mode\":\"day\",\"read_font\":\"font2\",\"current_user\":{\"id\":19619388,\"nickname\":\"枫未晚\",\"slug\":\"6004115ca941\",\"avatar\":\"https://upload.jianshu.io/users/upload_avatars/19619388/403b6205-b30a-4b4a-9721-e9a2269079bc.jpeg\",\"unread_counts\":{\"total\":null},\"is_member\":true,\"ads_free\":true}}</script>\n" +
                "    \n" +
                "    <script src=\"https://cdn2.jianshu.io/assets/babel-polyfill-1c17865be49622d517c1.js\" crossorigin=\"anonymous\"></script>\n" +
                "    <script src=\"https://cdn2.jianshu.io/assets/web-base-2e2009cb915ff1c9f4a4.js\" crossorigin=\"anonymous\"></script>\n" +
                "<script src=\"https://cdn2.jianshu.io/assets/web-0d23ed0271277986c7ae.js\" crossorigin=\"anonymous\"></script>\n" +
                "    \n" +
                "    <script src=\"https://cdn3.jianshu.io/assets/web/pages/home/index/entry-c0f431e38f9f2b3b4cda.js\" crossorigin=\"anonymous\"></script>\n" +
                "    \n" +
                "  </body>\n" +
                "</html>\n" +
                "\n";


        String pattern = "(data-note-id=\"\\S*\")";
        Matcher match = Pattern.compile(pattern).matcher(html);
        while (match.find()) {
            System.out.println(match.group());
        }

        String pattern2 = "(<i class=\"iconfont ic-paid1\"></i> \\S*)";
        Matcher match2 = Pattern.compile(pattern2).matcher(html);
        while (match2.find()) {
            System.out.println(match2.group());
        }

        String pattern3 = "(<meta name=\"csrf-token\" content=\"\\S*\")";
        Matcher match3 = Pattern.compile(pattern3).matcher(html);
        while (match3.find()) {
            System.out.println(match3.group());
        }

        String pattern4 = "(href=\"/p/\\S*#comments)";
        Matcher match4 = Pattern.compile(pattern4).matcher(html);
        while (match4.find()) {
            System.out.println(match4.group().replace("href=\"", "").replace("#comments", ""));
        }
    }
}
