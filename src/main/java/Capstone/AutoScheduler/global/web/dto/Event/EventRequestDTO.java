package Capstone.AutoScheduler.global.web.dto.Event;

import lombok.*;

import java.time.LocalDateTime;

public class EventRequestDTO {


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateEventRequestDTO {
        private String eventTitle;
        private String eventBody;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateEventDTO {
        private String eventTitle;
        private String eventBody;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }

}
