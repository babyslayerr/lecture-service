package kr.jjh.lecture.lecture.infrastructure;

import kr.jjh.lecture.lecture.domain.LectureStudent;
import kr.jjh.lecture.lecture.domain.LectureStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// 추후 Querydsl 추가
@Repository
@RequiredArgsConstructor
public class LectureStudentRepositoryImpl implements LectureStudentRepository {

    private final LectureStudentJpaRepository lectureStudentJpaRepository;

    public LectureStudent save(LectureStudent lectureStudent) {
        return lectureStudentJpaRepository.save(lectureStudent);
    }

    public int countByLectureScheduleId(Long lectureScheduleId) {

        return lectureStudentJpaRepository.countByLectureScheduleId(lectureScheduleId);
    }

    public Optional<LectureStudent> findFirstByStudentId(Long studentId){
        return lectureStudentJpaRepository.findFirstByStudentId(studentId);
    }

    public List<LectureStudent> findAllByLectureScheduleId(long lectureScheduleId) {
        return lectureStudentJpaRepository.findAllByLectureScheduleId(lectureScheduleId);
    }
}
