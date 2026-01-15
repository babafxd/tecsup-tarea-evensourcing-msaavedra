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

import java.util.Random;

@Slf4j
@Component
public class EnrolledAccessHandler {

    private final Random random = new Random();

    @Async("eventExecutor")
    @EventListener
    @Retryable(
            maxAttempts = 2,
            backoff = @Backoff(delay = 1000, multiplier = 2)
    )
    public void onStudentEnrolled(StudentEnrolledEvent event) throws InterruptedException {
        log.info("[{}] Creando acceso al material para alumno...", Thread.currentThread().getName());
        Thread.sleep(4000);
        System.out.println("Creando acceso al material para alumno: " + event.getStudentId());

        sendEmailNotification(event);
    }

    private void sendEmailNotification(StudentEnrolledEvent event) {

        log.info("[{}] Processing sendEmailNotification ...", Thread.currentThread().getName());

        if (random.nextBoolean()) {
            log.info("sendEmailNotification processing taking longer than expected...");
            throw new RuntimeException("sendEmailNotification processing failed due to timeout");
        }

        log.info("Enviando email de bienvenida al correo electrónico {}, para el curso {}",
                event.getEmail(),
                event.getCourseName()
        );

    }

    //cuando de los intentos + 1 = se dispara este evento; se ejecuta 1 por handler
    @Recover
    public void recover(RuntimeException e, StudentEnrolledEvent event) {
        log.error("All retries exhausted for sendEmailNotification processing: {}", event.getEmail());
    }

}
