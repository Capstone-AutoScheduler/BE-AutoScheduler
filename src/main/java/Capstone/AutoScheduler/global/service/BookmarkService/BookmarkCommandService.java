package Capstone.AutoScheduler.global.service.BookmarkService;

import Capstone.AutoScheduler.global.domain.entity.Bookmark;

public interface BookmarkCommandService {
    // 북마크 추가
    Bookmark createBookmark(Long memberId, Long generatorId);
    // 북마크 삭제
    void deleteBookmark(Long bookmarkId);
}
