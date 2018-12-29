package entities;
// Generated Dec 14, 2018 10:17:08 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Account generated by hbm2java
 */
@Entity
@Table(name = "ACCOUNT",
         schema = "dbo",
         catalog = "WINDOWS"
)
public class Account implements java.io.Serializable {

    @Id
    @Column(name = "USERNAME", unique = true, nullable = false, length = 20)
    private String username;
    @Column(name = "PASSWORD", length = 100)
    private String password;

    public Account() {
    }

    public Account(String username) {
        this.username = username;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
