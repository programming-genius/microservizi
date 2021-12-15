package it.backend.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "validation")
public class Validation {

    private Integer id;
    private Date date;
    private BusinessUser businessUser;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="business_user_id")
    public BusinessUser getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUser businessUser) {
        this.businessUser = businessUser;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
