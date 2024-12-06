package Capstone.AutoScheduler.global.service.EventService;

import Capstone.AutoScheduler.global.domain.entity.Event;

import java.util.List;

public interface EventQueryService {

    List<Event> getMemberEvent(Long memberId);
    List<Event> getMemberEventByDate(Long memberId, String date);
    Event getEvent(Long memberId, Long eventId);
    List<Event> findRecentEventsByMember(Long memberId, int limit);
}
