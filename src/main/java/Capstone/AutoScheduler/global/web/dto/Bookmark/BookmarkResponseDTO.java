package Capstone.AutoScheduler.global.web.dto.Bookmark;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class BookmarkResponseDTO {
    // 북마크 추가
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBookmarkResultDTO {
        Long bookmarkId;
        Long memberId;
        Long generatorId;
        LocalDateTime createdAt;
    }

    // 북마크 삭제
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteBookmarkResultDTO {
        Long bookmarkId;
    }
}
