package Capstone.AutoScheduler.global.web.dto.Event;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class EventRequestDTO {


    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateEventRequestDTO {
        private String eventTitle;
        private String eventBody;
        private String eventColor;
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
        private String eventColor;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateMultipleEventsRequestDTO {
        private List<CreateEventRequestDTO> events; // 여러 이벤트 생성 요청
    }


}
