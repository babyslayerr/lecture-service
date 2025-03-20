package kr.jjh.lecture.integration.lecture;

import kr.jjh.lecture.lecture.domain.LectureSchedule;
import kr.jjh.lecture.lecture.domain.LectureService;
import kr.jjh.lecture.lecture.domain.LectureScheduleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class LectureServiceConcurrencyTest {

    @Autowired
    LectureScheduleRepository lectureScheduleRepository;

    @Autowired
    LectureService lectureService;
    @Test
    void 수강신청자가_30명이상일경우_30명만_성공한다() throws InterruptedException, ExecutionException {

        // given
        // 강좌 생성
        int max = 30;
        LectureSchedule lectureSchedule = LectureSchedule.builder()
                .max(max)
                // 추후 Lecture 추가
                .build();
        lectureScheduleRepository.save(lectureSchedule);
        // 강좌스케줄 ID
        long lectureScheduleId = 1L;
        // 테스트 스레드 개수
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        List<Future<Boolean>> results = new ArrayList<>();
        for(int i = 0; i<threadCount;i++){
            Long studentId = (long) i;
            results.add(executorService.submit(()->{
                try {
                    lectureService.applyLecture(studentId, lectureScheduleId);
                    return true;
                }catch (Exception e){
                    return false;
                }finally {
                    latch.countDown();
                }
            }));
        }
        // 각스레드 종료전까지 메인스레드 대기
        latch.await();
        executorService.shutdown();

        // 성공 카운팅
        int successCount = 0;
        for(Future<Boolean> result : results){
            if(result.get()){
                successCount++;
            }
        }
        Assertions.assertEquals(max,successCount);
    }
}
