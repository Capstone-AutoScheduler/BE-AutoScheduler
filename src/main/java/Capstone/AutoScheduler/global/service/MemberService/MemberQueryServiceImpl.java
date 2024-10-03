package Capstone.AutoScheduler.global.service.MemberService;

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
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public Member signIn(MemberRequestDTO.SignInRequestDTO request){
        Member findMember = memberRepository.findByUserName(request.getUserName());
        if(findMember != null){
            if (findMember.getPassword().equals(request.getPassword())){
                return findMember;
            }
        }
        return null;
    }

}
