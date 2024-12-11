package Capstone.AutoScheduler.global.converter;

import Capstone.AutoScheduler.global.domain.entity.Bookmark;
import Capstone.AutoScheduler.global.domain.entity.Member;
import Capstone.AutoScheduler.global.web.dto.Member.MemberRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Member.MemberResponseDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public static MemberResponseDTO.BookmarkPreviewDTO toBookmarkPreviewDTO(Bookmark bookmark) {
        return MemberResponseDTO.BookmarkPreviewDTO.builder()
                .memberId(bookmark.getMember().getMemberId())
                .memberName(bookmark.getMember().getName())
                .bookmarkId(bookmark.getId())
                .generatorId(bookmark.getGenerator().getGeneratorId())
                .generatorTitle(bookmark.getGenerator().getGeneratorTitle())
                .generatorDetail(bookmark.getGenerator().getGeneratorDetail())
                .sourceType(bookmark.getGenerator().getSourceType())
                .frames(bookmark.getGenerator().getFrames())
                .mapping(bookmark.getGenerator().getMapping())
                .webUrl(bookmark.getGenerator().getWebUrl())
                .isBookmarked(true)
                .createdAt(bookmark.getCreatedAt())
                .build();
    }

    public static MemberResponseDTO.BookmarkPreviewListDTO toBookmarkPreviewListDTO(List<Bookmark> bookmarkList) {
        List<MemberResponseDTO.BookmarkPreviewDTO> bookmarkPreviewDTOList = IntStream.range(0, bookmarkList.size())
                .mapToObj(i -> toBookmarkPreviewDTO(bookmarkList.get(i)))
                .collect(Collectors.toList());
        return MemberResponseDTO.BookmarkPreviewListDTO.builder()
                .bookmarkList(bookmarkPreviewDTOList)
                .build();
    }

}
