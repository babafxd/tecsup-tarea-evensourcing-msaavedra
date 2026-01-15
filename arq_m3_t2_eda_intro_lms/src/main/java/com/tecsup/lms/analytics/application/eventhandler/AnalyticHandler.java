package com.tecsup.lms.analytics.application.eventhandler;

import com.tecsup.lms.courses.domain.event.CoursePublishedEvent;
import com.tecsup.lms.enrolled.domain.event.StudentEnrolledEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnalyticHandler {

    @Async("eventExecutor") // No generara bloqueos
    @EventListener
    public void handleCoursePublished(CoursePublishedEvent event) throws InterruptedException {
        log.info("[{}] Doing analytics ....", Thread.currentThread().getName());
        Thread.sleep(1000);
        log.info("Finished analytics : {}", event.getTitle());
    }

    @Async("eventExecutor")
    @EventListener
    public void handleStudentEnrolled(StudentEnrolledEvent event) throws InterruptedException {
        log.info("[{}] Actualizando estadisticas del curso ....", Thread.currentThread().getName());
        Thread.sleep(4000);
        System.out.println("Actualizando estadisticas del curso: " + event.getCourseName());
    }



}
