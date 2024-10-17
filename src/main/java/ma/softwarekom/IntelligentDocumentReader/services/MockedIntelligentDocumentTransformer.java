package ma.softwarekom.IntelligentDocumentReader.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.document.Document;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnProperty(
        value="document.transformer.mocked",
        havingValue = "true",
        matchIfMissing = true)
public class MockedIntelligentDocumentTransformer implements IIntelligentDocumentTransformer{
    private final static Logger LOGGER = LoggerFactory.getLogger(MockedIntelligentDocumentTransformer.class);

    @Override
    public String keywordsEnricher(List<Document> documents, int keywordsCount) {
        LOGGER.info("Skipping..");
        return "";
    }

    @Override
    public List<Document> textSplitter(List<Document> documents) {
        LOGGER.info("Skipping..");
        return null;
    }

    @Override
    public String summaryEnricher(List<Document> documents) {
        LOGGER.info("Skipping..");
        return null;
    }
}
