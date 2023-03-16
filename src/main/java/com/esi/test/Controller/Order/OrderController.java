package com.esi.test.Controller.Order;

import java.util.List;
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

import com.esi.test.Model.Basket.basket;
import com.esi.test.Services.Order.orderService;
import com.esi.test.Services.Catalog.catalogService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
	@Autowired
	@Resource
	private orderService ServiceOrder;

	@Resource
	private catalogService ServiceCatalog;
	
	@GetMapping("/basket/getById")
	public basket getBasketById(@RequestParam int id) {
		return this.ServiceOrder.findBasketByid(id);
	}

	@GetMapping("/basket/getPriceById")
	public basket getBasketPriceById(@RequestParam int id) {
		basket currentBasket = this.ServiceOrder.findBasketByid(id);
		return currentBasket.getTotal();
	}
	
	@PostMapping(value="/basket/add")
	public ResponseEntity<basket> addBasket (@RequestBody basket Basket) {
		basket currentBasket = this.ServiceOrder.findBasketByid(Basket.getId());
		if (currentBasket != null){
			System.out.println("erreur");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			System.out.println("add new basket");
			basket savedBasket = this.ServiceOrder.saveBasket(Basket);
			return new ResponseEntity<basket>(savedBasket, HttpStatus.OK);
		}
	}
		
	@DeleteMapping ("/basket/delete/{idbasket}")
	public void  deleteBasket (@PathVariable int idbasket) {
   	 	this.ServiceOrder.deleteBasket(idbasket);
	}
	
	@PostMapping(value="/basket/addProduct/{productid}")
	public ResponseEntity<basket> addProductToBasket (@PathVariable String productid, @RequestBody int id) {
		basket currentBasket = ServiceOrder.findBasketByid(id);
		if (currentBasket != null) {
			product currentProduct = ServiceCatalog.findProductById(productid);
			currentBasket.addProduct(productid);
			currentBasket.sumTotal(currentProduct.getPrice());

			basket finalBasket = ServiceOrder.saveBasket(currentBasket);
			return new ResponseEntity<basket>(finalBasket, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
