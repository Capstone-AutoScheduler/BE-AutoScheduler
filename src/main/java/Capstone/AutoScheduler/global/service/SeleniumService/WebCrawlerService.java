package Capstone.AutoScheduler.global.service.SeleniumService;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WebCrawlerService {
//
//    public String getHtmlContent(String url) {
//        System.setProperty("webdriver.chrome.driver", "/Users/parkjh/3-2학기/캡스톤_07분반/chromedriver-mac-arm64"); // ChromeDriver 경로 설정
//
//        WebDriver driver = new ChromeDriver();
//        try {
//            driver.get(url);
//            return driver.getPageSource(); // 전체 HTML 반환
//        } finally {
//            driver.quit();
//        }
//    }
//}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.stereotype.Service;

@Service
public class WebCrawlerService {

    public String getHtmlContent(String url) {
        WebDriver driver = new SafariDriver(); // SafariDriver 사용
        try {
            driver.get(url);
            return driver.getPageSource(); // HTML 반환
        } finally {
            driver.quit();
        }
    }
}

