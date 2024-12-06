package Capstone.AutoScheduler.global.web.dto.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class EventResponseDTO {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateEventResultDTO {
        Long eventId;
        Long memberId;
        Long generatorId;
        String eventTitle;
        String eventBody;
        LocalDateTime startDate;
        LocalDateTime endDate;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateEventResultDTO {
        Long eventId;
        String eventTitle;
        String eventBody;
        LocalDateTime startDate;
        LocalDateTime endDate;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberEventPreviewDTO {
        Long memberId;
        Long generatorId;
        Long eventId;
        String eventTitle;
        String eventBody;
        LocalDateTime startDate;
        LocalDateTime endDate;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MemberEventPreviewListDTO {
        List<MemberEventPreviewDTO> events;
//        Integer listSize;
//        Integer totalPage;
//        Long totalElements;
//        boolean isFirst;
//        boolean isLast;
    }


}
