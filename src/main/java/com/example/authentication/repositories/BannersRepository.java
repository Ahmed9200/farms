package com.example.authentication.repositories;

import com.example.authentication.models.Banners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannersRepository extends JpaRepository<Banners, Integer> {

}
