package com.example.farms.repositories;

import com.example.farms.models.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {


    @Modifying
    @Transactional
    void deleteAllByInvoiceId(int id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE items SET quantity = quantity-?1 WHERE id=?2 ", nativeQuery = true)
    void updateQuantity(int quantity, int itemId);


    @Query(value = "select i.name , c.price ,c.sale , c.quantity , i.id as 'itemId'" +
            " from items i join cart c on c.item_id = i.id WHERE c.invoice_id=?1 ", nativeQuery = true)
    List<Map<Object,Object>> getInvoiceItems(int invoiceId);


}
