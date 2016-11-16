package content.com.ua.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by uzer on 05.10.2016.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static final String dateformat = "yyyy-MM-dd'T'HH:mm";

    public AbstractEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static String dateToString(Date date, String timeZone, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(date);
    }

    public static Date dateToNewFormat(Date date, String timeZone) {
        String newDate = dateToString(date, timeZone, dateformat);
        Date resultDate = stringToDate(newDate, timeZone);
        return resultDate;
    }

    public static Date stringToDate(String date, String timeZone) {
        DateFormat dateFormat = new SimpleDateFormat(dateformat);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        Date newDate = new Date();
        try {
            newDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
