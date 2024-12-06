package Capstone.AutoScheduler.global.web.controller;

import Capstone.AutoScheduler.global.apiPayload.ApiResponse;
import Capstone.AutoScheduler.global.apiPayload.code.status.SuccessStatus;
import Capstone.AutoScheduler.global.converter.EventConverter;
import Capstone.AutoScheduler.global.domain.entity.Event;
import Capstone.AutoScheduler.global.service.EventService.EventCommandService;
import Capstone.AutoScheduler.global.service.EventService.EventQueryService;
import Capstone.AutoScheduler.global.service.MemberService.MemberCommandService;
import Capstone.AutoScheduler.global.web.dto.Event.EventRequestDTO;
import Capstone.AutoScheduler.global.web.dto.Event.EventResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@RequestMapping("/event")
@Slf4j
//@Tag(name = "이벤트 일정 API", description = "이벤트 일정 생성/수정/삭제/조회 관련 API입니다.")
public class EventController {
    private final EventCommandService eventCommandService;
    private final EventQueryService eventQueryService;
    private final MemberCommandService memberCommandService;

    // 이벤트 일정 생성하기
    @PostMapping("/")
    @Operation(summary = "이벤트 일정 생성하기", description = "이벤트 일정을 생성합니다.")
    public ApiResponse<EventResponseDTO.CreateEventResultDTO> createEvent(@RequestParam Long memberId, @RequestBody EventRequestDTO.CreateEventRequestDTO request) {
        Event newEvent = eventCommandService.createEvent(memberId, request);
        return ApiResponse.onSuccess(SuccessStatus.EVENT_OK, EventConverter.toCreateResultDTO(newEvent));
    }

    // 이벤트 일정 수정하기
    @PatchMapping("/{eventId}")
    @Operation(summary = "이벤트 일정 수정 API", description = "이벤트 일정을 수정합니다.")
    public ApiResponse<EventResponseDTO.UpdateEventResultDTO> updateEvent(@RequestParam Long memberId, @RequestBody EventRequestDTO.UpdateEventDTO request, @PathVariable Long eventId) {
        return ApiResponse.onSuccess(SuccessStatus.EVENT_OK,
                EventConverter.UpdateEventResultDTO(eventCommandService.updateEvent(memberId, eventId, request)));
    }

    // 이벤트 일정 삭제하기
    @DeleteMapping("/{eventId}")
    @Operation(summary = "이벤트 일정 삭제 API", description = "이벤트 일정을 삭제합니다.")
    public ApiResponse<?> deleteEvent(@RequestParam Long memberId, @PathVariable Long eventId) {
        eventCommandService.deleteEvent(memberId, eventId);
        return ApiResponse.onSuccess(SuccessStatus.EVENT_OK, null);
    }

    // 사용자의 이벤트 일정 전체 리스트 조회하기
    @GetMapping("/member/{memberId}")
    @Operation(summary = "사용자의 이벤트 일정 리스트 조회 API", description = "사용자의 이벤트 일정 전체 리스트를 조회합니다.")
    public ApiResponse<EventResponseDTO.MemberEventPreviewListDTO> findEventsByMember(@PathVariable Long memberId) {
        List<Event> memberEventList = eventQueryService.getMemberEvent(memberId);
        return ApiResponse.onSuccess(SuccessStatus.EVENT_OK, EventConverter.toMemberEventPreviewListDTO(memberEventList));
    }

    // 사용자의 날짜별 이벤트 일정 전체 리스트 조회하기
    @GetMapping("/member/{memberId}/date/{date}")
    @Operation(summary = "사용자의 날짜별 이벤트 일정 리스트 조회 API", description = "사용자의 날짜별 이벤트 일정 전체 리스트를 조회합니다.")
    public ApiResponse<EventResponseDTO.MemberEventPreviewListDTO> findEventsByMemberAndDate(@PathVariable Long memberId, @PathVariable String date) {
        List<Event> memberEventList = eventQueryService.getMemberEventByDate(memberId, date);
        return ApiResponse.onSuccess(SuccessStatus.EVENT_OK, EventConverter.toMemberEventPreviewListDTO(memberEventList));
    }

    // 사용자의 특정 이벤트 조회하기
    @GetMapping("/member/{memberId}/event/{eventId}")
    @Operation(summary = "사용자의 특정 이벤트 일정 세부사항 조회 API", description = "사용자의 특정 이벤트 일정을 조회합니다.")
    public ApiResponse<EventResponseDTO.MemberEventPreviewDTO> findEventByMemberAndEvent(@PathVariable Long memberId, @PathVariable Long eventId) {
        Event event = eventQueryService.getEvent(memberId, eventId);
        return ApiResponse.onSuccess(SuccessStatus.EVENT_OK, EventConverter.toMemberEventPreviewDTO(event));
    }

    // 자동 생성된 모든 일정 저장하기
    @PostMapping("/multipleEvents/{memberId}/{generatorId}")
    @Operation(summary = "자동 생성된 모든 일정 저장하기", description = "일정 생성기에서 자동 생성된 개별 일정을 모두 저장합니다.")
    public ApiResponse<List<EventResponseDTO.CreateEventResultDTO>> createMultipleEvents(@PathVariable Long memberId, @PathVariable Long generatorId,
                                                                                         @RequestBody EventRequestDTO.CreateMultipleEventsRequestDTO request) {
        List<EventResponseDTO.CreateEventResultDTO> createdEvents = new ArrayList<>();

        for (EventRequestDTO.CreateEventRequestDTO eventRequest : request.getEvents()) {
            Event newEvent = eventCommandService.createEventWithGenerator(memberId, generatorId, eventRequest);
            createdEvents.add(EventConverter.toCreateResultDTO(newEvent));
        }
        return ApiResponse.onSuccess(SuccessStatus.EVENT_OK, createdEvents);
    }



}
