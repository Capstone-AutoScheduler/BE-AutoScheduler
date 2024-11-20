package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static GeneratorResponseDTO.GeneratorDTO toGeneratorDTO(Generator generator) {
        return GeneratorResponseDTO.GeneratorDTO.builder()
                .generatorId(generator.getGeneratorId())
                .memberId(generator.getMember().getMemberId())
                .sourceId(generator.getSource().getSourceId())
                .generatorTitle(generator.getGeneratorTitle())
                .generatorDetail(generator.getGeneratorDetail())
                .build();
    }

    public static GeneratorResponseDTO.GeneratorPreviewDTO toGeneratorPreviewDTO(Generator generator) {
        return GeneratorResponseDTO.GeneratorPreviewDTO.builder()
                .generatorId(generator.getGeneratorId())
                .memberId(generator.getMember().getMemberId())
                .sourceId(generator.getSource().getSourceId())
                .generatorTitle(generator.getGeneratorTitle())
                .generatorDetail(generator.getGeneratorDetail())
                .build();
    }

    public static GeneratorResponseDTO.GeneratorPreviewListDTO toGeneratorPreviewListDTO(List<Generator> generatorList) {
        List<GeneratorResponseDTO.GeneratorPreviewDTO> generatorPreviewDTOList = IntStream.range(0, generatorList.size())
                .mapToObj(i->toGeneratorPreviewDTO(generatorList.get(i)))
                .collect(Collectors.toList());
        return GeneratorResponseDTO.GeneratorPreviewListDTO.builder()
                .generators(generatorPreviewDTOList)
                .build();
    }

}
