package Capstone.AutoScheduler.global.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CrawlingResponseDTO {
    // html의 body와 cssFile링크
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCrawlingResultDTO {
        String cssFile;
        String htmlBody;
    }
}
