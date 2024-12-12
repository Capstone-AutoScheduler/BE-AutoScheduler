package Capstone.AutoScheduler.global.web.dto.Generator;

import Capstone.AutoScheduler.global.domain.enums.TypeSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

public class GeneratorRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateGeneratorRequestDTO {
        private String generatorTitle;
        private String generatorDetail;
        //private String frames;
        //private String mapping;
        // JSON 데이터를 리스트로 처리
        private List<Map<String, Object>> frames;
        private List<Map<String, Object>> mapping;
        private TypeSource sourceType;
        private String webUrl;
        private Boolean loginRequired;
    }
}
