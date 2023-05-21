package com.example.authentication.repositories;

import com.example.authentication.models.Categories;
import com.example.authentication.models.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Integer> {
    List<ContactUs> findAllByUserPhoneLike(String phone);
    List<ContactUs> findAllByUsernameLike(String username);
    long countAllByUsernameLike(String username);
    long countAllByUserPhoneLike(String phone);

}
