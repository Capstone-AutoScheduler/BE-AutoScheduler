package Capstone.AutoScheduler.global.service.BookmarkService;

public interface BookmarkQueryService {
    Long findBookmarkIdByMemberAndGenerator(Long memberId, Long generatorId);
}
