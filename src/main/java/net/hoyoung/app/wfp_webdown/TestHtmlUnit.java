package net.hoyoung.app.wfp_webdown;

import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestHtmlUnit {

	public static void main(String[] args) {
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
	      //htmlunit 对css和javascript的支持不好，所以请关闭之
//	        webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//			webClient.getOptions().setThrowExceptionOnScriptError(false);
	        webClient.getOptions().setCssEnabled(false);
	        try {
	        	String[] url = {"http://127.0.0.1:81/","http://www.wisco.com.cn/wgxw2015/index.jhtml"};
				HtmlPage htmlPage = webClient.getPage(url[1]);
				//http://localhost:81/
				/*DomNodeList<DomElement> elements = htmlPage.getElementsByTagName("a");
				for (DomElement domElement : elements) {
					if(domElement.getTextContent().equals("下一页")){
						
					}
				}*/
//				System.out.println(htmlPage.getWebResponse().getContentAsString());
				List<HtmlAnchor> anchors = htmlPage.getAnchors();
//				Page page = anchors.get(0).click();
//				System.out.println(page.getWebResponse().getContentAsString());
				for (HtmlAnchor htmlAnchor : anchors) {
//					System.out.println(htmlAnchor.getTextContent());
					if("下一页".equals(htmlAnchor.getTextContent())){
						
						System.out.println(htmlAnchor.getAttribute("onClick"));
						Page page = htmlAnchor.click();
						System.out.println(page.getUrl());
						System.out.println(page.getWebResponse().getContentAsString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	        webClient.closeAllWindows();
	        webClient = null;
	}

}
