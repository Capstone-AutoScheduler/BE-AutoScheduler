//package Capstone.AutoScheduler.global.service.SeleniumService;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.safari.SafariDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.stereotype.Service;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class WebCrawlerService {
//
//    public String getHtmlContent(String url) {
//        WebDriver driver = null;
//
//        try {
//            validateUrl(url);
//
//            driver = new SafariDriver();
//            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//
//            driver.get(url);
//
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
//
//            return driver.getPageSource();
//        } catch (MalformedURLException e) {
//            throw new IllegalArgumentException("유효하지 않은 URL 형식입니다: " + url);
//        } catch (Exception e) {
//            throw new RuntimeException("크롤링 중 오류가 발생했습니다: " + e.getMessage());
//        } finally {
//            if (driver != null) {
//                driver.quit();
//            }
//        }
//    }
//
//    private void validateUrl(String url) throws MalformedURLException {
//        URL parsedUrl = new URL(url);
//
//        if (!"https".equalsIgnoreCase(parsedUrl.getProtocol())) {
//            throw new IllegalArgumentException("HTTPS 프로토콜만 지원합니다.");
//        }
//    }
//}


package Capstone.AutoScheduler.global.service.SeleniumService;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Service
public class WebCrawlerService {

    public String getHtmlContent(String url) {
        WebDriver driver = null;

        try {
            validateUrl(url);

            // ChromeDriver 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // GUI 없이 실행
            options.addArguments("--no-sandbox"); // 보안 설정
            options.addArguments("--disable-dev-shm-usage"); // 메모리 문제 방지

            driver = new ChromeDriver(options);

            // URL 로드
            driver.get(url);

            // 페이지 로드 대기
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));

            // HTML 소스 반환
            return driver.getPageSource();
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("유효하지 않은 URL 형식입니다: " + url);
        } catch (Exception e) {
            throw new RuntimeException("크롤링 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit(); // WebDriver 종료
            }
        }
    }

    private void validateUrl(String url) throws MalformedURLException {
        URL parsedUrl = new URL(url);

        if (!"https".equalsIgnoreCase(parsedUrl.getProtocol())) {
            throw new IllegalArgumentException("HTTPS 프로토콜만 지원합니다.");
        }
    }
}
