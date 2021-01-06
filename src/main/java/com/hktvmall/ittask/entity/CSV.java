package com.hktvmall.ittask.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// import org.hibernate.annotations.Tables;

@Entity
@Table(name="item") 
public class CSV implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "quantity")
    private Integer quantity;

    public CSV(){}

    public CSV(String id, String name, Integer quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
	}

    public String getid() {
	    return id;
    };
    
    public void setId(String id) {
	    this.id = id;
    }
    
    public String getname() {
	    return name;
    };

    public void setname(String name) {
	    this.name = name;
    }

    public Integer getquantity() {
	    return quantity;
    }
    
    public void setquantity(Integer quantity) {
	    this.quantity = quantity;
    }
}
