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


//package Capstone.AutoScheduler.global.service.SeleniumService;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//
//@Service
//public class WebCrawlerService {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebCrawlerService.class);
//
//    public String getHtmlContent(String url) {
//        WebDriver driver = null;
//
//        try {
//            logger.info("크롤링 시작 - URL 검증 중: {}", url); // URL 검증 로그
//            validateUrl(url);
//
//            // ChromeDriver 설정
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless"); // GUI 없이 실행
//            options.addArguments("--no-sandbox"); // 보안 설정
//            options.addArguments("--disable-dev-shm-usage"); // 메모리 문제 방지
//
//            logger.info("ChromeDriver 초기화 중...");
//            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver"); // ChromeDriver 경로
//            driver = new ChromeDriver(options);
//
//            logger.info("URL 로드 중: {}", url);
//            driver.get(url);
//
//            logger.info("페이지 로드 대기 중...");
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // 대기 시간 15초로 조정
//            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
//
//            logger.info("크롤링 완료: {}", url);
//            return driver.getPageSource();
//        } catch (MalformedURLException e) {
//            logger.error("유효하지 않은 URL 형식: {}", url, e);
//            throw new IllegalArgumentException("유효하지 않은 URL 형식입니다: " + url);
//        } catch (Exception e) {
//            logger.error("크롤링 중 오류 발생: {}", e.getMessage(), e);
//            throw new RuntimeException("크롤링 중 오류가 발생했습니다: " + e.getMessage());
//        } finally {
//            if (driver != null) {
//                logger.info("ChromeDriver 종료 중...");
//                driver.quit(); // WebDriver 종료
//            }
//        }
//    }
//
//    private void validateUrl(String url) throws MalformedURLException {
//        URL parsedUrl = new URL(url);
//        if (!"https".equalsIgnoreCase(parsedUrl.getProtocol())) {
//            throw new IllegalArgumentException("HTTPS 프로토콜만 지원합니다.");
//        }
//    }
//}


//package Capstone.AutoScheduler.global.service.SeleniumService;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//
//@Service
//public class WebCrawlerService {
//
//    private static final Logger logger = LoggerFactory.getLogger(WebCrawlerService.class);
//
//    public String getHtmlContent(String url) {
//        WebDriver driver = null;
//
//        try {
//            logger.info("크롤링 시작 - URL 검증 중: {}", url);
//            validateUrl(url);
//
//            // ChromeDriver 설정
//            WebDriverManager.chromedriver().setup(); // ChromeDriver 자동 관리
//
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless"); // GUI 없이 실행
//            options.addArguments("--no-sandbox"); // 보안 설정
//            options.addArguments("--disable-dev-shm-usage"); // 메모리 문제 방지
//            options.addArguments("--disable-gpu"); //추가한 옵션
//            options.addArguments("--ignore-ssl-errors=yes");
//            options.addArguments("--ignore-certificate-errors");
//
//
//            logger.info("ChromeDriver 초기화 중...");
//            driver = new ChromeDriver(options);
//
//            logger.info("URL 로드 중: {}", url);
//            driver.get(url);
//
//            logger.info("페이지 로드 대기 중...");
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
//
//            logger.info("크롤링 완료: {}", url);
//            return driver.getPageSource();
//        } catch (MalformedURLException e) {
//            logger.error("유효하지 않은 URL 형식: {}", url, e);
//            throw new IllegalArgumentException("유효하지 않은 URL 형식입니다: " + url);
//        } catch (Exception e) {
//            logger.error("크롤링 중 오류 발생: {}", e.getMessage(), e);
//            throw new RuntimeException("크롤링 중 오류가 발생했습니다: " + e.getMessage());
//        } finally {
//            if (driver != null) {
//                logger.info("ChromeDriver 종료 중...");
//                driver.quit(); // WebDriver 종료
//            }
//        }
//    }
//
//    private void validateUrl(String url) throws MalformedURLException {
//        URL parsedUrl = new URL(url);
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
            options.addArguments("--disable-gpu"); //추가한 옵션
            options.addArguments("--ignore-ssl-errors=yes");
            options.addArguments("--ignore-certificate-errors");

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
