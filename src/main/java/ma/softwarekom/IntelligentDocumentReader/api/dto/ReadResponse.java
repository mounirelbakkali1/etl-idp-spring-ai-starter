package ma.softwarekom.IntelligentDocumentReader.api.dto;

import org.springframework.ai.document.Document;

import java.util.List;

public record ReadResponse(List<Document> documents, FileUploadResponse uploadResponse, String content) {
}
