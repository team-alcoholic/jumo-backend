package team_alcoholic.jumo_server.v2.note.dto.response;

import lombok.Getter;
import lombok.Setter;
import team_alcoholic.jumo_server.v2.aroma.dto.AromaRes;
import team_alcoholic.jumo_server.v2.liquor.dto.LiquorSimpleRes;
import team_alcoholic.jumo_server.v2.note.domain.NoteAroma;
import team_alcoholic.jumo_server.v2.note.domain.NoteImage;
import team_alcoholic.jumo_server.v2.note.domain.TastingNote;
import team_alcoholic.jumo_server.v2.user.dto.UserRes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TastingNoteRes extends NoteRes {

    private LocalDate tastingAt;
    private String method;
    private String place;
    private List<AromaRes> noteAromas = new ArrayList<>();
    private Integer score;
    private Boolean isDetail;
    private String content;
    private String nose;
    private String palate;
    private String finish;

    public static TastingNoteRes from(TastingNote note, boolean isLiked) {
        TastingNoteRes tastingNoteRes = new TastingNoteRes();

        tastingNoteRes.setId(note.getId());
        tastingNoteRes.setCreatedAt(note.getCreatedAt());
        tastingNoteRes.setUpdatedAt(note.getUpdatedAt());
        tastingNoteRes.setUser(UserRes.from(note.getUser()));
        tastingNoteRes.setLiquor(LiquorSimpleRes.from(note.getLiquor()));
        for (NoteImage noteImage : note.getNoteImages()) {
            tastingNoteRes.getNoteImages().add(NoteImageRes.from(noteImage));
        }
        tastingNoteRes.setLikes(note.getLikes());
        tastingNoteRes.setComments(note.getComments());
        tastingNoteRes.setLiked(isLiked);

        tastingNoteRes.setTastingAt(note.getTastingAt());
        tastingNoteRes.setMethod(note.getMethod());
        tastingNoteRes.setPlace(note.getPlace());
        for (NoteAroma noteAroma : note.getNoteAromas()) {
            tastingNoteRes.getNoteAromas().add(AromaRes.from(noteAroma.getAroma()));
        }
        tastingNoteRes.setScore(note.getScore());
        tastingNoteRes.setContent(note.getContent());
        tastingNoteRes.setIsDetail(note.getIsDetail());
        tastingNoteRes.setNose(note.getNose());
        tastingNoteRes.setPalate(note.getPalate());
        tastingNoteRes.setFinish(note.getFinish());

        return tastingNoteRes;
    }
}
