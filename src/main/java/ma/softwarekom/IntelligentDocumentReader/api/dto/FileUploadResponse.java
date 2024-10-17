package ma.softwarekom.IntelligentDocumentReader.api.dto;

import java.time.LocalDateTime;
import java.util.Date;

public record FileUploadResponse(String filePath, LocalDateTime uploadTime, String url, Date urlExpiry) {
}
