package team_alcoholic.jumo_server.v2.note.dto.response;

import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;
import team_alcoholic.jumo_server.v2.note.domain.Note;
import team_alcoholic.jumo_server.v2.note.domain.PurchaseNote;
import team_alcoholic.jumo_server.v2.note.domain.TastingNote;

@Getter @Setter
public class GeneralNoteRes {

    private String type;
    private PurchaseNoteRes purchaseNote;
    private TastingNoteRes tastingNote;

    public static GeneralNoteRes from(Note note, boolean isLiked) {
        GeneralNoteRes noteRes = new GeneralNoteRes();
        if (note instanceof PurchaseNote purchaseNote) {
            noteRes.setType(purchaseNote.getClass().getAnnotation(DiscriminatorValue.class).value());
            noteRes.setPurchaseNote(PurchaseNoteRes.from(purchaseNote, isLiked));
        }
        else if (note instanceof TastingNote tastingNote) {
            noteRes.setType(tastingNote.getClass().getAnnotation(DiscriminatorValue.class).value());
            noteRes.setTastingNote(TastingNoteRes.from(tastingNote, isLiked));
        }
        return noteRes;
    }

    public static GeneralNoteRes from(PurchaseNote note, boolean isLiked) {
        GeneralNoteRes noteRes = new GeneralNoteRes();
        noteRes.setType(note.getClass().getAnnotation(DiscriminatorValue.class).value());
        noteRes.setPurchaseNote(PurchaseNoteRes.from(note, isLiked));
        return noteRes;
    }

    public static GeneralNoteRes from(TastingNote note, boolean isLiked) {
        GeneralNoteRes noteRes = new GeneralNoteRes();
        noteRes.setType(note.getClass().getAnnotation(DiscriminatorValue.class).value());
        noteRes.setTastingNote(TastingNoteRes.from(note, isLiked));
        return noteRes;
    }
}
