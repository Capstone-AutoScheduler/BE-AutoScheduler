package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorResponseDTO;

public class GeneratorConverter {

    public static Generator toGenerator(GeneratorRequestDTO.CreateGeneratorRequestDTO request) {
        return Generator.builder()
                .generatorTitle(request.getGeneratorTitle())
                .generatorDetail(request.getGeneratorDetail())
                .build();
    }

    public static GeneratorResponseDTO.CreateGeneratorResultDTO toCreateResultDTO(Generator generator) {
        return GeneratorResponseDTO.CreateGeneratorResultDTO.builder()
                .generatorId(generator.getGeneratorId())
                .memberId(generator.getMember().getMemberId())
                .sourceId(generator.getSource().getSourceId())
                .generatorTitle(generator.getGeneratorTitle())
                .generatorDetail(generator.getGeneratorDetail())
                .build();
    }

}
