package team_alcoholic.jumo_server.v2.note.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team_alcoholic.jumo_server.v1.liquor.domain.Liquor;
import team_alcoholic.jumo_server.v2.liquor.domain.NewLiquor;
import team_alcoholic.jumo_server.v2.note.domain.Note;
import team_alcoholic.jumo_server.v2.user.domain.NewUser;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * 노트 상세 조회
     * @param id 노트 id
     */
    @EntityGraph(attributePaths = {"user", "liquor", "noteImages"})
    @Query("select n from Note n left join fetch n.noteImages ni where n.id=:id order by ni.id")
    Optional<Note> findDetailById(Long id);

    /**
     * 최신순 노트 페이지네이션 조회
     * @param cursor 마지막으로 조회한 노트의 id
     * @param pageable paging
     */
    @EntityGraph(attributePaths = {"user", "liquor", "noteImages"})
    @Query("select n from Note n left join fetch n.noteImages ni where n.id < :cursor order by n.id desc, ni.id")
    List<Note> findListByCursor(Long cursor, Pageable pageable);

    /**
     * 유형별 노트 상세 조회 전 간략한 목록 조회
     * 최신순 노트 페이지네이션 조회: 첫 페이지
     * @param pageable paging
     */
    @Query("select n from Note n order by n.id desc")
    List<Note> findSimpleList(Pageable pageable);

    /**
     * 유형별 노트 상세 조회 전 간략한 목록 조회
     * 최신순 노트 페이지네이션 조회
     * @param cursor 마지막으로 조회한 노트의 id
     * @param pageable paging
     */
    @Query("select n from Note n where n.id < :cursor order by n.id desc")
    List<Note> findSimpleListByCursor(Long cursor, Pageable pageable);

    /**
     * 최신순 노트 페이지네이션 조회: 첫 페이지
     * @param pageable paging
     */
    @EntityGraph(attributePaths = {"user", "liquor", "noteImages"})
    @Query("select n from Note n left join fetch n.noteImages ni order by n.id desc, ni.id")
    List<Note> findList(Pageable pageable);

    /**
     * 사용자별 노트 조회
     * @param user 사용자
     */
    @EntityGraph(attributePaths = {"user", "liquor", "noteImages"})
    @Query("select n from Note n left join fetch n.noteImages ni where n.user = :user order by n.id desc, ni.id")
    List<Note> findListByUser(NewUser user);

    /**
     * 주류별 노트 조회
     * @param liquor 주류
     */
    @EntityGraph(attributePaths = {"user", "liquor", "noteImages"})
    @Query("select n from Note n left join fetch n.noteImages ni where n.liquor = :liquor order by n.id desc, ni.id")
    List<Note> findListByLiquor(NewLiquor liquor);

    /**
     * 사용자별 주류별 노트 조회
     * @param user 사용자
     * @param liquor 주류
     */
    @EntityGraph(attributePaths = {"user", "liquor", "noteImages"})
    @Query("select n from Note n left join fetch n.noteImages ni where n.user = :user and n.liquor = :liquor order by n.id desc, ni.id")
    List<Note> findListByUserAndLiquor(NewUser user, NewLiquor liquor);
}
