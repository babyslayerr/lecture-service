package kr.jjh.lecture.lecture.infrastructure;

import kr.jjh.lecture.lecture.domain.LectureStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureStudentJpaRepository extends JpaRepository<LectureStudent, Long> {
    int countByLectureScheduleId(Long lectureScheduleId);

    List<LectureStudent> findAllByLectureScheduleId(Long lectureScheduleId);
}
