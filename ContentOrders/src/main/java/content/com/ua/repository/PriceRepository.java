package content.com.ua.repository;


import content.com.ua.entities.Price;
import content.com.ua.enums.ServiceType;
import content.com.ua.enums.UserRole;
import content.com.ua.enums.WriterLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends AbstractRepository<Price,Long>{

    public List<Price> findByUserTypeAndServiceTypeAndWriterLevel(UserRole userType, ServiceType serviceType, WriterLevel writerLevel);
}
