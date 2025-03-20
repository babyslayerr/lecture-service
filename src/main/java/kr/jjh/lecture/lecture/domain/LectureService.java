package kr.jjh.lecture.lecture.domain;

import jakarta.transaction.Transactional;
import kr.jjh.lecture.lecture.infrastructure.LectureStudentRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureStudentRepositoryImpl lectureStudentRepository;
    private final LectureScheduleRepository lectureScheduleRepository;

    @Transactional
    public LectureStudent applyLecture(Long studentId, Long lectureScheduleId) {
        // 수강 신청할 강좌
        // 동시성을 고려해서 비관적 락 적용
        LectureSchedule appliedLectureSchedule = lectureScheduleRepository.findById(lectureScheduleId).orElseThrow();
        // 해당 강좌스케줄을 수강하고 있는 학생수
        int lectureStudentCount = lectureStudentRepository.countByLectureScheduleId(lectureScheduleId);
        if(appliedLectureSchedule.getMax() <= lectureStudentCount){
            throw new IllegalStateException("수강인원을 초과하였습니다.");
        }

        // 수강할 학생 등록
        LectureStudent lectureStudent = new LectureStudent(studentId, lectureScheduleId);
        LectureStudent savedLectureStudent = lectureStudentRepository.save(lectureStudent);
        return savedLectureStudent;
    }

}
