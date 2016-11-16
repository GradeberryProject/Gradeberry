package content.com.ua.service;

import content.com.ua.entities.AbstractEntity;
import content.com.ua.entities.DBUser;

public interface DBUserService extends AbstractService <DBUser>{
    public DBUser findByLogin(String login);
    public DBUser findByLoginName(String loginName);


}
