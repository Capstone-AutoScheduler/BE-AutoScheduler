package Capstone.AutoScheduler.global.service.SeleniumService;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

//@Service
//public class WebCrawlerService {
//

//}

@Service
public class WebCrawlerService {

    // 크롤링 기본 기능
    public List<String> getHtmlContent(int type, String url) {
        List<String> htmlContent = new ArrayList<>();
        WebDriver driver = null;

        try {
            validateUrl(url);

            // ChromeDriver 설정
            ChromeOptions options = new ChromeOptions();
            //options.addArguments("--headless"); // GUI 없이 실행
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

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("유효하지 않은 URL 형식입니다: " + url);
        } catch (Exception e) {
            throw new RuntimeException("크롤링 중 오류가 발생했습니다: " + e.getMessage());
        } finally {
            if (driver != null) {
                //driver.quit(); // WebDriver 종료
            }
        }
    }

//    private void validateUrl(String url) throws MalformedURLException {
//        URL parsedUrl = new URL(url);
//
//        if (!"https".equalsIgnoreCase(parsedUrl.getProtocol())) {
//            throw new IllegalArgumentException("HTTPS 프로토콜만 지원합니다.");
//        }
//    }


    // 로그인 후 크롤링 (targetUrl https://eclass.cau.ac.kr로 지정)
    public List<String> getHtmlContentWithLogin(int type, String loginUrl, String username, String password, String targetUrl) {
        List<String> htmlContent = new ArrayList<>();
        WebDriver driver = null;

        try {
            validateUrl(loginUrl);

            // ChromeDriver 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--ignore-ssl-errors=yes");
            options.addArguments("--ignore-certificate-errors");

            driver = new ChromeDriver(options);

            // 1. 로그인 페이지 로드
            driver.get(loginUrl);

            // 2. 로그인 필드 대기 및 입력
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            //wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_user_id"))); // 로그인 필드 확인
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("txtUserID")));

            //WebElement usernameField = driver.findElement(By.id("login_user_id"));
            //WebElement passwordField = driver.findElement(By.id("login_user_password"));
            WebElement usernameField = driver.findElement(By.id("txtUserID"));
            WebElement passwordField = driver.findElement(By.id("txtPwd"));
            //WebElement loginButton = driver.findElement(By.xpath("//div[@class='login_btn']/a"));
            WebElement loginButton = driver.findElement(By.xpath("//a[@onclick='OnLogon();']"));

            // 로그인 정보 입력
            usernameField.sendKeys(username);
            passwordField.sendKeys(password);

            // JavaScript로 로그인 버튼 클릭
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginButton);

            // 3. 로그인 후 URL 확인
            String currentUrl = driver.getCurrentUrl();
            System.out.println("로그인 후 현재 URL: " + currentUrl);

            // 4. eclass3.cau.ac.kr로 명시적 이동
            if (!currentUrl.contains("eclass3.cau.ac.kr")) {
                System.out.println("eclass3.cau.ac.kr로 이동 중...");
                driver.get("https://eclass3.cau.ac.kr");
                wait.until(ExpectedConditions.urlContains("https://eclass3.cau.ac.kr"));
            }

            // 5. 타겟 URL 로드 (타겟 페이지의 콘텐츠 가져오기)
            driver.get(targetUrl);

            if (type == 0) {
                // CSS 파일 링크 가져오기
                String cssFiles = "";
                List<WebElement> links = driver.findElements(By.xpath("//link[@rel='stylesheet']"));
                for (WebElement link : links) {
                    String cssLink = link.getAttribute("href");
                    cssFiles += "<link rel=\"stylesheet\" href=\"" + cssLink + "\">";
                }
                htmlContent.add(cssFiles);

                // HTML body만 가져오기
                String bodyContent = driver.findElement(By.tagName("body")).getAttribute("outerHTML");
                htmlContent.add(bodyContent);
            } else {
                // 전체 HTML 소스 가져오기
                String pageSource = driver.getPageSource();
                htmlContent.add(null); // CSS 파일은 null로 설정
                htmlContent.add(pageSource);
            }

            return htmlContent;

        } catch (Exception e) {
            throw new RuntimeException("크롤링 중 오류 발생: " + e.getMessage(), e);
        } finally {
            if (driver != null) {
                driver.quit(); // WebDriver 종료
            }
        }
    }

    private void validateUrl(String url) throws MalformedURLException {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url; // 스키마 추가
        }
        URL parsedUrl = new URL(url);
        if (!"https".equalsIgnoreCase(parsedUrl.getProtocol())) {
            throw new IllegalArgumentException("HTTPS 프로토콜만 지원합니다.");
        }
    }
}


