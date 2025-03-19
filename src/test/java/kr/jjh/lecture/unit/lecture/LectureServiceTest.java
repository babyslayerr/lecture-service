package kr.jjh.lecture.unit.lecture;

import kr.jjh.lecture.lecture.domain.LectureSchedule;
import kr.jjh.lecture.lecture.domain.LectureService;
import kr.jjh.lecture.lecture.domain.LectureStudent;
import kr.jjh.lecture.lecture.infrastructure.LectureScheduleRepository;
import kr.jjh.lecture.lecture.infrastructure.LectureStudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LectureServiceTest {

    @Mock
    LectureStudentRepository lectureStudentRepository;
    @Mock
    LectureScheduleRepository lectureScheduleRepository;
    @InjectMocks
    LectureService lectureService;

    @Test
    public void 특강신청에_성공한다(){

        // given
        Long studentId = 1L;
        Long lectureScheduleId = 1L;
        LectureStudent lectureStudent = new LectureStudent(studentId, lectureScheduleId);
        LectureSchedule lectureSchedule = LectureSchedule.builder()
                .max(30)
                .build();

        given(lectureScheduleRepository.findById(lectureScheduleId)).willReturn(lectureSchedule);
        given(lectureStudentRepository.countByLectureScheduleId(lectureScheduleId)).willReturn(15);
        given(lectureStudentRepository.save(any())).willReturn(lectureStudent);
        // when
        LectureStudent savedLectureStudent = lectureService.applyLecture(studentId,lectureScheduleId);

        // then
        Assertions.assertEquals(lectureStudent,savedLectureStudent);
    }

    @Test
    void 특강정원이_가득차있을경우_저장은_실패한다(){

        // given
        Long studentId = 1L;
        Long lectureScheduleId = 1L;
        LectureSchedule lectureSchedule = LectureSchedule.builder()
                .max(30)
                .build();
        given(lectureScheduleRepository.findById(lectureScheduleId)).willReturn(lectureSchedule);
        given(lectureStudentRepository.countByLectureScheduleId(lectureScheduleId)).willReturn(30);

        // when, then
        Assertions.assertThrows(IllegalStateException.class ,()->{
            lectureService.applyLecture(studentId,lectureScheduleId);
        });

    }
}
