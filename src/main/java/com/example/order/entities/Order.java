package com.example.order.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_ORDER")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;

    private Instant date;

    @ManyToOne
    @JoinColumn(name="SELLER_USER_ID")
    private Seller seller;


    @ManyToOne
    @JoinColumn(name="CUSTOMER_USER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name ="ADDRESS_ID")
    private Address deliveredAddress;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name ="ORDER_ID")
    private List<Item> items = new ArrayList<>();

   
    

    public Order(){

    }
    
    public Order(Long id, Long number, Instant date) {
        this.id = id;
        this.number = number;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveredAddress() {
        return deliveredAddress;
    }

    public void setDeliveredAddress(Address deliveredAddress) {
        this.deliveredAddress = deliveredAddress;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

   /*  
      Se alguns dos metodos abaixo forem implementados, teremos
      uma agregacao e nao uma composicao.

            public void addItem(Item item){
                items.add(item);
            }
        
            public List<Item> getItems() {
                return items;
            }

            public void setItems(List<Item> items) {
                this.items = items;
            }
    */

    public void addItem(Integer amount, Product product){
        Item item = new Item();
        item.setProduct(product);
        item.setUnitPrice(product.getPrice());
        item.setAmount(amount);
        items.add(item);
    }
}
