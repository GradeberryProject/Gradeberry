package content.com.ua.entities;

import javax.persistence.*;


@Entity
public class PaperType extends AbstractEntity {

    private String paperType;

    public PaperType() {
        super();
    }

    public PaperType(String type) {
        this.paperType = type;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String type) {
        this.paperType = type;
    }
}
