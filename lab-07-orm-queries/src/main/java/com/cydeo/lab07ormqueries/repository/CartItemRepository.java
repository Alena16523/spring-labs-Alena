package com.cydeo.lab07ormqueries.repository;


import com.cydeo.lab07ormqueries.entity.CartItem;
import com.cydeo.lab07ormqueries.enums.CartState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long > {

    //Write a derived query to get count cart items
    Integer countAllBy();

    //Write a derived query to get cart items for specific cart state
    List<CartItem> findAllByCart_CartState(CartState state);

    //Write a native query to get cart items for specific cart state and product name
    @Query(value = "SELECT * FROM cart_item ci  JOIN cart c  ON ci.cart_id=c.id " +
            "JOIN product p ON ci.product_id=p.id where c.cart_state=?1 AND p.name=?2",nativeQuery = true)
    List<CartItem> retrieveCartItemsByStateAndName(@Param("cart_state") String cart_state, @Param("name") String name);

    //Write a native query to get cart items for specific cart state and without discount
    @Query(value = "select * from cart_item ci join cart c on ci.cart_id=c.id " +
            "where c.cart_state=?1 and c.discount_id is null", nativeQuery = true)
    List<CartItem> retrieveCartItemsByCartStateWithoutDiscount(@Param ("cartState") String cartState);

    //Write a native query to get cart items for specific cart state and with specific Discount type
    @Query(value = "SELECT * FROM cart_item ci JOIN cart c " +
            "ON ci.cart_id = c.id JOIN discount d ON c.discount_id = d.id" +
            "WHERE d.discount_type = ?1 AND c.cart_state = ?2 ", nativeQuery = true)
    List<CartItem> fetchAllByCartStateAndDiscountType(@Param("discountType") String discountType, @Param("state") String state);

}
