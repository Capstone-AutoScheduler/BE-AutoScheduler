package Capstone.AutoScheduler.global.service.GeneratorService;

import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorRequestDTO;

public interface GeneratorCommandService {
    // 일정 생성기 저장
    Generator createGenerator(Long memberId, GeneratorRequestDTO.CreateGeneratorRequestDTO request);



}
