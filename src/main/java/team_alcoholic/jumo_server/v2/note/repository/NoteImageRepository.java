package team_alcoholic.jumo_server.v2.note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team_alcoholic.jumo_server.v2.note.domain.NoteImage;

public interface NoteImageRepository extends JpaRepository<NoteImage, Long> {
}
