package com.fit3161.project.endpoint.attendance;

import com.fit3161.project.endpoint.attendance.request.AttendanceRequest;
import com.fit3161.project.endpoint.attendance.response.CreateResponse;
import com.fit3161.project.managers.ClientManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final ClientManager client;

    @PostMapping("/api/attendance/{eventId}")
    public ResponseEntity<CreateResponse> handler(@Valid @PathVariable UUID eventId, @Valid @RequestBody AttendanceRequest request) {
        client.setRequest(request);
        client.setEventId(eventId);
        return new ResponseEntity<>(attendanceService.getResponse(), attendanceService.getStatus());
    }
}