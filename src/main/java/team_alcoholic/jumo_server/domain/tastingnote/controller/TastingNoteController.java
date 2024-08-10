package team_alcoholic.jumo_server.domain.tastingnote.controller;

import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import team_alcoholic.jumo_server.domain.tastingnote.dto.TastingNoteReqDTO;
import team_alcoholic.jumo_server.domain.tastingnote.dto.TastingNoteResDTO;
import team_alcoholic.jumo_server.domain.tastingnote.dto.TastingNoteSimilarResDto;
import team_alcoholic.jumo_server.domain.tastingnote.service.TastingNoteService;
import team_alcoholic.jumo_server.domain.user.domain.User;
import team_alcoholic.jumo_server.domain.user.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class TastingNoteController {

    private final TastingNoteService tastingNoteService;
    private final UserService userService;

//    @Autowired
//    public TastingNoteController(TastingNoteService tastingNoteService) {
//        this.tastingNoteService = tastingNoteService;
//    }

    @GetMapping("/similar-tasting-notes")
    public TastingNoteSimilarResDto getSimilarTastingNotes(
            @RequestParam String keyword,
            @RequestParam(required = false) List<String> exclude,
            @RequestParam(defaultValue = "5") int limit) {

        return tastingNoteService.findSimilarTastingNotes(keyword, exclude, limit);
    }

    @PostMapping("/tasting-notes")
    public ResponseEntity<Long> saveTastingNote(@RequestBody @Valid TastingNoteReqDTO tastingNoteReqDTO,
                                                @AuthenticationPrincipal OAuth2User oAuth2User) {

//        long userId = Long.parseLong(hello.get("id").toString());

        long userId = Long.parseLong(Objects.requireNonNull(oAuth2User.getAttribute("id")).toString());
        User user = userService.findUserById(userId);


        Long tastingNoteId = tastingNoteService.saveTastingNote(tastingNoteReqDTO, user);
        return new ResponseEntity<>(tastingNoteId, HttpStatus.CREATED);
    }

    @GetMapping("/tasting-notes/{id}")
    public ResponseEntity<TastingNoteResDTO> getTastingNoteById(@PathVariable Long id, HttpServletRequest request) {
        TastingNoteResDTO tastingNoteResDTO = tastingNoteService.getTastingNoteById(id);

        return new ResponseEntity<>(tastingNoteResDTO, HttpStatus.OK);
    }
}