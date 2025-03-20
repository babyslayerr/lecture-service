package kr.jjh.lecture.lecture.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LectureStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long lectureScheduleId;
    private Long studentId;
    private LocalDateTime applyDateTime = LocalDateTime.now();
    public LectureStudent(Long studentId, Long lectureScheduleId) {
        this.lectureScheduleId = lectureScheduleId;
        this.studentId = studentId;
    }

}
