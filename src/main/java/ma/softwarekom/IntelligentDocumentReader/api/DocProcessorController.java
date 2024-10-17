package ma.softwarekom.IntelligentDocumentReader.api;


import jakarta.validation.Valid;
import ma.softwarekom.IntelligentDocumentReader.services.manager.DocumentProcessorManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/idp")
public class DocProcessorController {

    private static Logger LOGGER = LoggerFactory.getLogger(DocProcessorController.class);
    DocumentProcessorManager documentProcessorManager;

    public DocProcessorController(final DocumentProcessorManager documentProcessorManager) {
        this.documentProcessorManager = documentProcessorManager;
    }

    @PostMapping("/process")
    public ResponseEntity<?> readFile(@RequestParam("file") @Valid MultipartFile file, @RequestParam("keywordsCount") int keywordsCount) throws IOException {
        LOGGER.info("received file {} for processing, keywordsCount: {}",file.getName(),keywordsCount);
        return ResponseEntity.ok(documentProcessorManager.process(file,keywordsCount));
    }
}
