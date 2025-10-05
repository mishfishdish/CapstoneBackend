package com.fit3161.project.endpoint.attendance.getAttendance;

import com.fit3161.project.endpoint.attendance.getAttendance.response.GetAttendanceResponse;
import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class GetAttendanceController {

    private final GetAttendanceService getAttendanceService;
    private final ClientManager client;

    @GetMapping("/api/attendance/{eventId}")
    public ResponseEntity<GetAttendanceResponse> handler(@Valid @PathVariable UUID eventId) {
        client.setEventId(eventId);
        return new ResponseEntity<>(getAttendanceService.getResponse(), getAttendanceService.getStatus());
    }
}