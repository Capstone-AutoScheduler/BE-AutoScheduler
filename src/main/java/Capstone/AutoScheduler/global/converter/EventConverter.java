package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.web.dto.Event.EventRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Event.EventResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                .generatorId(event.getGenerator() != null ? event.getGenerator().getGeneratorId() : null) // generatorId 추가
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

    public static EventResponseDTO.MemberEventPreviewDTO toMemberEventPreviewDTO(Event event) {
        return EventResponseDTO.MemberEventPreviewDTO.builder()
                .memberId(event.getMember().getMemberId())
                .generatorId(event.getGenerator() != null ? event.getGenerator().getGeneratorId() : null) // generatorId 추가
                .eventId(event.getEventId())
                .eventTitle(event.getEventTitle())
                .eventBody(event.getEventBody())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }

    public static EventResponseDTO.MemberEventPreviewListDTO toMemberEventPreviewListDTO(
            List<Event> eventList
    ){
        List<EventResponseDTO.MemberEventPreviewDTO> memberEventPreviewDTOList = IntStream.range(0, eventList.size())
                .mapToObj(i->toMemberEventPreviewDTO(eventList.get(i)))
                .collect(Collectors.toList());
        return EventResponseDTO.MemberEventPreviewListDTO.builder()
                .events(memberEventPreviewDTOList)
                .build();
    }


}
