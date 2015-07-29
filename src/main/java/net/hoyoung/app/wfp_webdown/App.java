package net.hoyoung.app.wfp_webdown;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.htmlunit.HtmlUnitDownloader;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * Hello world!
 *
 */
public class App 
{
	private static String REQ_URL = "http://localhost:81";
//	private static String REQ_URL = "http://192.168.21.190:8375/rimp/login.do";
//	private static String REQ_URL = "http://www.wisco.com.cn/wgxw2015/index.jhtml";
    public static void main( String[] args )
    {
    	Spider.create(new WfpPageProcessor())
    	.setScheduler(new FileCacheQueueScheduler("E:\\huyang\\webfootprint\\urls"))
    	.setDownloader(new HtmlUnitDownloader())
    	.addPipeline(new WfpFilePipeline("E:\\huyang\\webfootprint\\downloads"))
    	.addUrl(REQ_URL).thread(1)
    	.run();
    	System.exit(0);
    }
}
