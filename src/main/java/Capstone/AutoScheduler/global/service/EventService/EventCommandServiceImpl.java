package Capstone.AutoScheduler.global.service.EventService;

import Capstone.AutoScheduler.global.converter.EventConverter;
import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.domain.entity.Generator;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.repository.EventRepository;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import Capstone.AutoScheduler.global.repository.GeneratorRepository;
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
    private final GeneratorRepository generatorRepository;

    @Override
    public Event createEvent(Long memberId, EventRequestDTO.CreateEventRequestDTO request) {
        Member member = findMemberById(memberId);

        Event newEvent = EventConverter.toEvent(request);
        newEvent.setMember(member);

        Event savedEvent = eventRepository.save(newEvent);
        log.info("Event created: {}", savedEvent);
        return savedEvent;
    }

    @Override
    public Event updateEvent(Long memberId, Long eventId, EventRequestDTO.UpdateEventDTO request) {
        Member member = findMemberById(memberId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found. ID: " + eventId));

        event.update(request);
        log.info("Event updated: {}", event);
        return event;
    }

    @Override
    public void deleteEvent(Long memberId, Long eventId) {
        Member member = findMemberById(memberId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found. ID: " + eventId));

        eventRepository.delete(event);
        log.info("Event deleted: {}", event);
    }

    @Override
    public Event createEventWithGenerator(Long memberId, Long generatorId, EventRequestDTO.CreateEventRequestDTO request) {
        Member member = findMemberById(memberId);
        Generator generator = findGeneratorById(generatorId);

        Event newEvent = EventConverter.toEvent(request);
        newEvent.setMember(member);
        newEvent.setGenerator(generator);

        Event savedEvent = eventRepository.save(newEvent);
        log.info("Event created with generator: {}", savedEvent);
        return savedEvent;
    }


    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found. ID: " + memberId));
    }

    private Generator findGeneratorById(Long generatorId) {
        return generatorRepository.findById(generatorId)
                .orElseThrow(() -> new IllegalArgumentException("Generator not found. ID: " + generatorId));
    }


}
