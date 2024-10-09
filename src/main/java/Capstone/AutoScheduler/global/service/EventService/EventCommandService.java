package Capstone.AutoScheduler.global.service.EventService;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.web.dto.Event.EventRequestDTO;

public interface EventCommandService {

    Event createEvent(Long memberId, EventRequestDTO.CreateEventRequestDTO request);
    Event updateEvent(Long memberId, Long eventId, EventRequestDTO.UpdateEventDTO request);
    void deleteEvent(Long memberId, Long eventId);
}
