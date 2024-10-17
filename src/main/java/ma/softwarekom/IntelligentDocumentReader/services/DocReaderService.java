package ma.softwarekom.IntelligentDocumentReader.services;


import com.drew.lang.annotations.NotNull;
import ma.softwarekom.IntelligentDocumentReader.api.dto.FileUploadResponse;
import ma.softwarekom.IntelligentDocumentReader.api.dto.ReadResponse;
import ma.softwarekom.IntelligentDocumentReader.entities.DocReaderLog;
import ma.softwarekom.IntelligentDocumentReader.repository.DocReaderLogRepository;
import ma.softwarekom.IntelligentDocumentReader.services.manager.S3StorageManager;
import ma.softwarekom.IntelligentDocumentReader.services.manager.StorageManager;
import ma.softwarekom.IntelligentDocumentReader.utils.FileUtils;
import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
 * Readers available:
 *   1. PagePDFDocumentReader
 *   2. TikaDocumentReader
 *   3. ParagraphPDFReader
 *   4. JSONReader
 *   4. TextReader
 * */

@Service
public class DocReaderService implements IDocReaderService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DocReaderService.class);
    private final StorageManager storageManager;
    private DocReaderLogRepository docReaderLogRepository;


    public DocReaderService(@NotNull StorageManager storageManager,
                            @NotNull DocReaderLogRepository docReaderLogRepository) {
        this.storageManager = storageManager;
        this.docReaderLogRepository = docReaderLogRepository;
    }

    @NotNull
    @Override
    public ReadResponse readFile(@NotNull final MultipartFile file) throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = dateTimeFormatter.format(LocalDate.now());
        String fileIdentifier =  todayDate+"/"+ FileUtils.formatFileName(Objects.requireNonNull(file.getOriginalFilename()));
        FileUploadResponse fileUploadResponse = storageManager.uploadFile(file, fileIdentifier);

        // based on the file type we will use the corresponding Document Reader from Spring AI
        Class<? extends DocumentReader> correspondingDocumentReader = FileUtils.getCorrespondingDocumentReader(file);

        // dynamic reader
        DocumentReader documentReader = correspondingDocumentReader
                .getDeclaredConstructor(String.class)
                .newInstance(fileUploadResponse.url());

        List<Document> documents = documentReader.read();
        LOGGER.info("Complete document reading..");

        // save logs
        docReaderLogRepository.save(
                DocReaderLog.builder()
                .documentName(fileIdentifier)
                .build()
        );
        return new ReadResponse(documents,
                fileUploadResponse,
                documents.stream()
                        .map(Document::getFormattedContent)
                        .collect(Collectors.joining())
        );
    }
}
