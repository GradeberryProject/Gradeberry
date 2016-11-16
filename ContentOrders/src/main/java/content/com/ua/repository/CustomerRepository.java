package content.com.ua.repository;

import content.com.ua.entities.Customer;
import content.com.ua.entities.DBUser;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends AbstractRepository<Customer, Long> {

    public Customer findByUser(DBUser user);
}
