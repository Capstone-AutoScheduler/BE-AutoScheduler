package Capstone.AutoScheduler.global.apiPayload.code.status;

import Capstone.AutoScheduler.global.apiPayload.code.BaseCode;
import Capstone.AutoScheduler.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 유저 관련 응답
    MEMBER_OK(HttpStatus.OK, "MEMBER_1000", "성공입니다."),
    // 이벤트 관련 응답
    EVENT_OK(HttpStatus.OK, "EVENT_2000", "성공입니다."),
    // 일정 생성기 관련 응답
    GENERATOR_OK(HttpStatus.OK, "GENERATOR_3000", "성공입니다."),
    // 북마크 관련 응답
    BOOKMARK_OK(HttpStatus.OK, "BOOKMARK_4000", "성공입니다.")

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}


