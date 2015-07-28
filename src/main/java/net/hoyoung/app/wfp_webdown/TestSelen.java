package net.hoyoung.app.wfp_webdown;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelen {

	public static void main(String[] args) {
		System.getProperties().setProperty("webdriver.chrome.driver", "E:\\huyang\\webfootprint\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.wisco.com.cn/wgxw2015/index.jhtml");
        List<WebElement> webElements = webDriver.findElements(By.tagName("a"));
        String currentWindow = webDriver.getWindowHandle();//获取当前窗口句柄
        for (WebElement webElement : webElements) {
//			System.out.println(webElement.getText());
        	System.out.println("下一页");
			if("下一页".equals(webElement.getText())){
				/*System.err.println("下一页");
				Actions action = new Actions(webDriver);
				action.click();*/
				long start = System.currentTimeMillis();
				
				webElement.click();
				long end = System.currentTimeMillis();
				System.out.println("---------------------------"+(end-start)/1000+"------------------------------");
				Set<String> handles = webDriver.getWindowHandles();
				System.out.println(handles);
				Iterator<String> it = handles.iterator();
				for (String string : handles) {
					System.out.println(string.equals(currentWindow));
				}
			}
		}
       /* try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
//        webDriver.close();
	}

}
