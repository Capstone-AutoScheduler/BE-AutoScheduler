package Capstone.AutoScheduler.global.web.controller;


import Capstone.AutoScheduler.global.apiPayload.ApiResponse;
import Capstone.AutoScheduler.global.apiPayload.code.status.SuccessStatus;
import Capstone.AutoScheduler.global.converter.GeneratorConverter;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.service.GeneratorService.GeneratorCommandService;
import Capstone.AutoScheduler.global.service.MemberService.MemberCommandService;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/generator")
@Slf4j
@Tag(name = "일정 생성기 API", description = "일정 생성기 관련 API입니다.")
public class GeneratorController {

    private final GeneratorCommandService generatorCommandService;
    private final MemberCommandService memberCommandService;

    // 일정 생성기 저장하기
    @PostMapping("/")
    @Operation(summary = "일정 생성기 생성하기", description = "일정 생성기를 생성합니다.")
    public ApiResponse<GeneratorResponseDTO.CreateGeneratorResultDTO> generatorCreate(
            @RequestParam Long memberId,
            @RequestParam Long sourceId,
            @RequestBody GeneratorRequestDTO.CreateGeneratorRequestDTO request
    ){
        Generator newGenerator = generatorCommandService.createGenerator(memberId, sourceId, request);
        return ApiResponse.onSuccess(
                SuccessStatus.GENERATOR_OK,
                GeneratorConverter.toCreateResultDTO(newGenerator)
        );
    }

}
