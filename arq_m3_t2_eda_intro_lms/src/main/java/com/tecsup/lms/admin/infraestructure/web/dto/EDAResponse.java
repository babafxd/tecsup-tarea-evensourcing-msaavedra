package com.tecsup.lms.admin.infraestructure.web.dto;

import com.tecsup.lms.shared.domain.event.DomainEvent;
import lombok.Data;

import java.util.List;

@Data
public class EDAResponse {
    private List<DomainEvent> events;
}
