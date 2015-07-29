package net.hoyoung.app.wfp_webdown;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WfpPageProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	@Override
	public void process(Page page) {
//		System.out.println(page.getHtml());
//		System.out.println(page.getHtml().links().all());
	}
	@Override
	public Site getSite() {
		return site;
	}
}
