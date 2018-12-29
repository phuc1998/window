package entities;
// Generated Dec 14, 2018 10:17:08 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Bill generated by hbm2java
 */
@Entity
@Table(name = "BILL",
         schema = "dbo",
         catalog = "WINDOWS"
)
public class Bill implements java.io.Serializable {

    @Id
    @GenericGenerator(name = "i2", strategy = "common.BILLID")
    @GeneratedValue(generator = "i2")
    @Column(name = "ID", unique = true, nullable = false, length = 15)
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_DISCOUNT")
    private Discount discount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PAYMENT")
    private Payment payment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PHONE")
    private Phone phone;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_SELL")
    private Date dateSell;
    @Column(name = "COUNT")
    private Integer count;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "DISCRIPTION")
    private String discription;

    public Bill() {
    }

    public Bill(String id) {
        this.id = id;
    }

    public Bill(Discount discount, Payment payment, Phone phone, Date dateSell, Integer count, String status, String discription) {
        this.discount = discount;

        this.payment = payment;
        this.phone = phone;
        this.dateSell = dateSell;
        this.count = count;
        this.status = status;
        this.discription = discription;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Discount getDiscount() {
        return this.discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Date getDateSell() {
        return this.dateSell;
    }

    public void setDateSell(Date dateSell) {
        this.dateSell = dateSell;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiscription() {
        return this.discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

}
