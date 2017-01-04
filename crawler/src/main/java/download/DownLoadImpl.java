package download;

import domain.Page;
import utils.PageUtil;
import utils.PictureUtil;

public class DownLoadImpl implements DownLoad {

	/**
	 * 下载图片
	 */
	public void downloadPic(String url, String question, String name, int count) {
		PictureUtil.downloadPic(url, question, name, count);
	}

	/**
	 * 下载原始内容
	 */
	public Page download(String url) {
		Page page = new Page();
		page.setContent(PageUtil.getContent(url));
		page.setUrl(url);
		return page;
	}

}
