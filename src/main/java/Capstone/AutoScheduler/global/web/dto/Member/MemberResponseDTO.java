package Capstone.AutoScheduler.global.web.dto.Member;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateMemberResultDTO {
        private Long memberId;
        private String name;
        private String userName;
        private LocalDateTime createdAt;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInResultDTO {
        private Long memberId;
        private String name;
        private String userName;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkPreviewDTO {
        private Long memberId;
        private Long bookmarkId;
        private Long generatorId;
        private String generatorTitle;
        private String generatorDetail;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookmarkPreviewListDTO {
        private List<MemberResponseDTO.BookmarkPreviewDTO> bookmarkList;
    }




}
