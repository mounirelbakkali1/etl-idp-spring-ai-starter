package ma.softwarekom.IntelligentDocumentReader.services.manager;

import com.drew.lang.annotations.NotNull;
import ma.softwarekom.IntelligentDocumentReader.api.dto.ProcessResponse;
import ma.softwarekom.IntelligentDocumentReader.api.dto.ReadResponse;
import ma.softwarekom.IntelligentDocumentReader.api.exceptions.FileProcessingFailureException;
import ma.softwarekom.IntelligentDocumentReader.services.IDocReaderService;
import ma.softwarekom.IntelligentDocumentReader.services.IDocWriterService;
import ma.softwarekom.IntelligentDocumentReader.services.IIntelligentDocumentTransformer;
import ma.softwarekom.IntelligentDocumentReader.services.IntelligentDocumentTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentProcessorManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(DocumentProcessorManager.class);

    private IDocReaderService documentReader;
    private IIntelligentDocumentTransformer intelligentDocumentTransformer;
    private IDocWriterService documentWriterService;


    public DocumentProcessorManager(@NotNull IDocReaderService documentReader,
                                    @NotNull IIntelligentDocumentTransformer intelligentDocumentTransformer,
                                    @NotNull IDocWriterService documentWriterService) {
        this.documentReader = documentReader;
        this.intelligentDocumentTransformer = intelligentDocumentTransformer;
        this.documentWriterService = documentWriterService;
    }

    @NotNull
    public ProcessResponse process(MultipartFile file, int keywordsCount) {
        LOGGER.info("Processing of file: {} has been started",file.getOriginalFilename());
        try {
            ReadResponse readResponse = documentReader.readFile(file);
            String keywords = intelligentDocumentTransformer.keywordsEnricher(readResponse.documents(), keywordsCount);
            documentWriterService.persist(readResponse.documents());
            return new ProcessResponse(keywords,"File Processed Successfully",readResponse);
        } catch (Exception e) {
            LOGGER.error("Processing failed due to {}",e.getMessage());
            throw new FileProcessingFailureException(e);
        }
    }
}
