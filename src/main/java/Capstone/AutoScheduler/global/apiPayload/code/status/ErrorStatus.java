package Capstone.AutoScheduler.global.apiPayload.code.status;

import Capstone.AutoScheduler.global.apiPayload.code.BaseErrorCode;
import Capstone.AutoScheduler.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 회원 관려 에러 1000
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER_1001", "사용자가 없습니다."),
    MEMBER_NAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER_1002", "이름입력은 필수 입니다."),
    MEMBER_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_1003", "이미 존재하는 유저입니다."),
    MEMBER_ID_NULL(HttpStatus.BAD_REQUEST, "MEMBER_1004", "사용자 아이디는 필수 입니다."),
    MEMBER_ADMIN_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "MEMBER_1005", "관리자 권한이 없습니다."),
    MEMBER_LOGIN_FAIL(HttpStatus.BAD_REQUEST, "MEMBER_1006", "아이디나 비밀번호가 올바르지 않습니다."),
    MEMBER_WRONG_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER_1007", "이메일 형식이 올바르지 않습니다."),
    MEMBER_WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER_1008", "비밀번호 형식이 올바르지 않습니다."),
    MEMBER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_1009", "이미 가입된 이메일입니다."),
    MEMBER_NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_1010", "이미 존재하는 닉네임입니다."),
    MEMBER_SELF_FOLLOW(HttpStatus.BAD_REQUEST, "MEMBER_1100", "셀프 팔로우 기능은 제공하지 않습니다"),
    TECH_STACK_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_1111", "이미 존재하는 기술스택입니다."),
    PROJECT_ALREADY_EXISTS(HttpStatus.CONFLICT, "MEMBER_1112", "이미 존재하는 프로젝트입니다."),

    // 일정 관련 에러 2000

    // 일정 생성기 관련 에러 3000
    GENERATOR_CREATE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "GENERATOR_3001", "일정 생성기 저장 권한이 없습니다."),
    GENERATOR_UPDATE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "GENERATOR_3002", "일정 생성기 수정 권한이 없습니다."),
    GENERATOR_DELETE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "GENERATOR_3003", "일정 생성기 삭제 권한이 없습니다."),
    GENERATOR_NOT_EXIST_BY_TEAM(HttpStatus.BAD_REQUEST, "GENERATOR_3006", "해당 목록이 없습니다."),
    GENERATOR_NOT_EXIST_BY_PROJECT(HttpStatus.BAD_REQUEST, "GENERATOR_3007", "해당 목록이 없습니다."),
    GENERATOR_NOT_EXIST_BY_MEMBER(HttpStatus.BAD_REQUEST, "GENERATOR_3008", "해당 목록이 없습니다."),
    GENERATOR_NOT_FOUND(HttpStatus.BAD_REQUEST, "GENERATOR_3009", "일정 생성기가 없습니다."),

    // 북마크 관련 에러 4000
    BOOKMARK_CREATE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "BOOKMARK_4001", "북마크 추가 권한이 없습니다."),
    BOOKMARK_VIEW_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "BOOKMARK_4002", "북마크 게시글 리스트 조회 권한이 없습니다."),
    BOOKMARK_COUNT_ERROR(HttpStatus.BAD_REQUEST, "BOOKMARK_4003", "북마크 개수 계산에 에러가 있습니다."),
    BOOKMARK_DELETE_UNAUTHORIZED(HttpStatus.BAD_REQUEST, "BOOKMARK_4004", "북마크 삭제 권한이 없습니다."),
    BOOKMARK_NOT_FOUND(HttpStatus.BAD_REQUEST, "BOOKMARK_4005", "북마크가 없습니다."),
    BOOKMARK_DUPLICATE(HttpStatus.BAD_REQUEST, "BOOKMARK_4006", "이미 북마크가 되어있습니다."),

    // 카테고리 관련 에러 7000
    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "CATEGORY_7001", "카테고리가 없습니다."),
    // 회원별 관심 카테고리 관련 에러 8000
    MEMBERCATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBERCATEGORY_8001", "회원별 관심 카테고리가 없습니다."),

    // 프로젝트 관련 에러 9000
    PROJECT_NOT_FOUND(HttpStatus.BAD_REQUEST, "PROJECT_3009", "프로젝트가 없습니다.")


    ;



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}



