package com.esi.test.Repo.Catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.test.Model.Catalog.catalog;

import java.util.List;

public interface CatalogRepo extends  JpaRepository<catalog, Integer> {
    catalog findByid(int id);
}
