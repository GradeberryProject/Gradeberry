package content.com.ua.service;

import content.com.ua.entities.Account;
import content.com.ua.entities.Customer;
import content.com.ua.entities.DBUser;

public interface AccountService extends AbstractService <Account>{
    public void add(Account account);
    public void withdrawFromAccount(DBUser user, double sum);
}
