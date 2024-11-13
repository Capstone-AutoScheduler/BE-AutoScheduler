package Capstone.AutoScheduler.global.web.controller;

import Capstone.AutoScheduler.global.service.SeleniumService.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrawlerController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    @GetMapping("/crawl")
    public String crawl(@RequestParam String url) {
        return webCrawlerService.getHtmlContent(url);
    }
}
