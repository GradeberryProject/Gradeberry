package content.com.ua.repository;

import content.com.ua.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AbstractRepository <T extends AbstractEntity, ID extends Number> extends JpaRepository<T, ID>  {

}
