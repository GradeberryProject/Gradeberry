package content.com.ua.repository;

import content.com.ua.entities.Format;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatRepository extends AbstractRepository<Format,Long> {

    public Format findByFormat(String format);
}
