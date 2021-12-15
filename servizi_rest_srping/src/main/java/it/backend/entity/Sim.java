package it.backend.entity;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "sim")
@NamedEntityGraph(name="sim-business_user",
attributeNodes = {@NamedAttributeNode("businessUser")}
)
public class Sim {

    private Integer id;
    private String msisdn;
    private String imsi;
    private BusinessUser businessUser;
    private Set<Product> products;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "msisdn")
    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @Column(name = "imsi")
    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_user_id")
    @LazyToOne(LazyToOneOption.NO_PROXY)
    public BusinessUser getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUser businessUser) {
        this.businessUser = businessUser;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "sim_product",
            joinColumns = {@JoinColumn(name = "sim_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    /**
     * Synchronization method
     * @param product to be added to the set
     */
    public void addProduct(Product product){
        if(product==null)
            throw new IllegalArgumentException("Product null!");
        if(this.products==null)
            this.products = new LinkedHashSet<>();
        this.products.add(product);
        if(product.getSims()==null)
            product.setSims(new LinkedHashSet<>());
        product.getSims().add(this);
    }

    /**
     * Synchronization method
     * @param product to be removed from the list
     */
    public void removeProduct(Product product){
        if(product==null)
            throw new IllegalArgumentException("Product null!");
        if(this.products==null)
            throw new IllegalArgumentException("Products empty list!");
        if(product.getSims()==null)
            throw new IllegalArgumentException("Sims empty list!");
        this.products.remove(product);
        product.getSims().remove(this);
    }

    /**
     * Synchronization method
     * Removes all products
     */
    public void removeProducts() {
        Iterator<Product> iterator = this.products.iterator();
        while(iterator.hasNext()){
            Product product = iterator.next();
            product.getSims().remove(this);
            iterator.remove();
        }
    }

    @Override
    public boolean equals(Object sim){
        if(sim==null){
            return false;
        }
        return id != null && id.equals(((Sim)sim).id);
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public String toString() {
        return "Sim{" +
                "id=" + id +
                ", msisdn='" + msisdn + '\'' +
                ", imsi='" + imsi + '\'' +
                '}';
    }
}
