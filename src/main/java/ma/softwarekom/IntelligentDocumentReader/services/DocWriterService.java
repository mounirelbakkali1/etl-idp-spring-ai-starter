package ma.softwarekom.IntelligentDocumentReader.services;

import com.drew.lang.annotations.NotNull;
import ma.softwarekom.IntelligentDocumentReader.services.manager.S3StorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocWriterService implements IDocWriterService{
    private final static Logger LOGGER = LoggerFactory.getLogger(DocWriterService.class);

    private final VectorStore vectorStore;

    public DocWriterService(@NotNull VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Override
    public void persist(@NotNull List<Document> documents){
        LOGGER.info("{} documents passed for vector persistence",documents.size());
        vectorStore.add(documents);
        LOGGER.info("persisted successfully");
    }

}
