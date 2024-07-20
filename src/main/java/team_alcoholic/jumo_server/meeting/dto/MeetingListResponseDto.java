package team_alcoholic.jumo_server.meeting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MeetingListResponseDto {
    private List<MeetingListDto> meetings;
    private Long lastId;
    private boolean eof;
}
