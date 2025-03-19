package kr.jjh.lecture.lecture.domain;

import kr.jjh.lecture.lecture.infrastructure.LectureScheduleRepository;
import kr.jjh.lecture.lecture.infrastructure.LectureStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureStudentRepository lectureStudentRepository;
    private final LectureScheduleRepository lectureScheduleRepository;

    public synchronized LectureStudent applyLecture(Long studentId, Long lectureScheduleId) {
        // 수강 신청할 강좌
        LectureSchedule appliedLectureSchedule = lectureScheduleRepository.findById(lectureScheduleId);
        // 해당 강좌스케줄을 수강하고 있는 학생수
        int lectureStudentCount = lectureStudentRepository.countByLectureScheduleId(lectureScheduleId);
        if(appliedLectureSchedule.getMax() <= lectureStudentCount){
            throw new IllegalStateException("수강인원을 초과하였습니다.");
        }

        LectureStudent lectureStudent = new LectureStudent(studentId, lectureScheduleId);
        LectureStudent savedLectureStudent = lectureStudentRepository.save(lectureStudent);
        return savedLectureStudent;
    }

}
