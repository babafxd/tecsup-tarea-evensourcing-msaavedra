package com.tecsup.lms.admin.infraestructure.web.controller;

import com.tecsup.lms.admin.infraestructure.web.dto.DLQResponse;
import com.tecsup.lms.admin.infraestructure.web.dto.EDAResponse;
import com.tecsup.lms.shared.infrastructure.eventsourcing.MemoryEventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/eda")
@RequiredArgsConstructor
public class EDAController {

    private final MemoryEventStore eventStore;
    @GetMapping
    public ResponseEntity<EDAResponse> getEvents() {
        EDAResponse response = new EDAResponse();
        response.setEvents(eventStore.getAllEvents());
        return ResponseEntity.ok(response);
    }

}
