package com.example.authentication.repositories;

import com.example.authentication.models.Invoices;
import com.example.authentication.models.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Integer> {

}
