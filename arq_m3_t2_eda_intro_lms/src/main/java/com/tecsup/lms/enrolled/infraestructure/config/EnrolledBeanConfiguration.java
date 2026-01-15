package com.tecsup.lms.enrolled.infraestructure.config;

import com.tecsup.lms.enrolled.application.StudentEnrolledUseCase;
import com.tecsup.lms.enrolled.application.command.StudentEnrolledCommandHandler;
import com.tecsup.lms.enrolled.domain.repository.StudentRepository;
import com.tecsup.lms.shared.domain.event.EventPublisher;
import com.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CONFIGURACIÓN DE BEANS
 *
 * Registra los Use Cases y Domain Services como beans de Spring.
 *
 * Nota: Lombok @RequiredArgsConstructor se encarga de la inyección,
 * aquí solo creamos las instancias.
 */
@Configuration
public class EnrolledBeanConfiguration {

    @Bean
    public StudentEnrolledUseCase studentEnrolledUseCase(StudentRepository repository, EventPublisher eventPublisher) {
        return new StudentEnrolledUseCase(repository, eventPublisher);
    }

    @Bean
    public StudentEnrolledCommandHandler studentEnrolledCommandHandler(MemoryEventStore eventStore, StudentRepository studentRepository) {
        return new StudentEnrolledCommandHandler(eventStore, studentRepository);
    }

}
