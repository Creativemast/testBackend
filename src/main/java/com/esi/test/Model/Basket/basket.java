package com.esi.test.Model.Basket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="baskets")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class basket {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private double total;
	
	private String[] productsIds = new String[0];
	      
	public basket() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		return firstname;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String[] getProductsIds() {
		return productsIds;
	}

	public void setProductsIds(String[] productsIds) {
		this.productsIds = productsIds;
	}
	
	private static String[] append(String[] arr, String element) {
		String[] array = new String[arr.length + 1];
		System.arraycopy(arr, 0, array, 0, arr.length);
		array[arr.length] = element;
		return array;
	}
	
	public void addProduct(String productId) {
		this.productsIds = append(this.productsIds, productId);
	}

	public void sumTotal(double price) {
		this.total = this.total + price;
	}
}
