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
@Table(name="categories")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class cateogory implements Serializable {
	
	   @Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	   private int id;
	   
	   @Column(name="name")
	   private String name;
	   
	   @Column(name="description")
	   private String description;
	   
	   @ManyToOne(fetch = FetchType.LAZY, optional = false)
	   @JoinColumn(name = "catalog_id", nullable = false)
	   private catalog catalog;

	   @ManyToOne(fetch = FetchType.LAZY, optional = false)
	   @JoinColumn(name = "category_id", nullable = false)
	   private category mainCategory;

	   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	   List<product> products;

	   @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	   List<category> categories;
	   
	public category() {
	    super();
	}
	   
	public category(String name, double description, catalog catalog) {
		this.name = name;
		this.description = description;
		this.catalog = catalog;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public catalog getCatalog() {
		return catalog;
	}

	public void setCatalaog(catalog catalog) {
		this.catalog = catalog;
	}

	public category getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(catalog mainCategory) {
		this.mainCategory = mainCategory;
	}
}