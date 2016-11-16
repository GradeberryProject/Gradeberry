package content.com.ua.entities;

import content.com.ua.enums.ServiceType;
import content.com.ua.enums.UserRole;
import content.com.ua.enums.WriterLevel;

import javax.persistence.Entity;

@Entity
public class Price extends AbstractEntity {

    private UserRole userType;
    private ServiceType serviceType;
    private WriterLevel writerLevel;
    private int hoursFrom;
    private int hoursTo;
    private double price;

    public Price() {
        super();
    }

    public Price(UserRole userType, ServiceType serviceType, WriterLevel writerLevel, int hoursFrom, int hoursTo, double price) {
        this.userType = userType;
        this.serviceType = serviceType;
        this.writerLevel = writerLevel;
        this.hoursFrom = hoursFrom;
        this.hoursTo = hoursTo;
        this.price = price;
    }

    public UserRole getUserType() {
        return userType;
    }

    public void setUserType(UserRole userType) {
        this.userType = userType;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public WriterLevel getWriterLevel() {
        return writerLevel;
    }

    public void setWriterLevel(WriterLevel writerLevel) {
        this.writerLevel = writerLevel;
    }

    public int getHoursFrom() {
        return hoursFrom;
    }

    public void setHoursFrom(int hoursFrom) {
        this.hoursFrom = hoursFrom;
    }

    public int getHoursTo() {
        return hoursTo;
    }

    public void setHoursTo(int hoursTo) {
        this.hoursTo = hoursTo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
