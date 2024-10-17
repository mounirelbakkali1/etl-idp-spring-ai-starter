package ma.softwarekom.IntelligentDocumentReader.services;

import org.springframework.ai.document.Document;

import java.util.List;

public interface IIntelligentDocumentTransformer {
    String keywordsEnricher(List<Document> documents, int keywordsCount);
    List<Document> textSplitter(List<Document> documents);
    String summaryEnricher(List<Document> documents);
}
