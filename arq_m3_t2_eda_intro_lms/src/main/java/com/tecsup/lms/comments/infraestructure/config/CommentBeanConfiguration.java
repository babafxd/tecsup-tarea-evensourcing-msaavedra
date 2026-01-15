package com.tecsup.lms.comments.infraestructure.config;

import com.tecsup.lms.comments.application.command.CommentCommandHandler;
import com.tecsup.lms.comments.domain.repository.CommentRepository;
import com.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentBeanConfiguration {

    @Bean
    public CommentCommandHandler commentCommandHandler(MemoryEventStore eventStore, CommentRepository repository) {
        return new CommentCommandHandler(eventStore, repository);
    }

}
