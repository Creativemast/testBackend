package com.esi.test.Model.Catalog;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;

@Entity
@Table(name="catalogs")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class catalog implements Serializable {
	   @Id 
	   @GeneratedValue(strategy=GenerationType.AUTO)
	   private int id;
	   
	   @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	   List<category> categories;
	   
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public catalog() {
		super();
	}
}
