package ma.softwarekom.IntelligentDocumentReader.api.exceptions;

public class FileProcessingFailureException extends RuntimeException {
    public FileProcessingFailureException(Exception e) {
        super(e);
    }
}
