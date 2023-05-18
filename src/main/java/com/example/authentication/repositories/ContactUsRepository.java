package com.example.authentication.repositories;

import com.example.authentication.models.Categories;
import com.example.authentication.models.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {

}
