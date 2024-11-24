package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.web.dto.CrawlingResponseDTO;
import java.util.List;

public class CrawlingConverter{
    // html의 body와 css파일 DTO로 변환
    public static CrawlingResponseDTO.GetCrawlingResultDTO toGetCrawlingResultDTO(List<String> htmlContent) {
        return CrawlingResponseDTO.GetCrawlingResultDTO.builder()
                .cssFile(htmlContent.get(0))
                .htmlBody(htmlContent.get(1))
                .build();
    }
}

