package it.backend.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {

    private Integer id;
    private String name;
    private Set<Sim> sims;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy="products")
    public Set<Sim> getSims() {
        return sims;
    }

    public void setSims(Set<Sim> sims) {
        this.sims = sims;
    }

    @Override
    public boolean equals(Object product){
        if(product==null){
            return false;
        }
        return id != null && id.equals(((Product)product).id);
    }

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
