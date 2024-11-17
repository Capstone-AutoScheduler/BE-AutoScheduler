package Capstone.AutoScheduler.global.service.MemberService;

import Capstone.AutoScheduler.global.apiPayload.code.status.ErrorStatus;
import Capstone.AutoScheduler.global.apiPayload.exception.GeneralException;
import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.repository.BookmarkRepository;
import Capstone.AutoScheduler.global.repository.MemberRepository;
import Capstone.AutoScheduler.global.web.dto.Member.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;

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

    @Override
    public List<Bookmark> getBookmarkList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        List<Bookmark> memberBookmarks = bookmarkRepository.findAllByMember(member);

        return memberBookmarks;
    }

}
