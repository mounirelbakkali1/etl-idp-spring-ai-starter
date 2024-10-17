package ma.softwarekom.IntelligentDocumentReader.services;

import com.drew.lang.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.KeywordMetadataEnricher;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnMissingBean(MockedIntelligentDocumentTransformer.class)
public class IntelligentDocumentTransformer  implements  IIntelligentDocumentTransformer{
    private final static Logger LOGGER = LoggerFactory.getLogger(IntelligentDocumentTransformer.class);

    private ChatModel chatModel;

    public IntelligentDocumentTransformer(@NotNull ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @NotNull
    @Override
    public String keywordsEnricher(List<Document> documents, int keywordsCount){
        LOGGER.info("Received documents for keyword enriching, size: {}",documents.size());
        KeywordMetadataEnricher enricher = new KeywordMetadataEnricher(chatModel, keywordsCount);
        List<Document> enrichedDocs = enricher.apply(documents);
        LOGGER.info("Successfully processed");
        return enrichedDocs.stream()
                .map(doc -> doc.getMetadata().get("excerpt_keywords"))
                .toString();
    }

    @Override
    public String summaryEnricher(List<Document> documents){
        return null;
    }

    @NotNull
    @Override
    public List<Document> textSplitter(List<Document> documents){
        return new TokenTextSplitter()
                .apply(documents);
    }
}
