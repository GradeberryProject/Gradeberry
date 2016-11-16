package content.com.ua.repository;

import content.com.ua.entities.DBUser;
import org.springframework.stereotype.Repository;

@Repository
public interface DBUserRepository extends AbstractRepository<DBUser, Long>{
    public DBUser findByLogin(String login);
    public DBUser findByLoginName(String loginName);
}
