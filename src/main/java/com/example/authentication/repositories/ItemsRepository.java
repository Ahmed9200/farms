package com.example.authentication.repositories;

import com.example.authentication.models.Categories;
import com.example.authentication.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {

}
