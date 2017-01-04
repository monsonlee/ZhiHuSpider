# ZhiHuSpider
HttpcClient+HtmlCleaner+Xpath爬取知乎用户信息以及图片(某一话题下，所有精华问题最高票用户的信息以及用户答案中的所有图片)

src/main/java/crawler<br/>
           ----------Spider.java         爬虫入口<br/>

src/main/java/domain
           ----------Page.java           Page Bean
           ----------User.java           User Bean

src/main/java/download
           ----------DownLoad.java       下载接口
           ----------DownLoadImpl.java   下载实现类

src/main/java/process
           ----------Process.java        解析接口
           ----------ProcessImpl.java    解析实现类

src/main/java/utils
           ----------PageUtil.java       下载页面源代码的工具类
           ----------PictureUtil.java    下载图片的工具类
           ----------UserUtil.java       解析用户主页信息的工具类

src/main/resources
           ----------log4j.properties

src/test/java/crawler
           ----------TestSpider.java     爬虫测试类
