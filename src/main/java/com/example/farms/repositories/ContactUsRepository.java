package com.example.farms.repositories;

import com.example.farms.models.entities.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {
    List<ContactUs> findAllByUserPhoneLike(String phone);
    List<ContactUs> findAllByUsernameLike(String username);
    long countAllByUsernameLike(String username);
    long countAllByUserPhoneLike(String phone);
    List<ContactUs> findAllByOrderByDateDesc();

}
