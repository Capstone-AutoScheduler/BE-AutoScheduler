package Capstone.AutoScheduler.global.web.controller;

import Capstone.AutoScheduler.global.apiPayload.ApiResponse;
import Capstone.AutoScheduler.global.apiPayload.code.status.ErrorStatus;
import Capstone.AutoScheduler.global.apiPayload.code.status.SuccessStatus;
import Capstone.AutoScheduler.global.converter.CrawlingConverter;
import Capstone.AutoScheduler.global.converter.EventConverter;
import Capstone.AutoScheduler.global.service.SeleniumService.WebCrawlerService;
import Capstone.AutoScheduler.global.web.dto.CrawlingResponseDTO;
import Capstone.AutoScheduler.global.web.dto.Event.EventResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//public class CrawlerController {
//
//    @Autowired
//    private WebCrawlerService webCrawlerService;
//
//    @CrossOrigin(origins = "http://localhost:3000")  // 프론트엔드 서버만 허용

//}

@RestController
public class CrawlerController {

    @Autowired
    private WebCrawlerService webCrawlerService;
    @CrossOrigin(origins = "http://localhost:3000")  // 프론트엔드 서버만 허용

    // 웹 크롤링 API (기본기능)
    @Operation(summary = "(기본)웹 크롤링 API", description = "URL을 입력하면 해당 페이지의 HTML 소스를 반환합니다.")
    @GetMapping("/crawl")
    public ApiResponse<CrawlingResponseDTO.GetCrawlingResultDTO> crawl(
            @Parameter(description = "크롤링할 URL", required = true)
            @RequestParam int type,
            @RequestParam String url
    ) {
        try {
            // HTML 크롤링 결과 가져오기
            List<String> htmlContent = webCrawlerService.getHtmlContent(type, url);

            return ApiResponse.onSuccess(SuccessStatus.CRAWLING_OK, CrawlingConverter.toGetCrawlingResultDTO(htmlContent));
        } catch (IllegalArgumentException e) {
            return ApiResponse.onFailure(ErrorStatus.CRAWLING_NOT_EXIST.getCode(), ErrorStatus.CRAWLING_NOT_EXIST.getMessage(), null);
        } catch (Exception e) {
            return ApiResponse.onFailure(ErrorStatus.CRAWLING_ERROR.getCode(), ErrorStatus.CRAWLING_NOT_EXIST.getMessage(), null);
        }
    }

    // 웹 로그인 후 html 반환 API
    @Operation(summary = "로그인 후 HTML 반환 API", description = "로그인 정보와 URL을 입력하면 로그인 이후의 HTML 소스를 반환합니다.")
    @GetMapping("/crawl-with-login")
    public ApiResponse<CrawlingResponseDTO.GetCrawlingResultDTO> crawlWithLogin(
            @RequestParam String loginUrl,
            @RequestParam String targetUrl,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam int type
    ) {
        try {
            // HTML 크롤링 결과 가져오기
            List<String> htmlContent = webCrawlerService.getHtmlContentWithLogin(type, loginUrl, username, password, targetUrl);

            return ApiResponse.onSuccess(SuccessStatus.CRAWLING_OK, CrawlingConverter.toGetCrawlingResultDTO(htmlContent));
        } catch (Exception e) {
            return ApiResponse.onFailure(ErrorStatus.CRAWLING_ERROR.getCode(), e.getMessage(), null);
        }
    }
}

