package ma.softwarekom.IntelligentDocumentReader.services.manager;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.drew.lang.annotations.NotNull;
import ma.softwarekom.IntelligentDocumentReader.api.dto.FileUploadResponse;
import ma.softwarekom.IntelligentDocumentReader.repository.DocReaderLogRepository;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class S3StorageManager implements StorageManager {

    private final static Logger LOGGER = LoggerFactory.getLogger(S3StorageManager.class);

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.region}")
    private String region;

    private final AmazonS3 s3Client;

    public S3StorageManager(AmazonS3 s3Client, DocReaderLogRepository docReaderLogRepository) {
        this.s3Client = s3Client;
    }


    public FileUploadResponse uploadFile(@NotNull final MultipartFile multipartFile, String fileIdentifier) throws FileUploadException {
        LOGGER.info("Uploading {} to S3 bucket: {}",fileIdentifier,bucketName);
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getSize());
            s3Client.putObject(bucketName, fileIdentifier, multipartFile.getInputStream(), objectMetadata);
            LOGGER.debug("Upload successful");
            Date expiration = new Date();
            expiration.setTime(expiration.getTime() + 3600000);  // 1hr expiry
            String url = s3Client.generatePresignedUrl(
                            new GeneratePresignedUrlRequest(bucketName, fileIdentifier)
                                    .withMethod(HttpMethod.GET)
                                    .withExpiration(expiration))
                    .toString();
            return new FileUploadResponse(fileIdentifier, LocalDateTime.now(), url,expiration);
        } catch (IOException e) {
            LOGGER.error("Error occurred while upload, {}", e.getMessage());
            throw new FileUploadException("Error occurred in file upload ==> "+e.getMessage());
        }
    }
}
