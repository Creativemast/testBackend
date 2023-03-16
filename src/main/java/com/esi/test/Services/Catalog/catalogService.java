package com.esi.test.Services.Catalog;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.esi.test.Model.Catalog.catalog;
import com.esi.test.Model.Catalog.category;
import com.esi.test.Model.Catalog.product;

@Service (value="catalogService")
public class catalogService  {
	@Resource
	private com.esi.test.Repo.Catalog.CatalogRepo catalogRepo;
	@Resource
	private com.esi.test.Repo.Catalog.CategoryRepo categoryRepo;
	@Resource
	private com.esi.test.Repo.Catalog.ProductRepo productRepo;
	
	// CATALOGS //
    public List<catalog> getAllCatalogs(){
    	return this.catalogRepo.findAll();
    }
	
	public catalog findCatalogById(int id) {
		return this.catalogRepo.findByid(id);
	}
	
	public catalog saveCatalog(catalog Catalog) {
		return this.catalogRepo.save(Catalog);
	}
	
	public void deleteCatalog(int id) {
		this.catalogRepo.deleteById(id);
	}

	// CATEGORIES //
	public List<category> getCategoriesByCatalog(catalog Catalog){
    	return this.categoryRepo.findByCatalog(Catalog);
    }

	public List<category> getCategoriesByCategory(category Category){
    	return this.categoryRepo.findByCategory(Category);
    }
	
	public category findCategoryById(int id) {
		return this.categoryRepo.findByid(id);
	}
	
	public category saveCategory(category Category) {
		return this.categoryRepo.save(Category);
	}
	
	public void deleteCategory(int id) {
		this.categoryRepo.deleteById(id);
	}

	// PRODUCTS //
	public List<product> getProductsByCategory(category Category){
    	return this.productRepo.findByCategory(Category);
    }
	
	public product findProductById(int id) {
		return this.productRepo.findByid(id);
	}
	
	public product saveProduct(product Product) {
		return this.productRepo.save(Product);
	}
	
	public void deleteProduct(int id) {
		this.productRepo.deleteById(id);
	}
}
