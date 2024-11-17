package Capstone.AutoScheduler.global.service.MemberService;

import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.web.dto.Member.MemberRequestDTO;

import java.util.List;

public interface MemberQueryService {
    Member signIn(MemberRequestDTO.SignInRequestDTO request);

    List<Bookmark> getBookmarkList(Long memberId);
}
