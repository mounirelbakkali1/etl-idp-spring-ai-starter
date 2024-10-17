package ma.softwarekom.IntelligentDocumentReader.api.exceptions;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUploadException(FileUploadException ex) {
        return new ResponseEntity<>("Unexpected Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(FileProcessingFailureException.class)
    public ResponseEntity<String> handleFileProcessingFailureException(FileProcessingFailureException ex) {
        return new ResponseEntity<>("Unexpected Error while processing the document: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
