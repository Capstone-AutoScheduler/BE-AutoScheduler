package Capstone.AutoScheduler.global.service.BookmarkService;

import Capstone.AutoScheduler.global.repository.BookmarkRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BookmarkQueryServiceImpl implements BookmarkQueryService {
    private final BookmarkRepository bookmarkRepository;

    @Override
    public Long findBookmarkIdByMemberAndGenerator(Long memberId, Long generatorId) {
        return bookmarkRepository.findBookmarkIdByMemberAndGenerator(memberId, generatorId);
    }
}
