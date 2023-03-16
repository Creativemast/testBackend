package com.esi.test.Services.Order;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.esi.test.Model.Basket.basket;

@Service (value="orderService")
public class orderService  {
	@Resource
	private com.esi.test.Repo.Basket.BasketRepo BasketRepo;
	
	public basket findBasketByid(int id) {
		return this.BasketRepo.findByid(id);
	}
	
	public basket saveBasket(basket Basket) {
		return this.BasketRepo.save(Basket);
	}
	
	public void deleteBasket(int id) {
		this.BasketRepo.deleteById(id);
    }
}
