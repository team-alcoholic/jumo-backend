package team_alcoholic.jumo_server.v2.note.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team_alcoholic.jumo_server.global.common.service.CommonUtilService;
import team_alcoholic.jumo_server.v1.liquor.domain.Liquor;
import team_alcoholic.jumo_server.v1.liquor.exception.LiquorNotFoundException;
import team_alcoholic.jumo_server.v1.liquor.repository.LiquorRepository;
import team_alcoholic.jumo_server.v2.note.domain.NoteImage;
import team_alcoholic.jumo_server.v2.note.domain.PurchaseNote;
import team_alcoholic.jumo_server.v2.note.domain.TastingNote;
import team_alcoholic.jumo_server.v2.note.dto.request.PurchaseNoteCreateReq;
import team_alcoholic.jumo_server.v2.note.dto.request.TastingNoteCreateReq;
import team_alcoholic.jumo_server.v2.note.dto.response.PurchaseNoteRes;
import team_alcoholic.jumo_server.v2.note.dto.response.TastingNoteRes;
import team_alcoholic.jumo_server.v2.note.repository.NoteImageRepository;
import team_alcoholic.jumo_server.v2.note.repository.PurchaseNoteRepository;
import team_alcoholic.jumo_server.v2.note.repository.TastingNoteRepository;
import team_alcoholic.jumo_server.v2.user.domain.NewUser;
import team_alcoholic.jumo_server.v2.user.repository.UserRepository;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final PurchaseNoteRepository purchaseNoteRepository;
    private final TastingNoteRepository tastingNoteRepository;
    private final UserRepository userRepository;
    private final LiquorRepository liquorRepository;
    private final NoteImageRepository noteImageRepository;
    private final CommonUtilService commonUtilService;

    /**
     * 구매 노트를 생성하는 메서드
     * @param userUuid
     * @param noteCreateReq
     */
    public PurchaseNoteRes createPurchaseNote(UUID userUuid, PurchaseNoteCreateReq noteCreateReq) throws IOException {
        // PurchaseNote 엔티티 생성 및 저장
        NewUser user = userRepository.findByUserUuid(userUuid);
        Liquor liquor = liquorRepository.findById(noteCreateReq.getLiquorId())
            .orElseThrow(() -> new LiquorNotFoundException(noteCreateReq.getLiquorId()));
        PurchaseNote purchaseNote = new PurchaseNote(noteCreateReq, user, liquor);
        purchaseNoteRepository.save(purchaseNote);

        // NoteImage 엔티티 생성 및 저장
        for (MultipartFile requestImage : noteCreateReq.getNoteImages()) {
            String imageUrl = commonUtilService.uploadImageToS3(requestImage, "note-image/");
            NoteImage noteImage = new NoteImage(purchaseNote, requestImage.getOriginalFilename(), imageUrl);
            noteImageRepository.save(noteImage);
        }

        // dto 변환 후 반환
        return PurchaseNoteRes.from(purchaseNote);
    }

    /**
     * 감상 노트를 생성하는 메서드
     * @param userUuid
     * @param noteCreateReq
     */
    public TastingNoteRes createTastingNote(UUID userUuid, TastingNoteCreateReq noteCreateReq) throws IOException {
        // TastingNote 엔티티 생성 및 저장
        NewUser user = userRepository.findByUserUuid(userUuid);
        Liquor liquor = liquorRepository.findById(noteCreateReq.getLiquorId())
            .orElseThrow(() -> new LiquorNotFoundException(noteCreateReq.getLiquorId()));
        TastingNote tastingNote = new TastingNote(noteCreateReq, user, liquor);
        TastingNote result = tastingNoteRepository.save(tastingNote);

        // NoteImage 엔티티 생성 및 저장
        for (MultipartFile requestImage : noteCreateReq.getNoteImages()) {
            String imageUrl = commonUtilService.uploadImageToS3(requestImage, "note-image/");
            NoteImage noteImage = new NoteImage(tastingNote, requestImage.getOriginalFilename(), imageUrl);
            noteImageRepository.save(noteImage);
        }

        // dto 변환 후 반환
        return TastingNoteRes.from(result);
    }
}
