package Capstone.AutoScheduler.global.service.GeneratorService;


import Capstone.AutoScheduler.global.converter.GeneratorConverter;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.repository.GeneratorRepository;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorResponseDTO;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Getter
public class GeneratorQueryServiceImpl implements GeneratorQueryService {

    private final GeneratorRepository generatorRepository;

//        @Override
//        public Generator findById(Long generatorId) {
//            Generator generator = generatorRepository.findById(generatorId).get();
//            return generatorRepository.save(generator);
//        }

    @Override
    public Generator findById(Long generatorId) {
        return generatorRepository.findById(generatorId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Generator가 존재하지 않습니다. ID: " + generatorId));
    }

    @Override
    public List<Generator> getGenerators() {
            return generatorRepository.findAllByOrderByGeneratorIdDesc();
        }


    @Override
    public List<Generator> getGeneratorsWithBookmarkStatus(Long memberId) {
        return generatorRepository.findAllWithBookmarkStatus(memberId).stream()
                .map(data -> (Generator) data[0]) // Generator 객체만 추출
                .toList();
    }



}
