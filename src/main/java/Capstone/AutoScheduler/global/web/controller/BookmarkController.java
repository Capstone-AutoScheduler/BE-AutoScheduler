package Capstone.AutoScheduler.global.web.controller;

import Capstone.AutoScheduler.global.apiPayload.code.status.SuccessStatus;
import Capstone.AutoScheduler.global.apiPayload.ApiResponse;
import Capstone.AutoScheduler.global.converter.BookmarkConverter;
import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.service.BookmarkService.BookmarkCommandService;
import Capstone.AutoScheduler.global.web.dto.Bookmark.BookmarkResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
@Slf4j
@Tag(name = "일정 생성기 북마크 API", description = "일정 생성기 북마크 추가/삭제/개수 조회 관련 API입니다.")
public class BookmarkController {
    private final BookmarkCommandService bookmarkCommandService;

    // 북마크 추가
    @PostMapping("/add/{memberId}/{generatorId}")
    @Operation(summary = "북마크 추가 API", description = "bookmarks, memberId, generatorId")
    public ApiResponse<BookmarkResponseDTO.CreateBookmarkResultDTO> createBookmark(
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "generatorId") Long generatorId
    ) {
        Bookmark bookmark = bookmarkCommandService.createBookmark(memberId, generatorId);
        return ApiResponse.onSuccess(SuccessStatus.BOOKMARK_OK, BookmarkConverter.toCreateBookmarkResultDTO(bookmark));
    }

    // 북마크 삭제
    @DeleteMapping("/delete/{bookmarkId}")
    @Operation(summary = "북마크 삭제 API", description = "bookmarks, bookmarkId")
    public ApiResponse<BookmarkResponseDTO.DeleteBookmarkResultDTO> deleteBookmark(
            @PathVariable(name = "bookmarkId") Long bookmarkId
    ) {
        bookmarkCommandService.deleteBookmark(bookmarkId);
        return ApiResponse.onSuccess(SuccessStatus.BOOKMARK_OK, BookmarkConverter.toDeleteBookmarkResultDTO(bookmarkId));
    }
}
