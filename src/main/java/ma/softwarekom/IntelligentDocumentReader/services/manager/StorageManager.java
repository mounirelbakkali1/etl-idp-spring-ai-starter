package ma.softwarekom.IntelligentDocumentReader.services.manager;

import ma.softwarekom.IntelligentDocumentReader.api.dto.FileUploadResponse;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageManager {

    public FileUploadResponse uploadFile(MultipartFile file, String fileIdentifier) throws FileUploadException;
}
