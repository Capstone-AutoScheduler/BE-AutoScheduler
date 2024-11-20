package Capstone.AutoScheduler.global.service.GeneratorService;


import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.repository.GeneratorRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Getter
public class GeneratorQueryServiceImpl implements GeneratorQueryService {

        private final GeneratorRepository generatorRepository;

        @Override
        public Generator findById(Long generatorId) {
            Generator generator = generatorRepository.findById(generatorId).get();
            return generatorRepository.save(generator);
        }
}
