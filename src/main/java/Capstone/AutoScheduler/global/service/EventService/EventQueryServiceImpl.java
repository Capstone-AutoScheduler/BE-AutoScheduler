package Capstone.AutoScheduler.global.service.EventService;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.repository.EventRepository;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class EventQueryServiceImpl implements EventQueryService {

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;

    @Override
    public List<Event> getMemberEvent(Long memberId) {
        Member getMember = memberRepository.findById(memberId).get();
        List<Event> MemberEventList = eventRepository.findAllByMember(getMember);

        return MemberEventList;
    }

    @Override
    public List<Event> getMemberEventByDate(Long memberId, String date) {
        Member getMember = memberRepository.findById(memberId).get();
        List<Event> MemberEventList = eventRepository.findAllByMember(getMember);
        List<Event> MemberEventListByDate = new ArrayList<>();

        for (Event event : MemberEventList) {
            if (event.getStartDate().toString().contains(date)) {
                MemberEventListByDate.add(event);
            }
        }
        return MemberEventListByDate;
    }

    @Override
    public Event getEvent(Long memberId, Long eventId) {
        Member getMember = memberRepository.findById(memberId).get();
        Event getEvent = eventRepository.findById(eventId).get();

        if (getEvent.getMember().equals(getMember)) {
            return getEvent;
        } else {
            return null;
        }
    }
}
