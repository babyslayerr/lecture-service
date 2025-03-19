package kr.jjh.lecture.lecture.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LectureStudent {
    private Long lectureScheduleId;
    private Long studentId;
    private LocalDateTime applyDateTime = LocalDateTime.now();
    public LectureStudent(Long studentId, Long lectureScheduleId) {
        this.lectureScheduleId = lectureScheduleId;
        this.studentId = studentId;
    }

}
