package de.ait.javalessons.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

//GET /products/public/list – returns a list of products (accessible to all).
 // Публичный эндпоинт доступен всем пользователям
    @GetMapping("/public/list")
    public ResponseEntity<String> getPublicProductList(){
        return ResponseEntity.ok("Public product list:[Product1, Product2, Product3,...ProductN]");
    }
//GET /products/customer/cart – accessible only to users with the CUSTOMER role, simulates viewing the cart.
// Эндпоинт доступен только пользователям с ролью CUSTOMER

@PreAuthorize("hasRole('CUSTOMER')")
@GetMapping("/customer/cart")
public ResponseEntity<String> getCustomerCart(){
    return ResponseEntity.ok("Customer cart:[Product1, Product2, Product3]");
}

//GET /products/manager/add – accessible only to users with the MANAGER role, simulates adding a new product.
    //Эндпоинт доступен только пользователям с ролью MANAGER
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/manager/add")
    public ResponseEntity<String> getManagerAdd(){
        return ResponseEntity.ok("Manager add:[ProductN+1, ProductN+2, ProductN+3]");
    }
}
