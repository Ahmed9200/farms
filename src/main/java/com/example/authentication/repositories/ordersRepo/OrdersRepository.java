package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {



    @Query(value = "SELECT * FROM orders limit ?1 offset ?2  ", nativeQuery = true)
    List<Orders> findAllOrders(int limit, int offset );


    //--------------------------------------

    @Query(value = "SELECT * " +
            " FROM orders " +
            " WHERE creation_date between  ?3 and ?4   limit ?1 offset ?2  ", nativeQuery = true)
    List<Orders> filterOrdersByCreationDate(int limit, int offset , Object sDate , Object eDate);

    @Query(value = "SELECT count(distinct id) " +
            " FROM orders " +
            " WHERE creation_date between  ?3 and ?4  limit ?1 offset ?2  ", nativeQuery = true)
    long filterOrdersByCreationDateCount(int limit,int offset , Object sDate , Object eDate);


    //--------------------------------------


    @Query(value = "SELECT * " +
            " FROM orders " +
            " WHERE scan_date between  ?3 and ?4   limit ?1 offset ?2  ", nativeQuery = true)
    List<Orders> filterOrdersByScanDate(int limit, int offset , Object sDate , Object eDate);

    @Query(value = "SELECT count(distinct id) " +
            " FROM orders " +
            " WHERE scan_date between  ?3 and ?4  limit ?1 offset ?2  ", nativeQuery = true)
    long filterOrdersByScanDateCount(int limit,int offset , Object sDate , Object eDate);

    //-----------------------------------------

    @Query(value = "SELECT * " +
            " FROM orders " +
            " WHERE order_type like ?3   limit ?1 offset ?2  ", nativeQuery = true)
    List<Orders> filterOrdersByType(int limit, int offset , String type);

    @Query(value = "SELECT count(distinct id) " +
            " FROM orders " +
            " WHERE order_type like ?3  limit ?1 offset ?2  ", nativeQuery = true)
    long filterOrdersByTypeCount(int limit,int offset , String type );


    //----------------------------------------





}
