package Capstone.AutoScheduler.global.service.BookmarkService;

import Capstone.AutoScheduler.global.apiPayload.code.status.ErrorStatus;
import Capstone.AutoScheduler.global.apiPayload.exception.GeneralException;
import Capstone.AutoScheduler.global.converter.BookmarkConverter;
import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.repository.BookmarkRepository;
import Capstone.AutoScheduler.global.repository.GeneratorRepository;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookmarkCommandServiceImpl implements BookmarkCommandService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final GeneratorRepository generatorRepository;

    @Override
    public Bookmark createBookmark(Long memberId, Long generatorId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Generator generator = generatorRepository.findById(generatorId).orElseThrow(() -> new GeneralException(ErrorStatus.GENERATOR_NOT_FOUND));
        // 중복 북마크 방지
        if (bookmarkRepository.existsByMemberAndGenerator(member, generator)) {
            throw new GeneralException(ErrorStatus.BOOKMARK_DUPLICATE);
        }
        Bookmark bookmark = BookmarkConverter.toBookmark(member, generator);
        return bookmarkRepository.save(bookmark);
    }

    // 북마크 삭제
    @Override
    public void deleteBookmark(Long bookmarkId) {

        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow(() -> new GeneralException(ErrorStatus.BOOKMARK_NOT_FOUND));
        bookmarkRepository.delete(bookmark);
    }



}
