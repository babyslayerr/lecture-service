package kr.jjh.lecture.lecture.infrastructure;

import kr.jjh.lecture.lecture.domain.LectureStudent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LectureStudentRepository {
    Map<Long, LectureStudent> storage = new HashMap<>();

    private AtomicLong id = new AtomicLong();

    public LectureStudent save(LectureStudent lectureStudent) {
        long id = this.id.getAndIncrement();
        storage.put(id, lectureStudent);
        LectureStudent savedLectureStudent = storage.get(id);
        return savedLectureStudent;
    }

    public int countByLectureScheduleId(Long lectureScheduleId) {
        List<LectureStudent> list = storage.values().stream().filter((e) -> {
            if (e.getLectureScheduleId().equals(lectureScheduleId)) return true;
            return false;
        }).toList();
        return list.size();
    }

    public List<LectureStudent> findAllByLectureScheduleId(long lectureScheduleId) {

        List<LectureStudent> list = storage.values().stream().filter((e) -> {
            if (e.getLectureScheduleId().equals(lectureScheduleId)) return true;
            return false;
        }).toList();
        return list;
    }
}
