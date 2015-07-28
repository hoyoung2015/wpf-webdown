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
//	private static String REQ_URL = "http://127.0.0.1:81";
	private static String REQ_URL = "http://192.168.21.190:8375/rimp/login.do";
    public static void main( String[] args )
    {
    	Spider.create(new WfpPageProcessor())
    	.setDownloader(new HtmlUnitDownloader())
    	.setScheduler(new FileCacheQueueScheduler("E:\\huyang\\webfootprint\\urls"))
    	.addUrl(REQ_URL).thread(5)
    	.run();
    }
}
