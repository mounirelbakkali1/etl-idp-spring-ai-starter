package ma.softwarekom.IntelligentDocumentReader.repository;

import ma.softwarekom.IntelligentDocumentReader.entities.DocReaderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocReaderLogRepository extends JpaRepository<DocReaderLog,Long> {
}
