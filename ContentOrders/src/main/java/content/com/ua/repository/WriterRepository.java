package content.com.ua.repository;

import content.com.ua.entities.DBUser;
import content.com.ua.entities.Writer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WriterRepository extends AbstractRepository<Writer, Long>{

    public Writer findByUser(DBUser user);
}
