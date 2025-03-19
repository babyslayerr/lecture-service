package kr.jjh.lecture.lecture.domain;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class LectureSchedule {

    private Long id;
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    private LocalDateTime startDate;
    private int max;

    public int getMax() {

        return 30;
    }
}
