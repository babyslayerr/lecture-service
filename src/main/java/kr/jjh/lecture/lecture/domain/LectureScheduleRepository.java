package kr.jjh.lecture.lecture.domain;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureScheduleRepository extends JpaRepository<LectureSchedule, Long> {

    // 배타락 적용
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<LectureSchedule> findById(Long id);
}
