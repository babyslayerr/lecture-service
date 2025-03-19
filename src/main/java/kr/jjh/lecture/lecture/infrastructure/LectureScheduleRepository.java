package kr.jjh.lecture.lecture.infrastructure;

import kr.jjh.lecture.lecture.domain.LectureSchedule;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LectureScheduleRepository {

    private final Map<Long,LectureSchedule> store = new HashMap<>();
    private AtomicLong id = new AtomicLong(1);

    public LectureSchedule findById(Long lectureScheduleId) {
        return store.get(lectureScheduleId);
    }

    public void save(LectureSchedule lectureSchedule) {
        store.put(id.getAndIncrement(),lectureSchedule);
    }
}
