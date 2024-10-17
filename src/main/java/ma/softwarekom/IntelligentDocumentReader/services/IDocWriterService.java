package ma.softwarekom.IntelligentDocumentReader.services;

import org.springframework.ai.document.Document;

import java.util.List;

public interface IDocWriterService {
    void persist(List<Document> documents);
}
