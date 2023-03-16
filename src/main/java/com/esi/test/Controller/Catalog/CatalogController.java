package com.esi.test.Controller.Catalog;

import java.util.List;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esi.test.Model.Catalog.catalog;
import com.esi.test.Model.Catalog.category;
import com.esi.test.Model.Catalog.product;
import com.esi.test.Services.Catalog.catalogService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CatalogController {
	@Autowired
	@Resource
	private catalogService ServiceCatalog;
	
	public static <T> T fromJSON(final TypeReference<T> type, final String jsonPacket) {
	   T data = null;

	   try {
	      data = new ObjectMapper().readValue(jsonPacket, type);
	   } catch (Exception e) {
	      // Handle the problem
	   }
	   return data;
	}
	
	// CATALOGS //
	@GetMapping("/catalog/all")
	public List<catalog> allCatalogs() {
		return this.catalogService.getAllCatalogs();
	}
	
	@PostMapping(value="/catalog/add")
	public  ResponseEntity<catalog>  addCatalog (@RequestBody Map<String, Object> Catalog) {		
		String categories = Catalog.get("categories").toString();
		List<piece> categoriesJson = fromJSON(new TypeReference<List<cateogry>>() {}, categories);
		
		catalog currentCatalog = new catalog();
		catalog finalCatalog = this.ServiceCatalog.saveCatalog(currentCatalog);
		
		for (int i=0 ; i<categoriesJson.size() ; i++) {
			this.ServiceCatalog.saveCategory(
				new category(
					categoriesJson.get(i).getName(), 
					categoriesJson.get(i).getDescription(), 
					currentCatalog
				)
			);
		}
		
		return new ResponseEntity<catalog>(finalCatalog, HttpStatus.OK);
	}
		
	@DeleteMapping ("/catalog/delete/{idcatalog}")
	public void  deleteCatalog (@PathVariable int idcatalog) {
   	 	this.ServiceCatalog.deleteCatalog(idcatalog);
	}
	
	@GetMapping("/catalog/getById")
	public catalog getCatalogById(@RequestParam int id) {
		return this.ServiceCatalog.findCatalogById(id);
	}

	// CATEGORIES //
	@GetMapping("/cateogry/allbycatalog")
	public List<category> categoriesByCatalog(@PathVariable int id) {
		catalog currentCatalog = this.ServiceCatalog.findCatalogById(id);
		return this.ServiceCatalog.getCategoriesByCatalog(currentCatalog);
	}

	@GetMapping("/cateogry/allbycategory")
	public List<category> categoriesByCategory(@PathVariable int id) {
		catalog currentCategory = this.ServiceCatalog.findCategoryById(id);
		return this.ServiceCatalog.getCategoriesByCategory(currentCategory);
	}

	@PostMapping(value="/category/add")
	public  ResponseEntity<category>  addCategory (@RequestBody Map<String, Object> Category) {	
		String name = (String) Category.get("name");
		String description = (String) Category.get("description");
		int catalog = Integer.parseInt(Category.get("catalog").toString());

		category currentCategory = new category(name, description, catalog);
		category finalCategory = this.ServiceCatalog.saveCatalog(currentCategory);
		
		return new ResponseEntity<category>(finalCategory, HttpStatus.OK);
	}

	@DeleteMapping ("/category/delete/{idcategory}")
	public void  deleteCategory (@PathVariable int idcategory) {
   	 	this.ServiceCatalog.deleteCategory(idcategory);
	}
	
	@GetMapping("/category/getById")
	public category getCategoryById(@RequestParam int id) {
		return this.ServiceCatalog.findCategoryById(id);
	}

	// PRODUCTS //
	@GetMapping("/product/allbycategory")
	public List<product> productsByCtegory(@PathVariable int id) {
		category currentCategory = this.ServiceCatalog.findCategoryById(id);
		return this.ServiceCatalog.getProductsByCategory(currentCategory);
	}

	@PostMapping(value="/product/add")
	public  ResponseEntity<product>  addCategory (@RequestBody Map<String, Object> Product) {	
		String name = (String) Product.get("name");
		double price = (double) Product.get("price");
		int quantity = (double) Product.get("quantity");
		int category = Integer.parseInt(Product.get("category").toString());

		product currentProduct = new product(name, price, quantity, category);
		product finalProduct = this.ServiceCatalog.saveProduct(currentProduct);
		
		return new ResponseEntity<product>(finalProduct, HttpStatus.OK);
	}

	@DeleteMapping ("/product/delete/{idproduct}")
	public void  deleteProduct (@PathVariable int idproduct) {
   	 	this.ServiceCatalog.deleteProduct(idproduct);
	}
	
	@GetMapping("/product/getById")
	public product getProductById(@RequestParam int id) {
		return this.ServiceCatalog.findProductById(id);
	}
}
