package process;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Page;
import download.DownLoadImpl;
import utils.PictureUtil;
import utils.UserUtil;

public class ProcessImpl implements Process {

	private Logger logger = LoggerFactory.getLogger(ProcessImpl.class.getSimpleName());

	/**
	 * 解析原始页面内容
	 */
	public void process(Page page) {
		HtmlCleaner htmlCleaner = new HtmlCleaner();
		// 获得根节点
		String content = page.getContent();
		// 抓取话题精华问题页面URL
		String url = page.getUrl();
		TagNode tagNode = htmlCleaner.clean(content);
		Object[] qObj;// 问题数组
		Object[] uObj;// 最高票用户数组
		Object[] answerObj;// 答案数组
		try {
			// 获取用户
			uObj = tagNode.evaluateXPath("//*[@id='zh-topic-top-page-list']/*/div/div/div[1]/div[3]/span/span[1]/a");
			if (uObj != null & uObj.length > 0) {
				logger.info("此页有" + uObj.length + "个用户");
				for (int i = 0; i < uObj.length; i++) {
					// 获取问题
					qObj = tagNode.evaluateXPath("//*[@id='zh-topic-top-page-list']/div[" + (i + 1) + "]/div/div/h2/a");
					// 获取答案
					answerObj = tagNode.evaluateXPath(
							"//*[@id=\"zh-topic-top-page-list\"]/div[" + (i + 1) + "]/div/div/div[1]/div[5]/textarea");
					TagNode qNode = (TagNode) qObj[0];// 问题节点
					TagNode aNode = (TagNode) answerObj[0];// 答案节点
					TagNode uNode = (TagNode) uObj[i];// 最高票用户节点
					// 解析node，获取精华问题
					String currentPage = "page" + url.substring(url.lastIndexOf("=") + 1);
					String question = qNode.getText().toString();
					if (question.contains("\n")) {
						question = question.replace("\n", "");
					}
					// 解析node，获取最高票用户链接URL
					String userHref = "https://www.zhihu.com" + uNode.getAttributeByName("href");
					String username = uNode.getText().toString();

					logger.info("\n" + currentPage + "问题" + (i + 1) + "：" + question + "\n最高票用户名：" + username
							+ "\n主页链接：" + userHref + "\n======================================================");

					// 根据url解析用户信息
					processUser(userHref);

					// 解析node，获取最高票用户答案中的图片地址
					String answer = aNode.getText().toString();
					Pattern pattern = Pattern.compile("data-original=&quot;(.*?)&quot;");
					Matcher matcher = pattern.matcher(answer);
					int count = 0;
					question = (currentPage + question).substring(0, (currentPage + question).length() - 1);
					while (matcher.find()) {
						// 下载图片
						PictureUtil.downloadPic(matcher.group(1), question, username, ++count);
					}
				}
			} else {
				String topic = url.substring(url.length() - 2);
				Pattern pattern = Pattern
						.compile("<div\\sclass=\"name\"><a\\shref=\"(.*?)\"\\sclass=\"name-link\"\\sdata-highlight>"
								+ topic + "</a></div>");
				Matcher matcher = pattern.matcher(content);
				if (matcher.find()) {
					String topicURL = "https://www.zhihu.com" + matcher.group(1) + "/top-answers";
					DownLoadImpl downLoadPage = new DownLoadImpl();
					Page userPage = null;
					// 因为每一话题下，所有精华问题只有50页，所以暂时先搞个for循环，后期优化
					for (int i = 1; i <= 50; i++) {
						userPage = downLoadPage.download(topicURL + "?page=" + i);
						process(userPage);
					}
				} else {
					logger.info("没有找到相关话题");
				}
			}
		} catch (XPatherException e) {
			logger.error("解析失败" + e.getMessage());
		}
	}

	/**
	 * 处理用户主页，析取用户信息
	 */
	public void processUser(String userUrl) {
		UserUtil.processUser(userUrl);
	}
}
