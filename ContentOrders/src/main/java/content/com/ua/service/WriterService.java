package content.com.ua.service;

import content.com.ua.entities.DBUser;
import content.com.ua.entities.Writer;

import java.util.List;

/**
 * Created by uzer on 15.10.2016.
 */
public interface WriterService extends AbstractService <Writer> {

    public Writer findByUser(DBUser user);
}
