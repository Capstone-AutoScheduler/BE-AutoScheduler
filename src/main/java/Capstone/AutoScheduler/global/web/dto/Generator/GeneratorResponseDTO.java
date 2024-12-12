package Capstone.AutoScheduler.global.web.dto.Generator;

import Capstone.AutoScheduler.global.domain.enums.TypeSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

public class GeneratorResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateGeneratorResultDTO {
        Long generatorId;
        Long memberId;
        String memberName;
        //Long sourceId;
        TypeSource sourceType;
        String generatorTitle;
        String generatorDetail;
        //String frames;
        //String mapping;
        // JSON 데이터를 List<Map<String, Object>>로 처리
        List<Map<String, Object>> frames;
        List<Map<String, Object>> mapping;
        String webUrl;
        Boolean loginRequired;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneratorDTO {
        Long generatorId;
        Long memberId;
        String memberName;
        //Long sourceId;
        TypeSource sourceType;
        String generatorTitle;
        String generatorDetail;
        //String frames;
        //String mapping;
        // JSON 데이터를 List<Map<String, Object>>로 처리
        List<Map<String, Object>> frames;
        List<Map<String, Object>> mapping;
        String webUrl;
        Boolean loginRequired;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneratorPreviewDTO {
        Long generatorId;
        Long memberId;
        String memberName;
        TypeSource sourceType;
        String generatorTitle;
        String generatorDetail;
        //String frames;
        //String mapping;
        // JSON 데이터를 List<Map<String, Object>>로 처리
        List<Map<String, Object>> frames;
        List<Map<String, Object>> mapping;
        String webUrl;
        Boolean loginRequired;
        boolean isBookmarked;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneratorPreviewListDTO {
        List<GeneratorPreviewDTO> generators;
    }



}
