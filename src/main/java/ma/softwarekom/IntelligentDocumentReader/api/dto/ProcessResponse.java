package ma.softwarekom.IntelligentDocumentReader.api.dto;

public record ProcessResponse(
        String body,
        String message,
        ReadResponse responseDetails
) {
}
