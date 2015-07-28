package net.hoyoung.app.wfp_webdown;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.htmlunit.HtmlUnitDownloader;
import us.codecraft.webmagic.scheduler.DuplicateRemovedScheduler;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String REQ_URL = "http://127.0.0.1:81";
//	private static String REQ_URL = "http://www.wisco.com.cn/wgxw2015/index.jhtml";
    public static void main( String[] args )
    {
    	Spider spider = Spider.create(new WfpPageProcessor())
    	.setScheduler(new FileCacheQueueScheduler("E:\\huyang\\webfootprint\\urls"))
    	.addUrl(REQ_URL).thread(5);
    	DuplicateRemovedScheduler scheduler = (DuplicateRemovedScheduler) spider.getScheduler();
    	spider.setDownloader(new HtmlUnitDownloader(scheduler))
    	.run();
    }
}
