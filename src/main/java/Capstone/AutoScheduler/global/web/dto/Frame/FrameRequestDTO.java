package Capstone.AutoScheduler.global.web.dto.Frame;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrameRequestDTO {
    private Long id;
    private List<String> title;
    private List<DateDetailDTO> date;
    private List<DetailDTO> detail;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DateDetailDTO {
        private String type;
        private Long startBubbleId;
        private List<String> childOperations;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailDTO {
        private String type;
        private Long startBubbleId;
        private List<String> childOperations;
    }
}

