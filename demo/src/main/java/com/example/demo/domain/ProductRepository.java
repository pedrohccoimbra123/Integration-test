package com.example.demo.domain;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {


        //@Query("SELECT p FROM product p ORDER BY p.stock DESC")
        //List<Products> findHighestProductInStock(PageRequest pageable);

        @Query("SELECT p FROM product p ORDER BY p.stock DESC")
        List<Products> findByOrderByHighestProductInStock();


        @Query("SELECT p FROM product p WHERE p.price = (SELECT MAX(p2.price) FROM product p2)")
        Products findProductWithHighestPrice();



}
