package com.example.authentication.repositories.ordersRepo;

import com.example.authentication.models.orders.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {




    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET order_current_status = ?2 " +
            " WHERE id=?1 ", nativeQuery = true)
    void updateCurrentStatus(int orderId , String status);


    @Modifying
    @Transactional
    @Query(value = "UPDATE orders SET scan_date = ?2 " +
            " WHERE id=?1 ", nativeQuery = true)
    void updateScanDate(int orderId , String scanDate);




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

    @Query(value = "select u.phone as 'userPhone' , o.id as 'orderId' , " +
            "o.creation_date as 'creationDate' ," +
            "       o.order_type as 'orderType' , o.owner_id as 'userId' ," +
            " o.order_current_status as 'orderCurrentStatus' ," +
            "       o.scan_date as 'scanDate' " +
            "from users u join orders o on o.owner_id=u.id " +
            "where " +
            "    (?1 is null or o.order_type in (?1)) and " +
            "    (?2 is null or o.order_current_status in (?2)) and " +
            "    (?3 is null or o.id = ?3) and " +
            "    (?9 is null or o.owner_id = ?9) and " +
            "    (?4 is null or u.phone like ?4) and " +
            "    (?5 is null or o.creation_date  between  ?5 and ?6) limit ?7 offset ?8", nativeQuery = true)
    List<Map<Object,Object>> filterOrders(Object orderType , Object currentStatus , Object id ,
                              String phone , String date , String de ,
                              int limit , int offset,Object userId);

    @Query(value = "select u.phone as 'userPhone' , o.id as 'orderId' , " +
            "o.creation_date as 'creationDate' ," +
            "       o.order_type as 'orderType' ," +
            " o.order_current_status as 'orderCurrentStatus' ," +
            "       o.scan_date as 'scanDate' " +
            "from users u join orders o on o.owner_id=u.id " +
            "where " +
            "    (?1 is null or o.order_type in (?1)) and " +
            "    (?2 is null or o.order_current_status in (?2)) and " +
            "    (?9 is null or o.owner_id = ?9) and " +
            "    (?3 is null or o.id = ?3) and " +
            "    (?4 is null or u.phone like ?4) and " +
            "    (?5 is null or o.creation_date  between  ?5 and ?6)" +
            " order by o.creation_date asc limit ?7 offset ?8", nativeQuery = true)
    List<Map<Object,Object>> filterOrdersOrderByCreationASC(Object orderType , Object currentStatus , Object id ,
                              String phone , String date , String de ,
                              int limit , int offset , Object ownerId);


    @Query(value = "select u.phone as 'userPhone' , o.id as 'orderId' , " +
            "o.creation_date as 'creationDate' ," +
            "       o.order_type as 'orderType' ," +
            " o.order_current_status as 'orderCurrentStatus' ," +
            "       o.scan_date as 'scanDate' " +
            "from users u join orders o on o.owner_id=u.id " +
            "where " +
            "    (?1 is null or o.order_type in (?1)) and " +
            "    (?2 is null or o.order_current_status in (?2)) and " +
            "    (?3 is null or o.id = ?3) and " +
            "    (?9 is null or o.owner_id = ?9) and " +
            "    (?4 is null or u.phone like ?4) and " +
            "    (?5 is null or o.creation_date  between  ?5 and ?6) " +
            "order by o.creation_date desc limit ?7 offset ?8", nativeQuery = true)
    List<Map<Object,Object>> filterOrdersOrderByCreationDESC(Object orderType , Object currentStatus , Object id ,
                              String phone , String date, String de ,
                              int limit , int offset , Object userId);


    @Query(value = "select u.phone as 'userPhone' , o.id as 'orderId' , " +
            "o.creation_date as 'creationDate' ," +
            "       o.order_type as 'orderType' ," +
            " o.order_current_status as 'orderCurrentStatus' ," +
            "       o.scan_date as 'scanDate' " +
            "from users u join orders o on o.owner_id=u.id " +
            "where " +
            "    (?1 is null or o.order_type in (?1)) and " +
            "    (?2 is null or o.order_current_status in (?2)) and " +
            "    (?3 is null or o.id = ?3) and " +
            "    (?9 is null or o.owner_id = ?9) and " +
            "    (?4 is null or u.phone like ?4) and " +
            "    (?5 is null or o.creation_date  between  ?5 and ?6) " +
            "order by o.scan_date asc limit ?7 offset ?8", nativeQuery = true)
    List<Map<Object,Object>> filterOrdersOrderByScanDateASC(Object orderType , Object currentStatus , Object id ,
                              String phone , String date , String de ,
                              int limit , int offset,Object userId);


    @Query(value = "select u.phone as 'userPhone' , o.id as 'orderId' , " +
            "o.creation_date as 'creationDate' ," +
            "       o.order_type as 'orderType' ," +
            " o.order_current_status as 'orderCurrentStatus' ," +
            "       o.scan_date as 'scanDate' " +
            "from users u join orders o on o.owner_id=u.id " +
            "where " +
            "    (?1 is null or o.order_type in (?1)) and " +
            "    (?2 is null or o.order_current_status in (?2)) and " +
            "    (?3 is null or o.id = ?3) and " +
            "    (?9 is null or o.owner_id = ?9) and " +
            "    (?4 is null or u.phone like ?4) and " +
            "    (?5 is null or o.creation_date  between  ?5 and ?6) " +
            "order by o.scan_date desc limit ?7 offset ?8", nativeQuery = true)
    List<Map<Object,Object>> filterOrdersOrderByScanDateDESC(Object orderType , Object currentStatus , Object id ,
                              String phone , String date, String de ,
                              int limit , int offset,Object userId);


    @Query(value = "select count(o.id) " +
            "from users u join orders o on o.owner_id=u.id " +
            "where " +
            "    (?1 is null or o.order_type like (?1)) and " +
            "    (?2 is null or o.order_current_status like (?2)) and " +
            "    (?3 is null or o.id = ?3) and " +
            "    (?7 is null or o.owner_id = ?7) and " +
            "    (?4 is null or u.phone like ?4) and " +
            "    (?5 is null or o.creation_date  between ?5 and ?6)", nativeQuery = true)
    long filterOrdersCount(Object orderType , Object currentStatus , Object id ,
                           String phone , String date, String de , Object userId);

    //------------------------------

    @Query(value = "SELECT count(id)  FROM orders " +
            " WHERE owner_id = ?1 and order_current_status in (1) ;", nativeQuery = true)
    long totalNew(int id);

    @Query(value = "SELECT count(id)  FROM orders " +
            " WHERE owner_id = ?1 and order_current_status not in (1,7) ;", nativeQuery = true)
    long totalWorking(int id);

    @Query(value = "SELECT count(id)  FROM orders " +
            " WHERE owner_id = ?1 and order_current_status in (7) ;", nativeQuery = true)
    long totalFinished(int id);

}
