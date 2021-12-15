//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2021.12.13 alle 01:43:22 PM CET 
//


package jdk.spring.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="room" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="customer" type="{http://www.titan.com/Dependency}CustomerType"/&gt;
 *         &lt;element name="Error" type="{http://www.titan.com/Dependency}Error"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "amount",
    "room",
    "customer",
    "error"
})
@XmlRootElement(name = "Reservation", namespace = "http://www.titan.com/Reservation")
public class Reservation {

    protected float amount;
    protected int room;
    @XmlElement(required = true)
    protected CustomerType customer;
    @XmlElement(name = "Error", required = true)
    protected Error error;

    /**
     * Recupera il valore della proprietà amount.
     * 
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Imposta il valore della proprietà amount.
     * 
     */
    public void setAmount(float value) {
        this.amount = value;
    }

    /**
     * Recupera il valore della proprietà room.
     * 
     */
    public int getRoom() {
        return room;
    }

    /**
     * Imposta il valore della proprietà room.
     * 
     */
    public void setRoom(int value) {
        this.room = value;
    }

    /**
     * Recupera il valore della proprietà customer.
     * 
     * @return
     *     possible object is
     *     {@link CustomerType }
     *     
     */
    public CustomerType getCustomer() {
        return customer;
    }

    /**
     * Imposta il valore della proprietà customer.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerType }
     *     
     */
    public void setCustomer(CustomerType value) {
        this.customer = value;
    }

    /**
     * Recupera il valore della proprietà error.
     * 
     * @return
     *     possible object is
     *     {@link Error }
     *     
     */
    public Error getError() {
        return error;
    }

    /**
     * Imposta il valore della proprietà error.
     * 
     * @param value
     *     allowed object is
     *     {@link Error }
     *     
     */
    public void setError(Error value) {
        this.error = value;
    }

}
