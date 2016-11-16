package content.com.ua.repository;

import content.com.ua.entities.PaperType;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperTypeRepository extends AbstractRepository<PaperType,Long> {

    public PaperType findByPaperType(String paperType);
}
