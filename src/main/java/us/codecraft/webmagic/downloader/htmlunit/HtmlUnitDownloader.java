package us.codecraft.webmagic.downloader.htmlunit;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.selector.PlainText;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitDownloader implements Downloader,Cloneable {
	private Logger logger = Logger.getLogger(getClass());
	private volatile WebClientPool webClientPool;
	private int poolSize = 1;
	private DuplicateRemovedScheduler duplicateRemovedScheduler;
	public HtmlUnitDownloader() {
		
	}
	public HtmlUnitDownloader(DuplicateRemovedScheduler duplicateRemovedScheduler) {
		this.duplicateRemovedScheduler = duplicateRemovedScheduler;
	}
	private void checkInit() {
        if (webClientPool == null) {
            synchronized (this){
            	webClientPool = new WebClientPool(poolSize);
            }
        }
    }
	public int getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	@Override
	public Page download(Request request, Task task) {
		checkInit();
		//获取当前页面
		WebClient webClient;
		try {
			webClient = webClientPool.get();
		} catch (InterruptedException e) {
			logger.warn("interrupted", e);
            return null;
		}
		logger.info("downloading page " + request.getUrl());
		HtmlPage htmlPage = null;
		try {
			htmlPage = webClient.getPage(request.getUrl());
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		List<HtmlAnchor> anchors = htmlPage.getAnchors();
		System.out.println("----------------->");
		for (HtmlAnchor htmlAnchor : anchors) {
			String href = htmlAnchor.getAttribute("href");
			System.out.println(href);
			System.out.println("".equals(href));//
			/*
			 * 校验是否合法
			 * 非空串
			 * 存在href属性
			 */
		}
		System.out.println("<-----------------");
		 Page page = new Page();
		 page.setStatusCode(htmlPage.getWebResponse().getStatusCode());
		 String content = htmlPage.getWebResponse().getContentAsString();
//		 System.out.println(UrlUtils.fixAllRelativeHrefs(content, request.getUrl()));
		 page.setUrl(new PlainText(request.getUrl()));
		 page.setRequest(request);
		 page.setRawText(content);
		 page.getHtml();
		 webClientPool.returnToPool(webClient);
		return page;
	}

	@Override
	public void setThread(int threadNum) {
		this.poolSize = threadNum;
	}
    public void close() {
    	webClientPool.closeAll();
    }
}
