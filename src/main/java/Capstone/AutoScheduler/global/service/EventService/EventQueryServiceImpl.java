package Capstone.AutoScheduler.global.service.EventService;

import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.repository.EventRepository;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
