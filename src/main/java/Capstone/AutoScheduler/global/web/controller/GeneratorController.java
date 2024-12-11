package Capstone.AutoScheduler.global.web.controller;


import Capstone.AutoScheduler.global.apiPayload.ApiResponse;
import Capstone.AutoScheduler.global.apiPayload.code.status.SuccessStatus;
import Capstone.AutoScheduler.global.converter.GeneratorConverter;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.repository.BookmarkRepository;
import Capstone.AutoScheduler.global.service.GeneratorService.GeneratorCommandService;
import Capstone.AutoScheduler.global.service.GeneratorService.GeneratorQueryService;
import Capstone.AutoScheduler.global.service.MemberService.MemberCommandService;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/generator")
@Slf4j
@Tag(name = "일정 생성기 API", description = "일정 생성기 관련 API입니다.")
public class GeneratorController {

    private final GeneratorCommandService generatorCommandService;
    private final GeneratorQueryService generatorQueryService;
    private final MemberCommandService memberCommandService;
    private final BookmarkRepository bookmarkRepository;

    // 일정 생성기 저장하기
    @PostMapping("/")
        @Operation(summary = "일정 생성기 생성하기", description = "일정 생성기를 생성합니다.")
    public ApiResponse<GeneratorResponseDTO.CreateGeneratorResultDTO> generatorCreate(
            @RequestParam Long memberId,
            //@RequestParam Long sourceId,
            @RequestBody GeneratorRequestDTO.CreateGeneratorRequestDTO request
    ){
        Generator newGenerator = generatorCommandService.createGenerator(memberId, request);
        return ApiResponse.onSuccess(
                SuccessStatus.GENERATOR_OK,
                GeneratorConverter.toCreateResultDTO(newGenerator)
        );
    }

    // 특정 일정 생성기 조회
    @GetMapping("/{generatorId}")
    @Operation(summary = "특정 일정 생성기 조회 API", description = "특정 일정 생성기를 조회합니다. Path variable로 조회할 일정 생성기의 ID를 입력하세요.")
    public ApiResponse<GeneratorResponseDTO.GeneratorDTO> findGenerator(@PathVariable Long generatorId) {
        Object request;
        Generator findGenerator = generatorQueryService.findById(generatorId);
        return ApiResponse.onSuccess(
                SuccessStatus.GENERATOR_OK,
                GeneratorConverter.toGeneratorDTO(findGenerator)
        );
    }

    // 전체 일정 생성기 리스트 조회
    @GetMapping("/list")
    @Operation(summary = "전체 일정 생성기 리스트 조회 API", description = "전체 일정 생성기 리스트를 조회합니다.")
    public ApiResponse<GeneratorResponseDTO.GeneratorPreviewListDTO> findGenerators() {
        List<Generator> generators = generatorQueryService.getGenerators();
        return ApiResponse.onSuccess(
                SuccessStatus.GENERATOR_OK,
                GeneratorConverter.toGeneratorPreviewListDTO(generators));
    }

    // 전체 일정 생성기 리스트 조회 (사용자의 북마크 상태 포함)
    @GetMapping("/list/{memberId}")
    @Operation(summary = "전체 일정 생성기 리스트 조회 (사용자의 북마크 상태 포함) API", description = "전체 일정 생성기 리스트를 조회하며 사용자가 북마크한 생성기를 표시합니다.")
    public ApiResponse<GeneratorResponseDTO.GeneratorPreviewListDTO> findGeneratorsWithBookmark(@PathVariable Long memberId) {
        List<Generator> generators = generatorQueryService.getGeneratorsWithBookmarkStatus(memberId);
        List<Long> bookmarkedGeneratorIds = bookmarkRepository.findBookmarkedGeneratorIdsByMemberId(memberId);
        return ApiResponse.onSuccess(
                SuccessStatus.GENERATOR_OK,
                GeneratorConverter.toGeneratorPreviewListDTOWithBookmark(generators, bookmarkedGeneratorIds));
    }


}
