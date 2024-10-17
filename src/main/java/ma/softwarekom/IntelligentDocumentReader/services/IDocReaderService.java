package ma.softwarekom.IntelligentDocumentReader.services;

import ma.softwarekom.IntelligentDocumentReader.api.dto.ReadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IDocReaderService {
    ReadResponse readFile(MultipartFile file) throws Exception ;
}
