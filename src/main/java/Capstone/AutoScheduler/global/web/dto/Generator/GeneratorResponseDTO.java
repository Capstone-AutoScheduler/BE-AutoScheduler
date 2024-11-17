package Capstone.AutoScheduler.global.web.dto.Generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GeneratorResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateGeneratorResultDTO {
        Long generatorId;
        Long memberId;
        Long sourceId;
        String generatorTitle;
        String generatorDetail;
    }
}
