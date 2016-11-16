package content.com.ua.repository;

import content.com.ua.entities.FileDB;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends AbstractRepository<FileDB,Long> {
}
