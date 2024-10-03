package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.web.dto.Member.MemberRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Member.MemberResponseDTO;
import org.springframework.stereotype.Component;

public class MemberConverter {

    public static Member toMember(MemberRequestDTO.CreateMemberRequestDTO request){
        return Member.builder()
                .name(request.getName())
                .userName(request.getUserName())
                .password(request.getPassword())
                .build();
    }

    public static MemberResponseDTO.CreateMemberResultDTO toCreateResultDTO(Member member){
        return MemberResponseDTO.CreateMemberResultDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .userName(member.getUserName())
                .build();
    }

    public static MemberResponseDTO.SignInResultDTO toSignInResultDTO(Member member){
        return MemberResponseDTO.SignInResultDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .userName(member.getUserName())
                .build();
    }

}
