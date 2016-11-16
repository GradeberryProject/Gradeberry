package content.com.ua.repository;

import content.com.ua.entities.Account;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends AbstractRepository<Account,Long> {
}
