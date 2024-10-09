package Capstone.AutoScheduler.global.service.EventService;

import Capstone.AutoScheduler.global.converter.EventConverter;
import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.repository.EventRepository;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import Capstone.AutoScheduler.global.web.dto.Event.EventRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventCommandServiceImpl implements EventCommandService {

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    @Override
    public Event createEvent(Long memberId, EventRequestDTO.CreateEventRequestDTO request) {

        Event newEvent = EventConverter.toEvent(request);
        Member getMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));
        newEvent.setMember(getMember);

        Event savedEvent = eventRepository.save(newEvent);
        return savedEvent;
    }

    @Override
    public Event updateEvent(Long memberId, Long eventId, EventRequestDTO.UpdateEventDTO request) {
        Member getMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        Event updateEvent = eventRepository.findById(eventId).get();
        updateEvent.update(request);
        return updateEvent;
    }

    @Override
    public void deleteEvent(Long memberId, Long eventId) {
        Member getMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        Event deleteEvent = eventRepository.findById(eventId).get();
        eventRepository.delete(deleteEvent);
    }

}
