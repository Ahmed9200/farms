package com.example.farms.repositories;

import com.example.farms.models.entities.Invoices;
import com.example.farms.models.enums.InvoiceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

@Repository
public interface InvoicesRepository extends JpaRepository<Invoices, Integer> {

    Page<Invoices> findAllByUsernameLike(String username, Pageable pageable);
    Page<Invoices> findAllByDateBetween(Date s , Date e , Pageable p);

    @Modifying
    @Transactional
    @Query(value = "UPDATE invoices SET status =?1 WHERE id=?2 ", nativeQuery = true)
    void updateStatus(int status, int itemId);


    @Query(value = "select * from invoices where date between ?1 and DATE_ADD(?2,INTERVAL 1 DAY)", nativeQuery = true)
    Page<Invoices> dateBetween(Date s , Date e , Pageable p);

    @Query(value = "select sum(total) as 'total' from invoices", nativeQuery = true)
    Map<Object,Object> getSumOfInvoices();

    @Query(value = "select sum(total) as 'total' from invoices where username like ?1", nativeQuery = true)
    Map<Object,Object> getSumOfInvoicesByUser(String username);

    @Query(value = "select sum(total) as 'total' from invoices where date between ?1 and ?2", nativeQuery = true)
    Map<Object,Object> getSumOfInvoicesByDate(Date start,Date end);

}
