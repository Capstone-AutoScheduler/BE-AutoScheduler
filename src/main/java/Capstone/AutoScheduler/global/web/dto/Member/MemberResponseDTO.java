package Capstone.AutoScheduler.global.web.dto.Member;

import lombok.*;

import java.time.LocalDateTime;

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


}
