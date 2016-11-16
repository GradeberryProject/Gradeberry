package content.com.ua.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;


@Entity
public class Format extends AbstractEntity {

    private String format;

    public Format() {
        super();
    }

    public Format(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
