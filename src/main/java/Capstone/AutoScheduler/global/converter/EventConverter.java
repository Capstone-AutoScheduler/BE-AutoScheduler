package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.web.dto.Event.EventRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Event.EventResponseDTO;

public class EventConverter {

    public static Event toEvent(EventRequestDTO.CreateEventRequestDTO request) {
        return Event.builder()
                .eventTitle(request.getEventTitle())
                .eventBody(request.getEventBody())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    public static EventResponseDTO.CreateEventResultDTO toCreateResultDTO(Event event) {
        return EventResponseDTO.CreateEventResultDTO.builder()
                .eventId(event.getEventId())
                .memberId(event.getMember().getMemberId())
                .eventTitle(event.getEventTitle())
                .eventBody(event.getEventBody())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }

    public static EventResponseDTO.UpdateEventResultDTO UpdateEventResultDTO(Event event) {
        return EventResponseDTO.UpdateEventResultDTO.builder()
                .eventId(event.getEventId())
                .eventTitle(event.getEventTitle())
                .eventBody(event.getEventBody())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }


}
