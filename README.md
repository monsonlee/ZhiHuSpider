# ZhiHuSpider
HttpcClient+HtmlCleaner+Xpath爬取知乎用户信息以及图片(某一话题下，所有精华问题最高票用户的信息以及用户答案中的所有图片)

src/main/java/crawler<br/>
           ----------Spider.java         爬虫入口<br/>

src/main/java/domain<br/>
           ----------Page.java           Page Bean<br/>
           ----------User.java           User Bean<br/>

src/main/java/download<br/>
           ----------DownLoad.java       下载接口<br/>
           ----------DownLoadImpl.java   下载实现类<br/>

src/main/java/process<br/>
           ----------Process.java        解析接口<br/>
           ----------ProcessImpl.java    解析实现类<br/>

src/main/java/utils<br/>
           ----------PageUtil.java       下载页面源代码的工具类<br/>
           ----------PictureUtil.java    下载图片的工具类<br/>
           ----------UserUtil.java       解析用户主页信息的工具类<br/>

src/main/resources<br/>
           ----------log4j.properties<br/>

src/test/java/crawler<br/>
           ----------TestSpider.java     爬虫测试类<br/>
