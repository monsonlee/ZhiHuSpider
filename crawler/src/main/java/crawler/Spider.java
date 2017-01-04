package crawler;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Page;
import download.DownLoad;
import download.DownLoadImpl;
import process.Process;
import process.ProcessImpl;

public class Spider {
	Logger logger = LoggerFactory.getLogger(Spider.class.getSimpleName());
	private DownLoad downLoadIn;
	private Process processIn;

	public void setDownLoadInter(DownLoad downLoadIn) {
		this.downLoadIn = downLoadIn;
	}

	public void setProcessInter(Process processIn) {
		this.processIn = processIn;
	}

	/**
	 * 启动爬虫
	 */
	public static void start() {
		Spider spider = new Spider();
		spider.setDownLoadInter(new DownLoadImpl());
		spider.setProcessInter(new ProcessImpl());
		System.out.println("请输入一个要爬取的知乎话题:");
		// 获取话题
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String topic = scanner.nextLine();// 话题
		String url = "https://www.zhihu.com/search?type=topic&q=" + topic;
		// 下载
		Page page = spider.download(url);
		// 解析
		spider.process(page);
	}

	/**
	 * 下载
	 * 
	 * @param url
	 * @return
	 */
	public Page download(String url) {
		return downLoadIn.download(url);
	}

	/**
	 * 解析爬取的原始内容
	 * 
	 * @param page
	 * @param user
	 */
	public void process(Page page) {
		processIn.process(page);
	}

}
