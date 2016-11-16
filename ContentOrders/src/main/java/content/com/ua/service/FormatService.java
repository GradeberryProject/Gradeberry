package content.com.ua.service;

import content.com.ua.entities.Format;

public interface FormatService extends AbstractService <Format> {

    public Format findByFormat(String format);
}
