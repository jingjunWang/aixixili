package invoice.iceason.fapiao.utility;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PDFDownloader {
///http://dev.fapiao.com:19080/dzfp-platform/downloadAction.do?method=download&request=e5uhf8WETIOMgaa2cCUMtl16dPE020M43YAKEMSVfW3YK5W0h5OUnBCUEfdZVn.gK5HwW42DRmsQlBH3WVZ.4Ajiy6ZDLjpM%5EdihIdidAIe
	private static final String REMOTE_FILE_URL = "http://dev.fapiao.com:19080/dzfp-platform/downloadAction.do?method=download&request=e5uhf8WETIOMgaa2cCUMtl16dPE020M4MlrqRar8egjz240794VwmYMlhdAvFCkWmq8.CK1aBTKq6xDQ7AACww__%5EFFbahJaDe";
	private static final String LOCAL_FILE_PATH = "F:/爱茜茜里电子发票/1.pdf"; // 改成你保存 文件的路径

	public static void main(String[] args) {
		new PDFDownloader(REMOTE_FILE_URL, LOCAL_FILE_PATH).download();
	}

	private String remoteFileUrl;
	private String localFilePath;

	public PDFDownloader(String remoteFileUrl, String localFilePath) {
		this.remoteFileUrl = remoteFileUrl;
		this.localFilePath = localFilePath;
	}

	public void download() {
		try {
			URL url = new URL(remoteFileUrl);

			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(5 * 1000); // 5000 毫秒内没有连接上
//			httpURLConnection.setRequestMethod("GET") ;
//			httpURLConnection.setRequestProperty(
//					"Accept",
//					"image/gif, image/jpeg, image/pjpeg, image/pjpeg, " +
//					"application/x-shockwave-flash, application/xaml+xml, " +
//					"application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
//					"application/x-ms-application, application/vnd.ms-excel, " +
//					"application/vnd.ms-powerpoint, application/msword, */*");
//			httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
//			httpURLConnection.setRequestProperty("Charset", "UTF-8");
//			//设置浏览器类型和版本、操作系统，使用语言等信息
//			httpURLConnection.setRequestProperty(
//					"User-Agent",
//					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; Trident/4.0; " +
//					".NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; " +
//					".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
//			//设置为长连接
//			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.connect(); // 连接
			System.out.println("连接 URL 成功~");

			int fileLenght = httpURLConnection.getContentLength();
			System.out.println("文件大小：" + (fileLenght / 1024.0) + " KB");

			System.out.println("开始下载...");
			FileOutputStream fos = null;
			try {
				DataInputStream dis = new DataInputStream(httpURLConnection.getInputStream());
				fos = new FileOutputStream(localFilePath);
				byte[] buf = new byte[10240]; // 根据实际情况可以 增大 buf 大小
				for (int readSize; (readSize = dis.read(buf)) > 0;) {
					fos.write(buf, 0, readSize);
				}
				System.out.println("下载完毕~");
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("下载时出错");
			}

			httpURLConnection.disconnect();
		} catch (IOException ex) {
			System.out.println("URL 不存在或者连接超时");
		}
	}
	
	public static void download(String urls,String path) {
		try {
			URL url = new URL(urls);

			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(5 * 1000); // 5000 毫秒内没有连接上
//			httpURLConnection.setRequestMethod("GET") ;
//			httpURLConnection.setRequestProperty(
//					"Accept",
//					"image/gif, image/jpeg, image/pjpeg, image/pjpeg, " +
//					"application/x-shockwave-flash, application/xaml+xml, " +
//					"application/vnd.ms-xpsdocument, application/x-ms-xbap, " +
//					"application/x-ms-application, application/vnd.ms-excel, " +
//					"application/vnd.ms-powerpoint, application/msword, */*");
//			httpURLConnection.setRequestProperty("Accept-Language", "zh-CN");
//			httpURLConnection.setRequestProperty("Charset", "UTF-8");
//			//设置浏览器类型和版本、操作系统，使用语言等信息
//			httpURLConnection.setRequestProperty(
//					"User-Agent",
//					"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.2; Trident/4.0; " +
//					".NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; " +
//					".NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
//			//设置为长连接
//			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			httpURLConnection.connect(); // 连接
			System.out.println("连接 URL 成功~");

			int fileLenght = httpURLConnection.getContentLength();
			System.out.println("文件大小：" + (fileLenght / 1024.0) + " KB");

			System.out.println("开始下载...");
			FileOutputStream fos = null;
			try {
				DataInputStream dis = new DataInputStream(httpURLConnection.getInputStream());
				fos = new FileOutputStream(path);
				byte[] buf = new byte[10240]; // 根据实际情况可以 增大 buf 大小
				for (int readSize; (readSize = dis.read(buf)) > 0;) {
					fos.write(buf, 0, readSize);
				}
				System.out.println("下载完毕~");
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("下载时出错");
			}
			finally{
				if(fos!=null){
					fos.close();
				}
			}

			httpURLConnection.disconnect();
		} catch (IOException ex) {
			System.out.println("URL 不存在或者连接超时");
		}
	}
}