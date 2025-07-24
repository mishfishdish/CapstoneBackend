package com.fit3161.project.endpoint.activityManagement.importActivity;

import com.fit3161.project.endpoint.activityManagement.importActivity.response.ImportResult;
import com.fit3161.project.managers.ClientManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ImportController {

    private final ImportService importService;
    private final ClientManager client;

    @DeleteMapping("/api/import")
    public ResponseEntity<ImportResult> handler(@RequestParam("file") MultipartFile file) {
        client.setFile(file);
        return new ResponseEntity<>(importService.getResponse(), importService.getStatus());
    }
}