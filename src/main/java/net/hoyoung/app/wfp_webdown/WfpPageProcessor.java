package net.hoyoung.app.wfp_webdown;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class WfpPageProcessor implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	@Override
	public void process(Page page) {
		System.err.println(page.getHtml());
		page.putField("html", page.getHtml());
	}
	@Override
	public Site getSite() {
		return site;
	}
}
