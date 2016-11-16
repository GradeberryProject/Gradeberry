package content.com.ua.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by uzer on 30.09.2016.
 */
@Entity
public class FileDB extends AbstractEntity {

    private String name;

    private String path;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userOrder")
    private UserOrder userOrder;

    public FileDB() {
        super();
    }

    public FileDB(String name, UserOrder userOrder, String path) {
        this.name = name;
        this.userOrder = userOrder;
        this.path = path;
    }

    public UserOrder getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
