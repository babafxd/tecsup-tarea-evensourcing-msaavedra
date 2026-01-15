package com.tecsup.lms.enrolled.domain.event;

import com.tecsup.lms.shared.domain.event.DomainEvent;
import lombok.Getter;

@Getter
public class LessonCompletedEvent extends DomainEvent {

    private final String studentId;
    private final String lessonId;
    private final int newProgressPercentage;

    public LessonCompletedEvent(String studentId, String lessonId, int newProgressPercentage) {
        super();
        this.studentId = studentId;
        this.lessonId = lessonId;
        this.newProgressPercentage = newProgressPercentage;
    }

}
