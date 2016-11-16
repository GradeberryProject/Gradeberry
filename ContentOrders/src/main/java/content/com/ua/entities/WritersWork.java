package content.com.ua.entities;

import content.com.ua.enums.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "writers_work")
public class WritersWork extends AbstractEntity {

    private String doneDate;

    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_file")
    private FileDB resultFileDB;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer")
    private Writer writer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userOrder", referencedColumnName = "doneWork")
    private UserOrder userOrder;

    private Status status;

    public WritersWork() {
        super();
    }

    public WritersWork(String description, FileDB resultFileDB, Writer writer, UserOrder userOrder, Status status) {
        this.description = description;
        this.resultFileDB = resultFileDB;
        this.writer = writer;
        this.userOrder = userOrder;
        this.status = status;
        this.doneDate = dateToString(new Date(), writer.getDBUser().getTimeZone(), "yyyy-MM-dd'T'HH:mm");
    }

    public String getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date newDate) {
        String currentDate = dateToString(newDate, this.writer.getDBUser().getTimeZone(), "yyyy-MM-dd'T'HH:mm");
        this.doneDate = currentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FileDB getResultFileDB() {
        return resultFileDB;
    }

    public void setResultFileDB(FileDB resultFileDB) {
        this.resultFileDB = resultFileDB;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public UserOrder getOrder() {
        return userOrder;
    }

    public void setOrder(UserOrder userOrder) {
        this.userOrder = userOrder;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
