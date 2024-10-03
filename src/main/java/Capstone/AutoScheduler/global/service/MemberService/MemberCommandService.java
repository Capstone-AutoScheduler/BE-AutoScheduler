package Capstone.AutoScheduler.global.service.MemberService;

import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.web.dto.Member.MemberRequestDTO;

public interface MemberCommandService {

    Member createMember(MemberRequestDTO.CreateMemberRequestDTO request);
}
