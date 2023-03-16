package com.esi.test.Repo.Catalog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esi.test.Model.Catalog.catalog;
import com.esi.test.Model.Catalog.category;

import java.util.List;

public interface CategoryRepo extends  JpaRepository<category, Integer> {
	List<category> findByCatalog(catalog catalog);
	List<category> findByCategory(category category);
}
