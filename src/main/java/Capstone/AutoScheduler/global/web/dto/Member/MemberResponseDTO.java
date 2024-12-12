package Capstone.AutoScheduler.global.web.dto.Member;

import Capstone.AutoScheduler.global.domain.enums.TypeSource;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
        private String memberName;
        private Long bookmarkId;
        private Long generatorId;
        private String generatorTitle;
        private String generatorDetail;
        private TypeSource sourceType;
        private List<Map<String, Object>> frames;
        private List<Map<String, Object>> mapping;
        private String webUrl;
        private boolean isBookmarked;
        private boolean loginRequired;
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
