package content.com.ua.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import content.com.ua.enums.ServiceType;
import content.com.ua.enums.Status;
import content.com.ua.enums.WriterLevel;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


@Entity
public class UserOrder extends AbstractEntity {

    @Column(name = "topic")
    private String topic;

    private int pageCount;

    private String deadline;

    private String instructions;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paperType")
    private PaperType paperType;

    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<FileDB> fileDBs;

    private String date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "writer")
    private Writer writer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doneWork")
    private WritersWork doneWork;

    private int resourcesCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "format")
    private Format format;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject")
    private Subject subject;

    private double customerPrice;

    private double writerPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private WriterLevel writerLevel;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private String userOrderId;

    public UserOrder() {
        super();
    }

    public UserOrder(Customer customer) {
        this.customer = customer;
        this.fileDBs = new ArrayList<FileDB>();
        this.date = dateToString(new Date(), customer.getUser().getTimeZone(), "yyyy-MM-dd'T'HH:mm");
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String printDeadline() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date newDeadline = new Date();
        try {
            newDeadline = dateFormat.parse(deadline);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newDate = dateToString(newDeadline, this.customer.getUser().getTimeZone(), "yyyy-MM-dd HH:mm");
        return newDate;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public PaperType getPaperType() {
        return paperType;
    }

    public void setPaperType(PaperType paperType) {
        this.paperType = paperType;
    }

    public List<FileDB> getFileDBs() {
        return fileDBs;
    }

    public void setFileDBs(List<FileDB> fileDBs) {
        this.fileDBs = fileDBs;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public int getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(int resourcesCount) {
        this.resourcesCount = resourcesCount;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getDate() {
        return date;
    }

    public void setDate() {
        this.date = dateToString(new Date(), this.customer.getUser().getTimeZone(), "yyyy-MM-dd'T'HH:mm");
    }

    public WritersWork getDoneWork() {
        return doneWork;
    }

    public void setDoneWork(WritersWork doneWork) {
        this.doneWork = doneWork;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public WriterLevel getWriterLevel() {
        return writerLevel;
    }

    public void setWriterLevel(WriterLevel writerLevel) {
        this.writerLevel = writerLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addToFiles(FileDB fileDB) {
        fileDBs.add(fileDB);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCustomerPrice() {
        return customerPrice;
    }

    public void generateCustomerPrice(Price price) {
        this.customerPrice = price.getPrice() * this.pageCount;
    }

    public double getWriterPrice() {
        return writerPrice;
    }

    public void generateWriterPrice(Price price) {
        this.writerPrice = price.getPrice() * this.pageCount;
    }

    public void setCustomerPrice(double customerPrice) {
        this.customerPrice = customerPrice;
    }

    public void setWriterPrice(double writerPrice) {
        this.writerPrice = writerPrice;
    }

    public int hours1() {
        int hours = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        //  2016-10-12T10:12
        dateFormat.setTimeZone(TimeZone.getTimeZone(this.customer.getUser().getTimeZone()));
        try {
            String deadline = this.deadline;
            long dateFrom = dateFormat.parse(this.date).getTime();
            long dateTo = dateFormat.parse(this.deadline).getTime();
            hours = (int) Math.abs(dateFrom - dateTo) / 3600000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hours;
    }

    public String getUserOrderId() {
        return userOrderId;
    }

    public void setUserOrderId(String userOrderId) {
        this.userOrderId = userOrderId;
    }

//    @Override
//    public String toString() {
//        return
//                "<br/> Topic=" + this.topic +
//                        "<br/> PageCount=" + this.pageCount +
//                        "<br/> Instructions=" + this.instructions +
//                        "<br/> PaperType=" + this.paperType.getPaperType() +
//                        "<br/> ResourcesCount=" + this.resourcesCount +
//                        "<br/> Format=" + this.format.getFormat() +
//                        "<br/> Subject=" + this.subject.getSubject() +
//                        "<br/> WriterLevel=" + this.writerLevel +
//                        "<br/> ServiceType=" + this.serviceType+
//                        "<br/> Deadline="+printDeadline();
//    }
}
