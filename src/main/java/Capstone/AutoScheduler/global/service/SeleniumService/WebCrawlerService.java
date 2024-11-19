//package Capstone.AutoScheduler.global.service.SeleniumService;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.stereotype.Service;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//
//@Service
//public class WebCrawlerService {
//
//    private static final String SELENIUM_GRID_URL = "http://selenium_chrome:4444/wd/hub";
//
//    public String getHtmlContent(String url) {
//        WebDriver driver = null;
//
//        try {
//            validateUrl(url);
//
//            // Selenium Grid에 연결하여 RemoteWebDriver 사용
//            ChromeOptions options = new ChromeOptions();
//            driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
//
//            // URL 로드
//            driver.get(url);
//
//            // 페이지 로드 대기
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
//
//            // HTML 소스 반환
//            return driver.getPageSource();
//        } catch (MalformedURLException e) {
//            throw new IllegalArgumentException("유효하지 않은 URL 형식입니다: " + url);
//        } catch (Exception e) {
//            throw new RuntimeException("크롤링 중 오류가 발생했습니다: " + e.getMessage());
//        } finally {
//            if (driver != null) {
//                driver.quit(); // WebDriver 종료
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class WebCrawlerService {

    public String getHtmlContent(String url) throws MalformedURLException {
        // Selenium Grid의 URL 설정 (EC2 퍼블릭 IP를 사용)
        String seleniumGridUrl = "http://3.35.252.162:4444/wd/hub";

        // ChromeOptions 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // 헤드리스 모드로 실행 (브라우저 창이 뜨지 않음)
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // WebDriver 설정 (Selenium Grid에 연결)
        WebDriver driver = new RemoteWebDriver(new URL(seleniumGridUrl), new ChromeOptions());

        try {
            // 지정된 URL로 이동
            driver.get(url);

            // 페이지의 HTML 소스 가져오기
            String pageSource = driver.getPageSource();

            return pageSource;
        } finally {
            // WebDriver 종료
            driver.quit();
        }
    }
}
