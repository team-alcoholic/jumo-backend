package team_alcoholic.jumo_server.v1.tastingnote.dto;

import lombok.Builder;
import team_alcoholic.jumo_server.v1.liquor.dto.LiquorRes;
import team_alcoholic.jumo_server.v1.tastingnote.domain.TastingNoteV1;
import team_alcoholic.jumo_server.v1.user.dto.UserRes;

public record TastingNoteResDTO(
        Long id,
        LiquorRes liquor,
        Integer noseScore,
        Integer palateScore,
        Integer finishScore,
        String noseMemo,
        String palateMemo,
        String finishMemo,
        String overallNote,
        String mood,
        String noseNotes,
        String palateNotes,
        String finishNotes,
        String createdAt,
        String updatedAt,
        UserRes user
) {

    @Builder
    public TastingNoteResDTO {
    }

    public static TastingNoteResDTO fromEntity(TastingNoteV1 tastingNote) {
        return TastingNoteResDTO.builder()
                .id(tastingNote.getId())
                .liquor(LiquorRes.from(tastingNote.getLiquor()))
                .noseScore(tastingNote.getNoseScore())
                .palateScore(tastingNote.getPalateScore())
                .finishScore(tastingNote.getFinishScore())
                .noseMemo(tastingNote.getNoseMemo())
                .palateMemo(tastingNote.getPalateMemo())
                .finishMemo(tastingNote.getFinishMemo())
                .overallNote(tastingNote.getOverallNote())
                .mood(tastingNote.getMood())
                .noseNotes(tastingNote.getNoseNotes())
                .palateNotes(tastingNote.getPalateNotes())
                .finishNotes(tastingNote.getFinishNotes())
                .createdAt(tastingNote.getCreatedAt().toString())
                .updatedAt(tastingNote.getUpdatedAt().toString())
                .user(UserRes.fromEntity(tastingNote.getUser()))
                .build();
    }
}