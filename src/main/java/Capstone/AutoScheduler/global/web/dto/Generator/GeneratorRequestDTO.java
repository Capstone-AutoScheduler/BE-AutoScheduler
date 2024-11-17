package Capstone.AutoScheduler.global.web.dto.Generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GeneratorRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateGeneratorRequestDTO {
        private String generatorTitle;
        private String generatorDetail;
    }
}
