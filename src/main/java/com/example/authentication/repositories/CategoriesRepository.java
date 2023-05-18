package com.example.authentication.repositories;

import com.example.authentication.models.Banners;
import com.example.authentication.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {

}
