package com.esi.test.Repo.Basket;

import org.springframework.data.jpa.repository.JpaRepository;
import com.esi.test.Model.Basket.basket;

import java.util.List;

public interface BasketRepo extends  JpaRepository<basket, Integer> {
	basket findByid(int id);
}
