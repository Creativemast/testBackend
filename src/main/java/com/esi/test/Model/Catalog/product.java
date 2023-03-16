package com.esi.test.Model.Catalog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="products")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class product implements Serializable {
	
	   @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	   private int id;
	   
	   @Column(name="name")
	   private String name;
	   
	   @Column(name="price")
	   private double price;

	   @Column(name="quantity")
	   private Integer quantity;

	   @ManyToOne(fetch = FetchType.LAZY, optional = false)
	   @JoinColumn(name = "category_id", nullable = false)
	   private category category;
	   
	public product() {
	    super();
	}
	   
	public product(String name, double description, Integer quantity, category category) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public category getCategory() {
		return category;
	}

	public void setCategory(category category) {
		this.category = category;
	}
}