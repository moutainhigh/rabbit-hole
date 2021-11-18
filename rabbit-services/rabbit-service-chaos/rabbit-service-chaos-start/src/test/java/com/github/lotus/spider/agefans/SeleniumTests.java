package com.github.lotus.spider.agefans;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Created by hocgin on 2021/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class SeleniumTests {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/hocgin/Projects/dev/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.MINUTES);
        driver.get("https://www.agefans.cc/play/20200294?playid=3_19");
        WebDriver iframe = driver.switchTo().frame(((ChromeDriver) driver).findElementById("age_playfram"));
        String pageSource = iframe.getPageSource();
        String videoSrc = iframe.findElement(By.tagName("video")).getAttribute("src");


        System.out.println(videoSrc);

    }
}
