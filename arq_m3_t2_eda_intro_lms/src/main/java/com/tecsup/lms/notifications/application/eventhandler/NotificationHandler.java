package com.tecsup.lms.notifications.application.eventhandler;

import com.tecsup.lms.courses.domain.event.CoursePublishedEvent;
import com.tecsup.lms.enrolled.domain.event.StudentEnrolledEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationHandler {

    @Async("eventExecutor") // No generara bloqueos
    @EventListener
    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public void handleCoursePublished(CoursePublishedEvent event) throws InterruptedException {
        log.info("[{}] Sending notifications...", Thread.currentThread().getName());
        Thread.sleep(1000);
        log.info("Email sent for course: {}", event.getTitle());
    }

    //cuando de los intentos (maxAttempts) + 1 = se dispara este evento; se ejecuta 1 por handler
    @Recover
    public void recover(RuntimeException e, CoursePublishedEvent event) {
        log.error("All retries exhausted for Sending notifications: {}", event.getCourseId());
    }

}
