package com.example.authentication.repositories;

import com.example.authentication.models.CartItems;
import com.example.authentication.models.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

}
