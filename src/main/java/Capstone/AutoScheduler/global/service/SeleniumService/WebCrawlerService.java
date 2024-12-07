package Capstone.AutoScheduler.global.service.SeleniumService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebCrawlerService {

    public List<String> getHtmlContent(int type, String url) {
        List<String> htmlContent = new ArrayList<>();
        WebDriver driver = null;

        try {
            validateUrl(url);

            // ChromeDriver 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless"); // GUI 없이 실행
            options.addArguments("--no-sandbox"); // 보안 설정
            options.addArguments("--disable-dev-shm-usage"); // 메모리 문제 방지
            options.addArguments("--disable-gpu"); //추가한 옵션
            options.addArguments("--ignore-ssl-errors=yes");
            options.addArguments("--ignore-certificate-errors");

            driver = new ChromeDriver(options);

            // URL 로드
            driver.get(url);

            // 페이지 로드 대기
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));

            if(type == 0) {
                // header의 CSS파일 가져오기
                String cssFiles = "";
                List<WebElement> links = driver.findElements(By.xpath("//link[@rel='stylesheet']"));
                for (WebElement link : links) {
                    String cssLink = link.getAttribute("href");
                    cssFiles += "<link rel=\"stylesheet\" href=\"" + cssLink + "\">";
                }
                htmlContent.add(cssFiles);

                // html의 body만 가져오기
                String bodyContent = driver.findElement(By.tagName("body")).getAttribute("outerHTML");
                htmlContent.add(bodyContent);
            }
            else {
                String pageSource = driver.getPageSource();
                htmlContent.add(null);
                htmlContent.add(pageSource);
            }

            return htmlContent;

//            // HTML 소스 가져와서 변수에 저장
//            String htmlContent = driver.getPageSource();
//
//            // HTML을 MIME 타입으로 반환
//            HttpHeaders headers = new HttpHeaders();
//            headers.add(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");
//
//            // ResponseEntity와 getBody로 body return
//            return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK).getBody();
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


//package Capstone.AutoScheduler.global.service.SeleniumService;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.springframework.stereotype.Service;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//@Service
//public class WebCrawlerService {
//
//    public String getHtmlContent(String url) throws MalformedURLException {
//        // Selenium Grid의 URL 설정 (EC2 퍼블릭 IP를 사용)
//        String seleniumGridUrl = "http://3.35.252.162:4444/wd/hub";
//
//        // ChromeOptions 설정
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // 헤드리스 모드로 실행 (브라우저 창이 뜨지 않음)
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//
//        // WebDriver 설정 (Selenium Grid에 연결)
//        //WebDriver driver = new RemoteWebDriver(new URL(seleniumGridUrl), new ChromeOptions());
//        WebDriver driver = new RemoteWebDriver(new URL("http://selenium-chrome:4444/wd/hub"), options);
//
//        try {
//            // 지정된 URL로 이동
//            driver.get(url);
//
//            // 페이지의 HTML 소스 가져오기
//            String pageSource = driver.getPageSource();
//
//            return pageSource;
//        } finally {
//            // WebDriver 종료
//            driver.quit();
//        }
//    }
//}
