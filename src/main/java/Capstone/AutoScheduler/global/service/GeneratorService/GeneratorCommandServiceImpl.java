package Capstone.AutoScheduler.global.service.GeneratorService;

import Capstone.AutoScheduler.global.converter.GeneratorConverter;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.domain.entity.Source;
import Capstone.AutoScheduler.global.repository.GeneratorRepository;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import Capstone.AutoScheduler.global.repository.SourceRepository;
import Capstone.AutoScheduler.global.service.BookmarkService.BookmarkCommandService;
import Capstone.AutoScheduler.global.web.dto.Generator.GeneratorRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GeneratorCommandServiceImpl implements GeneratorCommandService {
    private final MemberRepository memberRepository;
    private final GeneratorRepository generateRepository;
    private final SourceRepository sourceRepository;
    private final BookmarkCommandService bookmarkCommandService;

    @Override
    public Generator createGenerator(Long memberId, GeneratorRequestDTO.CreateGeneratorRequestDTO request) {
        Generator newGenerator = GeneratorConverter.toGenerator(request);
        Member getMember = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));
        newGenerator.setMember(getMember);

//        Source getSource = sourceRepository.findById(sourceId).orElseThrow(() -> new IllegalArgumentException("해당 소스가 존재하지 않습니다."));
//        newGenerator.setSource(getSource);

        Generator savedGenerator = generateRepository.save(newGenerator);

        // 자신이 만든 일정 생성기는 자동으로 북마크 추가
        bookmarkCommandService.createBookmark(memberId, savedGenerator.getGeneratorId());

        return savedGenerator;
    }
}
