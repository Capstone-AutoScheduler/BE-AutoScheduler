package Capstone.AutoScheduler.global.service.GeneratorService;

import Capstone.AutoScheduler.global.domain.entity.Generator;

import java.util.List;

public interface GeneratorQueryService {

    Generator findById(Long generatorId);
    List<Generator> getGenerators();
    //List<Generator> getGeneratorsWithBookmarkStatus(Long memberId);
}
