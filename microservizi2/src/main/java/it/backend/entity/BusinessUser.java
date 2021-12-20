package it.backend.entity;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "business_user")
public class BusinessUser {

    private Integer id;
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private List<Sim> sim;
    private Validation validation;
    //private int version;

    /*@Version
    @Column(name="version")
    public int getVersion() {
        return version;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "firstname")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastname")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "fiscalcode")
    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "businessUser", orphanRemoval = false)
    public List<Sim> getSim() {
        return sim;
    }

    public void setSim(List<Sim> sim) {
        this.sim = sim;
    }

    /**
     * Synchronization method
     *
     * @param sim new sim to be added to the list
     * @throws IllegalArgumentException if the list is empty
     */
    public void addSim(Sim sim) {
        if (sim == null)
            throw new IllegalArgumentException("Sim null!");
        if (this.sim == null)
            this.sim = new ArrayList<>();
        this.sim.add(sim);
        sim.setBusinessUser(this);
    }

    /**
     * Synchronization method
     *
     * @param sim to be removed from the list
     * @throws IllegalArgumentException if the list is empty
     */
    public void removeSim(Sim sim) {
        if (sim == null)
            throw new IllegalArgumentException("Sim null!");
        if (this.sim == null)
            throw new IllegalArgumentException("Empty list!");
        sim.setBusinessUser(null);
        this.sim.remove(sim);
    }

    /**
     * Synchronization method
     * Remove all sim
     *
     * @throws IllegalArgumentException if the list is empty
     */
    public void removeAllSim() {
        if (this.sim == null)
            throw new IllegalArgumentException("Empty list!");
        Iterator<Sim> iterator = this.sim.iterator();
        while (iterator.hasNext()) {
            iterator.next().setBusinessUser(null);
            iterator.remove();
        }
    }

    public void addValidation(Validation validation) {
        if (validation == null)
            throw new IllegalArgumentException("Empty validation");
        this.validation = validation;
        validation.setBusinessUser(this);
    }

    public void removeValidation() {
        if (validation != null) {
            validation.setBusinessUser(null);
            validation = null;
        }
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "businessUser", orphanRemoval = true)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    @Override
    public boolean equals(Object businessUser) {
        if (businessUser == null) {
            return false;
        }
        return id != null && id.equals(((BusinessUser) businessUser).id);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fiscalCode='" + fiscalCode + '\'' +
                '}';
    }
}
