package com.example.authentication.repositories;

import com.example.authentication.models.ContactUs;
import com.example.authentication.models.Partners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnersRepository extends JpaRepository<Partners, Integer> {

    List<Partners> findAllByNameLike(String name);
}
