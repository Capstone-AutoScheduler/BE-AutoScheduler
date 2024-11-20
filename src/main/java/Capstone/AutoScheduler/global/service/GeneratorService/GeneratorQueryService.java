package Capstone.AutoScheduler.global.service.GeneratorService;

import Capstone.AutoScheduler.global.domain.entity.Generator;

public interface GeneratorQueryService {

    Generator findById(Long generatorId);
}
