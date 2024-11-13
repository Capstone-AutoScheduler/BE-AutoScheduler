package Capstone.AutoScheduler.global.web.controller;

import Capstone.AutoScheduler.global.service.SeleniumService.WebCrawlerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @Operation(
            summary = "크롤링 API",
            description = "URL을 입력하면 해당 페이지의 HTML 소스를 반환합니다."
    )
    @GetMapping("/crawl")
    public ResponseEntity<String> crawl(
            @Parameter(description = "크롤링할 URL", required = true)
            @RequestParam String url
    ) {
        try {
            // HTML 크롤링 결과 가져오기
            String htmlContent = webCrawlerService.getHtmlContent(url);

            // HTML을 MIME 타입으로 반환
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "text/html; charset=UTF-8");

            return new ResponseEntity<>(htmlContent, headers, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("유효하지 않은 URL 형식입니다: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("크롤링 중 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
