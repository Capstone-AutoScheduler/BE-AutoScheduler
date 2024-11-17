package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.web.dto.Bookmark.BookmarkResponseDTO;

import java.time.LocalDateTime;

public class BookmarkConverter {

    // 북마크 추가
    public static BookmarkResponseDTO.CreateBookmarkResultDTO toCreateBookmarkResultDTO(Bookmark bookmark) {
        return BookmarkResponseDTO.CreateBookmarkResultDTO.builder()
                .bookmarkId(bookmark.getId())
                .memberId(bookmark.getMember().getMemberId())
                .generatorId(bookmark.getGenerator().getGeneratorId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    // Service
    public static Bookmark toBookmark(Member member, Generator generator) {
        return Bookmark.builder()
                .member(member)
                .generator(generator)
                .build();
    }

    // 북마크 삭제
    public static BookmarkResponseDTO.DeleteBookmarkResultDTO toDeleteBookmarkResultDTO(Long bookmarkId) {
        return BookmarkResponseDTO.DeleteBookmarkResultDTO.builder()
                .bookmarkId(bookmarkId)
                .build();
    }
}
