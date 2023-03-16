package com.esi.test.Repo.Catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.test.Model.Catalog.category;
import com.esi.test.Model.Catalog.product;

import java.util.List;

public interface ProductRepo extends  JpaRepository<product, Integer> {
	List<product> findByCategory(category category);
}
