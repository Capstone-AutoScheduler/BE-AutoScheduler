package Capstone.AutoScheduler.global.web.dto.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class EventResponseDTO {


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateEventResultDTO {
        Long eventId;
        Long memberId;
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


}
