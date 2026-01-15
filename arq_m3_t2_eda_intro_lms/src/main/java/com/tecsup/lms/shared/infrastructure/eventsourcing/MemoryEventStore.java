package com.tecsup.lms.shared.infrastructure.eventsourcing;

import com.tecsup.lms.shared.domain.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class MemoryEventStore implements EventStore {

    private final Map<String, List<DomainEvent>> stores = new ConcurrentHashMap<>();
    private final ApplicationEventPublisher publisher;

    public MemoryEventStore(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void save(String aggregateId, DomainEvent event) {

        // agregar el evento al stores
        // computeIfAbsent; por el tipo de concurrencia
        this.stores.computeIfAbsent(aggregateId,key -> new ArrayList<>())
                .add(event);

        // publicar evento
        publisher.publishEvent(event);

    }

    @Override
    public List<DomainEvent> getEvents(String aggregateId) {
        return new ArrayList<>(stores.getOrDefault(aggregateId, List.of()));
    }

    @Override
    public List<DomainEvent> getAllEvents() {
        return stores.values().stream()
                .flatMap(List::stream)
                .toList();
    }
}
