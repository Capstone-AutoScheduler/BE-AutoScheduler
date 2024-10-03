package Capstone.AutoScheduler.global.service.MemberService;

import Capstone.AutoScheduler.global.converter.MemberConverter;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import Capstone.AutoScheduler.global.web.dto.Member.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    @Override
    public Member createMember(MemberRequestDTO.CreateMemberRequestDTO request){
        boolean memberExists = memberRepository.existsByUserName(request.getUserName());
        if(memberExists){
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        Member getMember = memberRepository.findByUserName(request.getUserName());

        Member newMember = MemberConverter.toMember(request);
        Member savedMember = memberRepository.save(newMember);

        return savedMember;
    }


}
