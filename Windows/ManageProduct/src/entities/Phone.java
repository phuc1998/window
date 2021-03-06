package entities;
// Generated Dec 14, 2018 10:17:08 PM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * Phone generated by hbm2java
 */
@Entity
@Table(name = "PHONE",
         schema = "dbo",
         catalog = "WINDOWS"
)
public class Phone implements java.io.Serializable {

    @Id
    @GenericGenerator(name = "i1", strategy = "increment")
    @GeneratedValue(generator = "i1")
    @Column(name = "ID", unique = true, nullable = false)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_CATEGORY")
    private Category category;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SCREEN_SIZE", length = 20)
    private String screenSize;
    @Column(name = "CPU", length = 20)
    private String cpu;
    @Column(name = "RAM")
    private Integer ram;
    @Column(name = "MEMORY")
    private Integer memory;
    @Column(name = "PIN")
    private Integer pin;
    @Column(name = "FRONT_CAM")
    private Integer frontCam;
    @Column(name = "BACK_CAM")
    private Integer backCam;
    @Column(name = "PRICE")
    private Integer price;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phone")
    private Set<Bill> bills = new HashSet(0);
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "phone")
    private Set<Inventory> inventories = new HashSet(0);

    public Phone() {
    }

    public Phone(int id) {
        this.id = id;
    }

    public Phone(Category category, String name, String screenSize, String cpu, Integer ram, Integer memory, Integer pin, Integer frontCam, Integer backCam, Integer price, Set<Bill> bills, Set<Inventory> inventories) {
        this.category = category;
        this.name = name;
        this.screenSize = screenSize;
        this.cpu = cpu;
        this.ram = ram;
        this.memory = memory;
        this.pin = pin;
        this.frontCam = frontCam;
        this.backCam = backCam;
        this.price = price;
        this.bills = bills;
        this.inventories = inventories;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreenSize() {
        return this.screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }

    public String getCpu() {
        return this.cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public Integer getRam() {
        return this.ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getMemory() {
        return this.memory;
    }

    public void setMemory(Integer memory) {
        this.memory = memory;
    }

    public Integer getPin() {
        return this.pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Integer getFrontCam() {
        return this.frontCam;
    }

    public void setFrontCam(Integer frontCam) {
        this.frontCam = frontCam;
    }

    public Integer getBackCam() {
        return this.backCam;
    }

    public void setBackCam(Integer backCam) {
        this.backCam = backCam;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Set<Bill> getBills() {
        return this.bills;
    }

    public void setBills(Set<Bill> bills) {
        this.bills = bills;
    }

    public Set<Inventory> getInventories() {
        return this.inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

}
