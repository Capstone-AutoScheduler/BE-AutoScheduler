package Capstone.AutoScheduler.global.web.dto.Generator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneratorDTO {
        Long generatorId;
        Long memberId;
        Long sourceId;
        String generatorTitle;
        String generatorDetail;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneratorPreviewDTO {
        Long generatorId;
        Long memberId;
        Long sourceId;
        String generatorTitle;
        String generatorDetail;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneratorPreviewListDTO {
        List<GeneratorPreviewDTO> generators;
    }



}
