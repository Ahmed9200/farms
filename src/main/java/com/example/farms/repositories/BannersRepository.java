package com.example.farms.repositories;

import com.example.farms.models.Banners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannersRepository extends JpaRepository<Banners, Integer> {

}
