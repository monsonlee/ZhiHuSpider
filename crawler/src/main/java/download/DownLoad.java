package download;

import domain.Page;

public interface DownLoad {
	public void downloadPic(String url, String question, String name, int count);

	public Page download(String url);
}
