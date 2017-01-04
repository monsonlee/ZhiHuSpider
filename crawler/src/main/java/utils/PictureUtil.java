package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PictureUtil {
	private static Logger logger = LoggerFactory.getLogger(PictureUtil.class.getSimpleName());

	/**
	 * 下载图片
	 */
	public static void downloadPic(String url, String question, String name, int count) {
		// 防止出现奇怪的SSL问题
		System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
		System.setProperty("jsse.enableSNIExtension", "false");
		System.setProperty("https.protocols", "SSLv3");
		System.setProperty("com.sun.net.ssl.rsaPreMasterSecretFix", "true");

		HttpClientBuilder builder = HttpClients.custom();// HttpClient构建器
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		// 设置每个站点的最大连接个数
		cm.setDefaultMaxPerRoute(4);
		builder.setConnectionManager(cm);
		// 获取一个HttpClient对象，模拟浏览器
		CloseableHttpClient httpclient = builder.build();
		HttpGet httpget = new HttpGet(url);
		httpget.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36");
		logger.info("正在下载:" + httpget.getURI());
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() >= 400) {
				throw new IOException("下载失败:" + response.getStatusLine().getStatusCode() + " imageUrl: " + url);
			}
			if (entity != null) {
				InputStream input = entity.getContent();
				File dir = new File("D:/zhihu-spider/" + question + "/" + name);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				File file = new File(dir + "/" + name + count + url.substring(url.lastIndexOf(".")));
				OutputStream output = new FileOutputStream(file);
				byte[] bf = new byte[1024];
				int len = -1;
				while ((len = input.read(bf)) != -1) {
					output.write(bf, 0, len);
					output.flush();
				}
				output.close();
				input.close();
				logger.info(name + count + url.substring(url.lastIndexOf(".")) + "下载成功");
			}
		} catch (Exception e) {
			logger.error("图片下载失败" + e.getMessage());
		} finally {
			try {
				response.close();
				httpclient.close();
			} catch (IOException e) {
				logger.error("资源释放失败" + e.getMessage());
			}
		}
	}
}
