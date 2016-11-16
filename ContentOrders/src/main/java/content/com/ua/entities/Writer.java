package content.com.ua.entities;

import content.com.ua.enums.WriterLevel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "writer")
public class Writer extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String passport;
    private byte[] diploma;
    private String phone;
    private WriterLevel academicDegree;
    private Double rating;
    private String city;
    private String skypeName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private DBUser user;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserOrder> writerOrders;

    @OneToMany(mappedBy = "writer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<WritersWork> writersWorks;

    public Writer() {
        super();
    }

    public Writer(DBUser user) {
        this.user = user;
    }

    public Writer(String firstName, String lastName, String passport, byte[] diploma, String phone, DBUser user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.diploma = diploma;
        this.phone = phone;
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSkypeName() {
        return skypeName;
    }

    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    public WriterLevel getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(WriterLevel academicDegree) {
        this.academicDegree = academicDegree;
    }

    public DBUser getUser() {
        return user;
    }

    public void setUser(DBUser user) {
        this.user = user;
    }

    public List<WritersWork> getWritersWorks() {
        return writersWorks;
    }

    public void setWritersWorks(List<WritersWork> writersWorks) {
        this.writersWorks = writersWorks;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public byte[] getDiploma() {
        return diploma;
    }

    public void setDiploma(byte[] diploma) {
        this.diploma = diploma;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public DBUser getDBUser() {
        return user;
    }

    public void setDBUser(DBUser user) {
        this.user = user;
    }

    public List<UserOrder> getWriterOrders() {
        return writerOrders;
    }

    public void setWriterOrders(List<UserOrder> writerOrders) {
        this.writerOrders = writerOrders;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
